/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotmatrix.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

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
        
        // Reader reader = new FileReader(this.filePathString)
        try (JsonReader reader = new JsonReader(new FileReader(this.filePathString))) {

            // Converting JSON File to Java Object
            HashMap<String, String> hashMapResult = gson.fromJson(reader, HashMap.class);
            return hashMapResult;

        } catch (IOException e) {

        }
        return null;
    }

}
