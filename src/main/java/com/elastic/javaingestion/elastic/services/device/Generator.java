package com.elastic.javaingestion.elastic.services.device;

import com.elastic.javaingestion.elastic.utils.DataGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Generator extends DataGenerator {

    JSONObject generateDevice(){
        JSONObject item = new JSONObject();
        JSONObject d = this.selectedDevices();
        item.put("report_type", this.getFromList(this.getTypes()));
        item.put("selected_manufacture", d.getString("name"));
        item.put("selected_model", d.getString("model"));
        item.put("detected_manufacture", d.getString("name"));
        item.put("detected_model", d.getString("model"));
        item.put("revision", this.getFromList(this.getRevisions()));
        item.put("imei", this.randomString(15, "359"));
        item.put("iccid", this.randomString(19, "89234"));
        item.put("imsi", this.randomString(15, "621"));
        item.put("advertisingid", UUID.randomUUID().toString().toUpperCase());
        item.put("start_date_time", this._datetimemilli());
        item.put("end_date_time", this._datetimemilli());
        item.put("phone_date_time", this._datetimemilli());
        item.put("connection_type", "");
        item.put("ufed_version", this.getFromList(getConnection_types()));
        item.put("usingclient", this.randomInt(0, 9));
        return item;
    }

    JSONObject generateSMS(String type){
        JSONObject sms = new JSONObject();
        sms.put("number", randomPhoneNumber());
        sms.put("timestamp", stringTimeStamp());
        sms.put("status", getFromList(new String[]{"Read", "Unread"}));
        sms.put("folder", getFromList(new String[]{"Inbox", "Outbox", "Sent"}));
        sms.put("storage", getFromList(new String[]{"Phone", "Memory"}));
        sms.put("type", getFromList(new String[]{"Incoming", "Outgoing"}));
        sms.put("text", getFaker().lorem().sentence());
        sms.put("smsc", randomPhoneNumber());
        sms.put("name", getFromList(getNetwork_providers()));
        return sms;
    }

    JSONObject generateImage(String type){
        JSONObject img = new JSONObject();
        String filename = getFaker().file().fileName(null, null, getFromList(new String[]{"png", "jpg", "jpeg", "gif"}), null);
        img.put("name", filename);
        img.put("stored_name", filename);
        img.put("thumb_location", "Images/Thumbnail/"+filename);
        img.put("path", "Images/Pictures/"+filename);
        img.put("memory", getFromList(new String[]{"Phone", "Memory"}));
        img.put("size", randomInt(128, 4096));
        img.put("date_time", stringTimeStamp());
        img.put("date_time_modified", stringTimeStamp());
        img.put("sha256", sha256(filename+stringTimeStamp()));
        img.put("meta", "");
        return img;
    }

    JSONObject generateDocument(String type){
        JSONObject img = new JSONObject();
        String filename = getFaker().file().fileName(null, null, getFromList(new String[]{"docx", "xlsx", "pdf", "pptx"}), null);
        img.put("name", filename);
        img.put("stored_name", filename);
        img.put("path", "Files/Documents/"+filename);
        img.put("memory", getFromList(new String[]{"Phone", "Memory"}));
        img.put("size", randomInt(128, 4096));
        img.put("date_time", stringTimeStamp());
        img.put("date_time_modified", stringTimeStamp());
        img.put("sha256", sha256(filename+stringTimeStamp()));
        return img;
    }

    JSONObject generateCall(String type){

        JSONObject call = new JSONObject();
        call.put("type", type);
        call.put("number", randomPhoneNumber());
        call.put("name", getFaker().name().fullName());
        call.put("timestamp", stringTimeStamp());
        call.put("duration", String.valueOf(randomInt(10, 3600)));
        return call;
    }

    JSONObject generateContact(String type){
        JSONObject d = new JSONObject();
        d.put("name", getFaker().name().fullName());
        d.put("memory", getFromList(getContact_memory()));
        Map<String, String> c = new HashMap<>();
        c.put("designation", getFromList(getContact_designation()));
        c.put("value", randomPhoneNumber());
        d.put("phone_number", c);
        return d;
    }

    JSONArray getItems(String name, String params){
        JSONArray items = new JSONArray();
        for (int i = 0; i < randomInt(5, 10); i++){
            JSONObject obj = null;
            switch (name){
                case "contact":
                    obj = generateContact(params);
                    break;

                case "sms":
                    obj = generateSMS(params);
                    break;

                case "image":
                    obj = generateImage(params);
                    break;

                case "document":
                    obj = generateDocument(params);
                    break;

                case "call":
                    obj = generateCall(params);
                    break;
            }

            items.put(obj);
        }
        return items;
    }

    List<JSONObject> run(){
        List<JSONObject> items = new ArrayList<>();
        //generate device

        for (int i = 0; i < randomInt(3, 10); i++){
            JSONObject item = new JSONObject();

            item.put("general_information", generateDevice());
            item.put("contacts", getItems("contact", null));
            item.put("sms_messages", getItems("sms", null));
            item.put("image_files", getItems("image", null));
            item.put("documents_files", getItems("document", null));
            item.put("incoming_calls", getItems("call", "Incoming"));
            item.put("outgoing_calls", getItems("call", "Outgoing"));
            item.put("missed_calls", getItems("call", "Missed"));

            items.add(item);
        }


        return items;
    }
}
