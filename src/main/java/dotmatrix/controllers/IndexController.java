/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotmatrix.controllers;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import simple.escp.Template;
import simple.escp.json.JsonTemplate;

/**
 *
 * @author tmsdzil
 */
public class IndexController {

    String filePathString;
    Report report;

    public IndexController(String filePath) {
       this.filePathString = filePath;
    }

    public String getFilePathString() {
        return filePathString;
    }

    public void setFilePathString(String filePathString) {
        this.filePathString = filePathString;
    }
    
    public Report gsonAction() throws FileNotFoundException{
        
        Gson gson = new Gson();
        report = gson.fromJson(new FileReader(this.filePathString), Report.class);
        return report;
        
    }
    
    public Template getTemplate() throws IOException, URISyntaxException{
        Template template = new JsonTemplate(Thread.currentThread().
                getContextClassLoader().
                getResource("template.json").
                toURI()
        );
        return template;
    }
    
    public Map<String, Object> getContent(){
        Map<String, Object> value = new HashMap<>();
        value.put("invoiceNo", "INVC-00001");
        List<Map<String, Object>> tables = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> line = new HashMap<>();
            line.put("code", String.format("XXX-%d", i));
            line.put("name", String.format("Barang ke %d", i));
            line.put("qty", String.format("%d", i * i));
            tables.add(line);
        }
        value.put("table_source", tables);
        return value;
    }
}
