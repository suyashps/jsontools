package com.mywork.jsontools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONFlattener {
    private Map<String, JsonNode> output;
    ObjectMapper jsonObjectMapper;

    JSONFlattener(){
        this.jsonObjectMapper = new ObjectMapper();
    }
    public Map<String, JsonNode> flatten(JsonNode root){
        this.output = new HashMap<>();
        flattenHelper(root, "");
        return output;
    }

    public Map<String, JsonNode> flatten (FileReader jsonReader) throws IOException{
        JsonNode root = jsonObjectMapper.readTree(jsonReader);
        return flatten(root);
    }

    public Map<String, JsonNode> flatten(File jsonFile) throws IOException {
        JsonNode root = jsonObjectMapper.readTree(jsonFile);
        return flatten(root);
    }

    private void flattenHelper(JsonNode root, String parentKey){
        if(root.isMissingNode()){
            return;
        } else if(root.isObject()){
            ObjectNode node = (ObjectNode) root;
            Iterator<Map.Entry<String,JsonNode>> entryIterator = node.fields();
            if(entryIterator == null || !entryIterator.hasNext()){
                return;
            }
            while(entryIterator.hasNext()){
                Map.Entry<String,JsonNode> entry = entryIterator.next();
                String newKey = parentKey.isEmpty() ? entry.getKey() : parentKey + "." + entry.getKey();
                flattenHelper(entry.getValue(),newKey);
            }
        } else {
            output.put(parentKey,root);
        }
    }
}
