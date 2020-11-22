package com.mywork.jsontools;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONFlattener {
    private Map<String, JsonNode> output;
    ObjectMapper jsonObjectMapper;

    JSONFlattener() {
        this.jsonObjectMapper = new ObjectMapper();
        this.output = new HashMap<>();
        this.jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        this.jsonObjectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        this.jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, true);
    }

    private Map<String, JsonNode> flatten(JsonNode root) {
        flattenHelper(root, "");
        return output;
    }

    public Map<String, JsonNode> flattenAsMap(File jsonFile) throws IOException {
        return flatten(this.jsonObjectMapper.readTree(jsonFile));
    }

    public String flattenAsString(File jsonFile) throws IOException {
        return this.jsonObjectMapper.writeValueAsString(flattenAsMap(jsonFile));
    }

    public JsonNode flattenAsJsonNode(File jsonFile) throws IOException {
        return this.jsonObjectMapper.readTree(flattenAsString(jsonFile));
    }

    public ObjectMapper getJsonObjectMapper(){
        return this.jsonObjectMapper;
    }

    private void flattenHelper(JsonNode root, String parentKey) {
        if (root.isMissingNode()) {
            return;
        } else if (root.isObject()) {
            ObjectNode node = (ObjectNode) root;
            Iterator<Map.Entry<String, JsonNode>> entryIterator = node.fields();
            if (entryIterator == null || !entryIterator.hasNext()) {
                return;
            }
            while (entryIterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = entryIterator.next();
                String newKey = parentKey.isEmpty() ? entry.getKey() : parentKey + "." + entry.getKey();
                flattenHelper(entry.getValue(), newKey);
            }
        } else {
            output.put(parentKey, root);
        }
    }
}
