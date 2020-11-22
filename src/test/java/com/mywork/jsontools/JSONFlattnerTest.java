package com.mywork.jsontools;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.builders.JUnit3Builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;


public class JSONFlattnerTest {
    String testResourcePath =  "src/test/resources";
    JSONFlattener jsonFlanttner;
    ObjectMapper jsonObjectMapper;
    @Before
    public void init(){
        jsonFlanttner = new JSONFlattener();
        jsonObjectMapper = new ObjectMapper();
    }
    @Test
    public void flatten2NestedJsonTest() throws IOException {
        Map<String, JsonNode> flattenedJson = jsonFlanttner.flatten(new File(testResourcePath + "/nested_2.json"));
        JsonNode input = jsonObjectMapper.readTree(jsonObjectMapper.writeValueAsString(flattenedJson));
        JsonNode output = jsonObjectMapper.readTree(new File(testResourcePath + "/flattened/nested_2_flat.json"));
        Assert.assertEquals(input,output);
    }

    @Test
    public void flatten3plusNestedJsonTest() throws IOException {
        Map<String, JsonNode> flattenedJson = jsonFlanttner.flatten(new File(testResourcePath + "/nested_3.json"));
        JsonNode input = jsonObjectMapper.readTree(jsonObjectMapper.writeValueAsString(flattenedJson));
        JsonNode output = jsonObjectMapper.readTree(new File(testResourcePath + "/flattened/nested_3_flat.json"));
        Assert.assertEquals(input,output);
    }

    @Test(expected = JsonParseException.class)
    public void badJsonTest() throws IOException {
        Map<String, JsonNode> flattenedJson = jsonFlanttner.flatten(new File(testResourcePath + "/bad_json.json"));
        JsonNode input = jsonObjectMapper.readTree(jsonObjectMapper.writeValueAsString(flattenedJson));
        JsonNode output = jsonObjectMapper.readTree(new File(testResourcePath + "/flattened/default_empty_file.json"));
        Assert.assertEquals(input,output);
    }

    @Test
    public void emptyJsonTest() throws IOException {
        Map<String, JsonNode> flattenedJson = jsonFlanttner.flatten(new File(testResourcePath + "/blank.json"));
        JsonNode input = jsonObjectMapper.readTree(jsonObjectMapper.writeValueAsString(flattenedJson));
        JsonNode output = jsonObjectMapper.readTree(new File(testResourcePath + "/flattened/default_empty_file.json"));
        Assert.assertEquals(input,output);
    }

    @Test(expected = JsonParseException.class)
    public void emptyFileTest() throws IOException {
        Map<String, JsonNode> flattenedJson = jsonFlanttner.flatten(new File(testResourcePath + "/bad_file.json"));
        JsonNode input = jsonObjectMapper.readTree(jsonObjectMapper.writeValueAsString(flattenedJson));
        JsonNode output = jsonObjectMapper.readTree(new File(testResourcePath + "/flattened/default_empty_file.json"));
        Assert.assertEquals(input,output);
    }
}
