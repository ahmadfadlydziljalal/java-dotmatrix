/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotmatrix.controllers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author tmsdzil
 */
public class Report {

    String filePathString;

    public Report(String filePathString) {
        this.filePathString = filePathString;
    }

    public String getFilePathString() {
        return filePathString;
    }

    public void setFilePathString(String filePathString) {
        this.filePathString = filePathString;
    }

    public HashMap<String, String> convertJsonToObject() {

        Gson gson = new Gson();
        HashMap<String, String> hashMapResult = new HashMap<>();
        
        try (JsonReader reader = new JsonReader(new FileReader(this.filePathString))) {
            
            // Converting JSON File to Java Object
            hashMapResult = gson.fromJson(reader, HashMap.class);
            
        } catch (IOException e) {

        }
        return hashMapResult;
    }

}
