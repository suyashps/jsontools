package com.mywork.jsontools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class MainWrapper {

    public static void main(String[] args) {

        FileReader argFileReader = getFileFromArgs(args);
        if (argFileReader == null){
            return;
        }

        ObjectMapper dataMapper = new ObjectMapper();
        JSONFlattener jsonFlattener = new JSONFlattener();

        try {
            Map<String,JsonNode> output = jsonFlattener.flatten(argFileReader);
            System.out.println(dataMapper.writeValueAsString(output));
        } catch (JsonProcessingException e) {
            System.out.println("Error parsing json: " + e.getMessage());
        }  catch (IOException e) {
            System.out.println("Error reading file contents: " + e.getMessage());
        }
    }

    private static FileReader getFileFromArgs(String[] args){
        String testFile;
        FileReader argFileReader = null;
        if(args.length > 0){
            testFile = args[0];
            try{
                argFileReader = new FileReader(testFile);
            } catch(FileNotFoundException fne){
                System.out.println("File not found: " + testFile);
            }
        } else {
            System.out.println("Missing args: json file");
        }
        return argFileReader;
    }
}
