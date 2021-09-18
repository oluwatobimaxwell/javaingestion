package com.elastic.javaingestion.elastic.services.device;

import org.apache.commons.codec.digest.DigestUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class ContentBuilder {

    public String sha256(String text){
        String uuid = UUID.randomUUID().toString();
        return DigestUtils.sha256Hex(text+uuid).toUpperCase();
    }

    public XContentBuilder documentfile(JSONObject obj, JSONObject device){
        try{
            XContentBuilder builder =  this.generalInformation(device);
            builder.field("document_file_name", obj.get("name").toString())
                    .field("document_file_stored_name", obj.get("stored_name").toString())
                    .field("document_file_path", obj.get("path"))
                    .field("document_file_memory", obj.get("memory"))
                    .field("document_file_size", obj.get("size"))
                    .field("document_file_date_time", obj.get("date_time"))
                    .field("document_file_date_time_modified", obj.get("date_time_modified"))
                    .field("document_file_sha256", obj.get("sha256"))
                    .field("document_file_identifier", sha256(obj + device.toString()));

            try{
                builder.field("document_file_base64", obj.get("base64").toString());
            }catch (Exception e){
                builder.field("document_file_base64", "ulngwszw6sxjlkqhvei7.pdf");
            }

            try{
                builder.field("document_file_extension", obj.get("extension").toString());
            }catch (Exception e){
                builder.field("document_file_extension", "pdf");
            }

            builder.endObject();
            return builder;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public XContentBuilder imagefile(JSONObject obj, JSONObject device){
        try{
            XContentBuilder builder =  this.generalInformation(device);
            builder.field("image_file_name", obj.get("name").toString())
                    .field("image_file_stored_name", obj.get("stored_name").toString())
                    .field("image_file_thumb_location", obj.get("thumb_location"))
                    .field("image_file_path", obj.get("path"))
                    .field("image_file_memory", obj.get("memory"))
                    .field("image_file_size", obj.get("size"))
                    .field("image_file_date_time", obj.get("date_time"))
                    .field("image_file_date_time_modified", obj.get("date_time_modified"))
                    .field("image_file_sha256", obj.get("sha256"))
                    .field("image_file_meta", obj.get("meta").toString())
                    .field("image_file_identifier", sha256(obj + device.toString()));

                    try{
                        builder.field("image_file_base64", obj.get("base64").toString());
                    }catch (Exception e){
                        System.out.println("Add Image Base 64 - Default");
                        builder.field("image_file_base64", "77AF55EF0E3A1953D63CA16126830A9073009B32E17E9FBFFD63C18D64525ABF.jpg");
                    }

                    try{
                        builder.field("image_file_extension", obj.get("extension").toString());
                    }catch (Exception e){
                        builder.field("image_file_extension", "jpg");
                    }

            builder.endObject();

            return builder;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public XContentBuilder sms_message(JSONObject obj, JSONObject device){
        try{
            XContentBuilder builder =  this.generalInformation(device);
            return builder.field("sms_message_number", obj.get("number").toString())
                    .field("sms_message_name", obj.get("name").toString())
                    .field("sms_message_timestamp", obj.get("timestamp"))
                    .field("sms_message_status", obj.get("status"))
                    .field("sms_message_folder", obj.get("folder"))
                    .field("sms_message_storage", obj.get("storage"))
                    .field("sms_message_type", obj.get("type"))
                    .field("sms_message_text", obj.get("text"))
                    .field("sms_message_smsc", obj.get("smsc"))
                    .field("sms_message_identifier", sha256(obj + device.toString()))
                    .endObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public XContentBuilder phonecall(JSONObject obj, JSONObject device, String label){
        try{
            XContentBuilder builder =  this.generalInformation(device);
            builder.field(label+"type", obj.get("type"))
                    .field(label+"number", obj.get("number").toString())
                    .field(label+"name", obj.get("name").toString())
                    .field(label+"timestamp", obj.get("timestamp"))
                    .field(label+"identifier", sha256(obj + device.toString()));
            try{
                builder.field(label+"duration", obj.get("duration"));
            }catch (Exception e){

            }

            try{
                builder.field(label+"voice", obj.get("voice"));
            }catch (Exception e){

            }

            try{
                builder.field(label+"voice2", obj.get("voice2"));
            }catch (Exception e){

            }

            builder.endObject();
            return builder;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public XContentBuilder contact(JSONObject obj, JSONObject device){
        try{
            Map<String, String> pn = new HashMap<>();
            pn.put("designation", obj.getJSONObject("phone_number").get("designation").toString());
            pn.put("value", obj.getJSONObject("phone_number").get("value").toString());
            XContentBuilder builder =  this.generalInformation(device)
                    .field("contact_name", obj.get("name"))
                    .field("contact_memory", obj.get("memory"))
                    .field("contact_phone_number", pn)
                    .field("contact_identifier", sha256(obj + device.toString()));
            try{
                builder.field("contact_extra_field", obj.get("extra_field").toString());
            }catch (Exception e){
                System.out.println("No extra field - Contact");
            }
            builder.endObject();
            return builder;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public XContentBuilder generalInformation(JSONObject obj){
        try {
            return XContentFactory.jsonBuilder()
                    .startObject()
                    .field("report_type", obj.get("report_type").toString())
                    .field("selected_manufacture", obj.get("selected_manufacture").toString())
                    .field("selected_model", obj.get("selected_model").toString())
                    .field("detected_manufacture", obj.get("detected_manufacture").toString())

                    .field("detected_model", obj.get("detected_model").toString())
                    .field("revision", obj.get("revision").toString())

                    .field("imei", obj.get("imei").toString())
                    .field("iccid", obj.get("iccid").toString())
                    .field("imsi", obj.get("imsi").toString())
                    .field("advertisingid", obj.get("advertisingid").toString())
                    .field("start_date_time", obj.get("start_date_time").toString())
                    .field("end_date_time", obj.get("end_date_time").toString())

                    .field("phone_date_time", obj.get("phone_date_time").toString())
                    .field("connection_type", obj.get("connection_type").toString())
                    .field("ufed_version", obj.get("ufed_version").toString())
                    .field("usingclient", obj.get("usingclient"))
                    .field("identifier",  sha256(obj.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
