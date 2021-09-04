package com.elastic.javaingestion.elastic.utils;
import com.elastic.javaingestion.elastic.services.device.DVService;
import com.elastic.javaingestion.elastic.services.phonecall.CallService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Stream;

import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;

import static java.util.stream.Collectors.toList;
//import org.codehaus.jackson.map.ObjectMapper;

@Controller
public class ContentProcessor {
    public final DVService deviceservice;
    public final CallService callService;

    @Autowired
    public ContentProcessor(DVService deviceservice, CallService callService){
        this.deviceservice = deviceservice;
        this.callService = callService;
    }

    public void run(BufferedReader content, String ext, Path path){
        if(ext.equals("xml")) {
            xmlToJSon(content);
        }
        if(ext.equals("csv")){
            csvToJSon(path);
        }
        System.out.println("FILE EXTENSION: "+ ext);
    }

    private JSONObject csvObject(String line, String first){
        System.out.println(line);
        System.out.println(first);
        try{
            String[] columns = first.split(",");
            String[] row = line.split(",");
            JSONObject obj = new JSONObject();

            return new JSONObject(first.split(","), line.split(","));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new JSONObject();
    }

    public void csvToJSon(Path path){
        try {
            List<String> lines = Files.readAllLines(Paths.get(path.toString()));
            String firstLine = lines.toArray()[0].toString();
            callService.csvToJson(lines, firstLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void xmlToJSon(BufferedReader content){
        StringBuilder str = new StringBuilder();
        content.lines().forEach(line -> { str.append(line); });
        JSONObject xml = XML.toJSONObject(str.toString());
        List<JSONObject> items = new ArrayList<>();
        try{
            JSONArray devices = xml.getJSONArray("device");
            devices.forEach(dvc -> {

                JSONObject device = (JSONObject)dvc;
                JSONObject device_obj = new JSONObject();
                device_obj.put("sms_messages", device.getJSONObject("sms_messages")
                        .getJSONArray("sms_message"));
                device_obj.put("image_files", device.getJSONObject("image_files")
                        .getJSONArray("image"));
                device_obj.put("incoming_calls", device.getJSONObject("incoming_calls")
                        .getJSONArray("incoming_call"));
                device_obj.put("outgoing_calls", device.getJSONObject("outgoing_calls")
                        .getJSONArray("outgoing_call"));
                device_obj.put("documents_files", device.getJSONObject("documents_files")
                        .getJSONArray("documents"));
                device_obj.put("contacts", device.getJSONObject("contacts")
                        .getJSONArray("contact"));
                device_obj.put("missed_calls", device.getJSONObject("missed_calls")
                        .getJSONArray("missed_call"));
                device_obj.put("general_information", device.getJSONObject("general_information"));
                items.add(device_obj);
            });

            //System.out.println(items);
            //deviceservice.saveDocuments(items);
            deviceservice.bulkSaving(items);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public String sha256(String text){
        String uuid = UUID.randomUUID().toString();
        return DigestUtils.sha256Hex(text+uuid).toUpperCase();
    }

    public void uploadFile(String name, String ext, Path path) {
        FileHandler fh = new FileHandler();
        String base64 = fh.loadFile(path.toString());
        String[] pathreg = path.toString().split("/");
        Map<String, String> data = new HashMap<>();
        BasicFileAttributes attr = null;
        try {
            attr = Files.readAttributes(path, BasicFileAttributes.class);
            data.put("image_file_memory", String.valueOf(attr.size()));
            data.put("image_file_size", String.valueOf(attr.size()));
            data.put("image_file_date_time", attr.creationTime().toString());
            data.put("image_file_date_time_modified", attr.lastModifiedTime().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.put("image_file_path", path.toString());
        data.put("image_file_name", name);
        data.put("image_file_stored_name", name);
        data.put("image_file_extension", ext);
        data.put("image_file_base64", "data:image/"+ext+";base64,"+base64);
        data.put("image_file_imei", pathreg[pathreg.length - 2]);
        data.put("imei", pathreg[pathreg.length - 2]);
        data.put("image_file_identifier", sha256(data.toString()));

        callService.ingestImage(data);
    }
}
