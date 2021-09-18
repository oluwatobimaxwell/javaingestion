package com.elastic.javaingestion.elastic.utils;
import com.elastic.javaingestion.elastic.services.device.DVService;
import com.elastic.javaingestion.elastic.services.phonecall.CallService;
import org.apache.commons.codec.digest.DigestUtils;
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
            //String firstLine = lines.toArray()[0].toString();
            String firstLine = lines.get(0);
            lines.remove(0);
            callService.csvToJson(lines, firstLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject xmlToJSONObject(JSONObject device){
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

        return device_obj;
    }

    public void xmlToJSon(BufferedReader content){
        StringBuilder str = new StringBuilder();
        content.lines().forEach(line -> { str.append(line); });
        JSONObject xml = XML.toJSONObject(str.toString());
        List<JSONObject> items = new ArrayList<>();
        try{
            JSONArray devices = xml.getJSONArray("device");
            devices.forEach(dvc -> {
                JSONObject device = new JSONObject(dvc.toString());
                items.add(xmlToJSONObject(device));

            });

            deviceservice.bulkSaving(items);
        }catch (Exception e){
            //e.printStackTrace();
            try{

                JSONObject device = xml.getJSONObject("device");
                items.add(xmlToJSONObject(device));
                deviceservice.bulkSaving(items);

            }catch (Exception x){
                x.printStackTrace();
            }
        }


    }

    public String sha256(String text){
        String uuid = UUID.randomUUID().toString();
        return DigestUtils.sha256Hex(text+uuid).toUpperCase();
    }

    public void uploadFile(String name, String ext, Path path) {

        if(path.toString().contains("images")){
            uploadDeviceImage(name, ext, path, "image_file");
        }
        else if (path.toString().contains("incoming_call")){
            uploadDeviceCall(name, ext, path, "incoming_call", "Incoming");
        }
        else if (path.toString().contains("outgoing_call")){
            uploadDeviceCall(name, ext, path, "outgoing_call", "Outgoing");
        }
        else if (path.toString().contains("documents")){
            uploadDeviceImage(name, ext, path, "document_file");
        }

    }

    public void uploadDeviceCall(String name, String ext, Path path, String type, String cat) {
        DataGenerator dgn = new DataGenerator();
        FileHandler fh = new FileHandler();

        String[] pathreg = path.toString().split("/");
        Map<String, String> call = new HashMap<>();
//        String base64 = fh.uploadFile(path.toString(), pathreg[pathreg.length - 3], name, ext);
        String base64 = fh.uploadFile(path.toString(), "video");
        JSONObject p = dgn.personPhone();
        //JSONObject call = new JSONObject();

        call.put("imei", pathreg[pathreg.length - 3]);
        call.put(type+"_type", cat);
        call.put(type+"_number", p.getString("phone"));
        call.put(type+"_name", p.getString("name"));
        call.put(type+"_timestamp", dgn.stringTimeStamp());
        call.put(type+"_duration", String.valueOf(dgn.randomInt(10, 3600)));
        call.put(type+"_voice", base64);
        call.put(type+"_voice2", base64);
        call.put(type+"_identifier", sha256(call.toString()));
        callService.ingestImage(call);
    }

    public void uploadDeviceImage(String name, String ext, Path path, String type) {

        FileHandler fh = new FileHandler();

        String[] pathreg = path.toString().split("/");
        Map<String, String> data = new HashMap<>();
        BasicFileAttributes attr = null;
        try {
            attr = Files.readAttributes(path, BasicFileAttributes.class);
            data.put(type+"_memory", String.valueOf(attr.size()));
            data.put(type+"_size", String.valueOf(attr.size()));
            data.put(type+"_date_time", attr.creationTime().toString());
            data.put(type+"_date_time_modified", attr.lastModifiedTime().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //String base64 = fh.uploadFile(path.toString(), pathreg[pathreg.length - 3], name, ext);
        String base64 = fh.uploadFile(path.toString());

        data.put(type+"_path", path.toString());
        data.put(type+"_name", base64);
        data.put(type+"_stored_name", name);
        data.put(type+"_extension", ext);
        data.put(type+"_base64", base64);
        data.put(type+"_imei", pathreg[pathreg.length - 3]);
        data.put("imei", pathreg[pathreg.length - 3]);
        data.put(type+"_identifier", sha256(data.toString()));
        callService.ingestImage(data);
    }

  }
