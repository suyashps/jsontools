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
    @Before
    public void init(){
        jsonFlanttner = new JSONFlattener();
    }
    @Test
    public void flatten2NestedJsonTest() throws IOException {
        JsonNode input = jsonFlanttner.flattenAsJsonNode(new File(testResourcePath + "/nested_2.json"));
        JsonNode output = jsonFlanttner.getJsonObjectMapper().readTree(new File(testResourcePath + "/flattened/nested_2_flat.json"));
        Assert.assertEquals(input,output);
    }

    @Test
    public void flatten3plusNestedJsonTest() throws IOException {
        JsonNode input = jsonFlanttner.flattenAsJsonNode(new File(testResourcePath + "/nested_3.json"));
        JsonNode output = jsonFlanttner.getJsonObjectMapper().readTree(new File(testResourcePath + "/flattened/nested_3_flat.json"));
        Assert.assertEquals(input,output);
    }

    @Test(expected = JsonParseException.class)
    public void badJsonTest() throws IOException {
        JsonNode input = jsonFlanttner.flattenAsJsonNode(new File(testResourcePath + "/bad_json.json"));
        JsonNode output = jsonFlanttner.getJsonObjectMapper().readTree(new File(testResourcePath + "/flattened/default_empty_file.json"));
        Assert.assertEquals(input,output);
    }

    @Test
    public void emptyJsonTest() throws IOException {
        JsonNode input = jsonFlanttner.flattenAsJsonNode(new File(testResourcePath + "/blank.json"));
        JsonNode output = jsonFlanttner.getJsonObjectMapper().readTree(new File(testResourcePath + "/flattened/default_empty_file.json"));
        Assert.assertEquals(input,output);
    }

    @Test(expected = JsonParseException.class)
    public void emptyFileTest() throws IOException {
        JsonNode input = jsonFlanttner.flattenAsJsonNode(new File(testResourcePath + "/bad_file.json"));
        JsonNode output = jsonFlanttner.getJsonObjectMapper().readTree(new File(testResourcePath + "/flattened/default_empty_file.json"));
        Assert.assertEquals(input,output);
    }

    @Test
    public void stringEscapeGoodTest() throws IOException {
        JsonNode input = jsonFlanttner.flattenAsJsonNode(new File(testResourcePath + "/escaped_string.json"));
        JsonNode output = jsonFlanttner.getJsonObjectMapper().readTree(new File(testResourcePath + "/flattened/escape_good.json"));
        Assert.assertEquals(input,output);
    }


    @Test(expected = JsonParseException.class)
    public void stringEscapeBadTest() throws IOException {
        JsonNode input = jsonFlanttner.flattenAsJsonNode(new File(testResourcePath + "/bad_escaped_string.json"));
        JsonNode output = jsonFlanttner.getJsonObjectMapper().readTree(new File(testResourcePath + "/flattened/default_empty_file.json"));
        Assert.assertEquals(input,output);
    }

    @Test
    public void dateStringTest() throws IOException {
        JsonNode input = jsonFlanttner.flattenAsJsonNode(new File(testResourcePath + "/date.json"));
        JsonNode output = jsonFlanttner.getJsonObjectMapper().readTree(new File(testResourcePath + "/flattened/date_flat.json"));
        Assert.assertEquals(input,output);
    }
}
