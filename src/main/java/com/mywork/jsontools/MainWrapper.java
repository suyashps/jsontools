package com.mywork.jsontools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class MainWrapper {

    public static void main(String[] args) {

        File argFile = getFileFromArgs(args);
        if (argFile == null) {
            return;
        }

        JSONFlattener jsonFlattener = new JSONFlattener();

        try {
            System.out.println(jsonFlattener.flattenAsString(argFile));
        } catch (JsonProcessingException e) {
            System.out.println("Error parsing json: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file contents: " + e.getMessage());
        }
    }

    private static File getFileFromArgs(String[] args) {
        String testFile;
        File argFile = null;
        if (args.length > 0) {
            testFile = args[0];
            argFile = new File(testFile);
        } else {
            System.out.println("Missing args: json file. See README for details.");
        }
        return argFile;
    }
}
