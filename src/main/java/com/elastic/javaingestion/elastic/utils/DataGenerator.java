package com.elastic.javaingestion.elastic.utils;
import com.elastic.javaingestion.elastic.models.DeviceDocument;
import com.elastic.javaingestion.elastic.models.*;
import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataGenerator {
    private final DeviceDocument device;
    String[] types = {"cell", "hdd"};
    String[] revisions = {"8.1.0 O11019 BCDEFH-200224V237"};
    String[] connection_types = {"USB Cable", "GSM", "CDMA", "LTE", "WiMAX"};
    String[] contact_memory = {"Phone", "SIM", "Email"};
    String[] contact_designation = {"Mobile", "Home", "Work", "Other"};
    String[] email_providers = {"gmail.com", "ymail.com", "hotmail.com", "yahoo.co.uk", "yahoo.com"};
    String[] network_providers = {"9mobile APN", "MTN NG", "GLO", "Airtel"};
    Faker faker = new Faker();
    List<DeviceDocument> docs = new ArrayList<>() {};

    public DataGenerator(){
        this.device = new DeviceDocument();
        this.newDevice();
    }

    public DeviceDocument getDevice(){
        return device;
    }


    public List<DeviceDocument> getDocs(){
        return docs;
    }

    public JSONObject selectedDevices(){
        JSONParser parser = new JSONParser();
        try {
//            resources/static/selected-devices.json
            JSONArray data = (JSONArray) parser.parse(
                    new FileReader("src/main/resources/static/selected-devices.json"));//path to the JSON file.
            return (JSONObject) data.get(randomInt(0, data.size() - 1));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    private int randomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    private long randomLong(long min) {
        Random r = new Random();
        return Math.abs(r.nextLong() + min);
    }

    public DateTime _datetime(){
        Random r = new Random();
        long t1 = System.currentTimeMillis() + r.nextInt();
        long t2 = t1 + 2 * 60 * 1000 + r.nextInt(60 * 1000) + 1;
        return new DateTime(t2);
    }

    public Long _datetimemilli(){
        Random r = new Random();
        long t1 = System.currentTimeMillis() + r.nextInt();
        long t2 = t1 + 2 * 60 * 1000 + r.nextInt(60 * 1000) + 1;
        return t2;
    }

    public DateTime timeStamp(){
        return new DateTime(System.currentTimeMillis());
    }

    public String getFromList(String[] options){
        return (options.length > 0) ? options[randomInt(0, options.length - 1)] : "";
    }

    public String randomPhoneNumber(){
        return  String.format(getFromList(new String[]{"080","081", "070", "090", "070", "060"})+"%1d%03d%04d",
                (int) Math.floor(9*Math.random() + 1),
                (int) Math.floor(999*Math.random()),
                (int) Math.floor(9999*Math.random()));
    }

    public String randomString(int length, String prefix){
        String n = "";
        int l = length - prefix.length();
        for (int i = 0; i < l; i++) {
            n = n+"1";
        }
        long x = Long.parseLong(n);
        String s = String.valueOf(randomLong(x));
        return prefix + String.format("%."+ l +"s", s);
    }

    public String sha256(String text){
        String uuid = UUID.randomUUID().toString();
        return DigestUtils.sha256Hex(text+uuid).toUpperCase();
    }

    public Void newDevice(){

        device.setReport_type(getFromList(types));
        try{
            JSONObject d = selectedDevices();
            device.setSelected_manufacture((String) d.get("name"));
            device.setSelected_model((String) d.get("model"));
            device.setDetected_manufacture((String) d.get("name"));
        }catch (Exception e){
            e.printStackTrace();
        }
        device.setRevision(getFromList(revisions));
        device.setImei(randomString(15, "359"));
        device.setIccid(randomString(19, "89234"));
        device.setImsi(randomString(15, "621"));
        device.setAdvertisingid(UUID.randomUUID().toString().toUpperCase());
        device.setStart_date_time(_datetimemilli());
        device.setEnd_date_time(_datetimemilli());
        device.setPhone_date_time(_datetimemilli());
        device.setUsingclient(0);
        device.setTimestamp(_datetime().toString());
        Map<String, String> ufed = new HashMap();
        ufed.put("xml_format","1.0.2.9");
        ufed.put("software","7.44.0.80 UFED");
        ufed.put("serial","7213602");
        device.setUfed_version(ufed);
        return null;
    }


    public List<Contact> generateContacts(int count){
        List<Contact> list = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            Contact d = new Contact();
            d.setReport_type(device.getReport_type());
            d.setSelected_manufacture(device.getSelected_manufacture());
            d.setSelected_model(device.getSelected_model());
            d.setDetected_manufacture(device.getDetected_manufacture());
            d.setRevision(device.getRevision());
            d.setImei(device.getImei());
            d.setIccid(device.getIccid());
            d.setImsi(device.getImsi());
            d.setAdvertisingid(device.getAdvertisingid());
            d.setStart_date_time(device.getStart_date_time());
            d.setEnd_date_time(device.getEnd_date_time());
            d.setPhone_date_time(device.getPhone_date_time());
            d.setUsingclient(device.getUsingclient());
            d.setTimestamp(device.getTimestamp());
            d.setUfed_version(device.getUfed_version());

            d.setContact_name(faker.name().fullName());
            Map<String, String> c = new HashMap<>();
            c.put("designation", getFromList(contact_designation));
            c.put("value", randomPhoneNumber());
            d.setContact_phone_number(c);
            d.setContact_memory(getFromList(contact_memory));
            d.setContact_sha256(sha256(d.toString()));

            list.add(d);
        }
        return list;
    }


    public List<SMSMessage> generateSMSMessages(int count){
        List<SMSMessage> list = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            SMSMessage d = new SMSMessage();
            d.setReport_type(device.getReport_type());
            d.setSelected_manufacture(device.getSelected_manufacture());
            d.setSelected_model(device.getSelected_model());
            d.setDetected_manufacture(device.getDetected_manufacture());
            d.setRevision(device.getRevision());
            d.setImei(device.getImei());
            d.setIccid(device.getIccid());
            d.setImsi(device.getImsi());
            d.setAdvertisingid(device.getAdvertisingid());
            d.setStart_date_time(device.getStart_date_time());
            d.setEnd_date_time(device.getEnd_date_time());
            d.setPhone_date_time(device.getPhone_date_time());
            d.setUsingclient(device.getUsingclient());
            d.setTimestamp(device.getTimestamp());
            d.setUfed_version(device.getUfed_version());

            d.setSms_message_number(randomPhoneNumber());
            d.setSms_message_name(getFromList(network_providers));
            d.setSms_message_timestamp(stringTimeStamp());
            d.setSms_message_folder(getFromList(new String[]{"Inbox", "Outbox", "Sent"}));
            d.setSms_message_status(getFromList(new String[]{"Read", "Unread"}));
            d.setSms_message_storage(getFromList(new String[]{"Phone", "Memory"}));
            d.setSms_message_type(getFromList(new String[]{"Incoming", "Outgoing"}));
            d.setSms_message_text(faker.lorem().sentence());
            d.setSms_message_smsc(randomPhoneNumber());
            d.setSms_message_sha256(sha256(d.toString()));
            list.add(d);
        }
        return list;
    }

    public List<ImageFile> generateImageFiles(int count){
        List<ImageFile> list = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            ImageFile d = new ImageFile();
            d.setReport_type(device.getReport_type());
            d.setSelected_manufacture(device.getSelected_manufacture());
            d.setSelected_model(device.getSelected_model());
            d.setDetected_manufacture(device.getDetected_manufacture());
            d.setRevision(device.getRevision());
            d.setImei(device.getImei());
            d.setIccid(device.getIccid());
            d.setImsi(device.getImsi());
            d.setAdvertisingid(device.getAdvertisingid());
            d.setStart_date_time(device.getStart_date_time());
            d.setEnd_date_time(device.getEnd_date_time());
            d.setPhone_date_time(device.getPhone_date_time());
            d.setUsingclient(device.getUsingclient());
            d.setTimestamp(device.getTimestamp());
            d.setUfed_version(device.getUfed_version());

            String filename = faker.file().fileName(null, null, getFromList(new String[]{"png", "jpg", "jpeg", "gif"}), null);
            d.setImage_name(filename);
            d.setImage_stored_name(filename);
            d.setImage_thumb_location("Images/Thumbnail/"+filename);
            d.setImage_path("Images/Pictures/"+filename);
            d.setImage_memory(getFromList(new String[]{"Phone", "Memory"}));
            d.setImage_size(randomInt(128, 4096));
            d.setImage_date_time(stringTimeStamp());
            d.setImage_date_time_modified(stringTimeStamp());
            d.setImage_sha256(sha256(d.toString()));
            list.add(d);
        }
        return list;
    }

    public List<DocumentFile> generateDocumentFiles(int count){
        List<DocumentFile> list = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            DocumentFile d = new DocumentFile();
            d.setReport_type(device.getReport_type());
            d.setSelected_manufacture(device.getSelected_manufacture());
            d.setSelected_model(device.getSelected_model());
            d.setDetected_manufacture(device.getDetected_manufacture());
            d.setRevision(device.getRevision());
            d.setImei(device.getImei());
            d.setIccid(device.getIccid());
            d.setImsi(device.getImsi());
            d.setAdvertisingid(device.getAdvertisingid());
            d.setStart_date_time(device.getStart_date_time());
            d.setEnd_date_time(device.getEnd_date_time());
            d.setPhone_date_time(device.getPhone_date_time());
            d.setUsingclient(device.getUsingclient());
            d.setTimestamp(device.getTimestamp());
            d.setUfed_version(device.getUfed_version());

            String filename = faker.file().fileName(null, null, getFromList(new String[]{"docx", "xlsx", "pdf", "pptx"}), null);
            d.setDocument_name(filename);
            d.setDocument_stored_name(filename);
            d.setDocument_path("Files/Documents/"+filename);
            d.setDocument_memory(getFromList(new String[]{"Phone", "Memory"}));
            d.setDocument_size(randomInt(128, 4096));
            d.setDocument_date_time(stringTimeStamp());
            d.setDocument_date_time_modified(stringTimeStamp());
            d.setDocument_sha256(sha256(d.toString()));
            list.add(d);
        }
        return list;
    }


    public List<IncomingCall> generateIncomingCalls(int count){
        List<IncomingCall> list = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            IncomingCall d = new IncomingCall();
            d.setReport_type(device.getReport_type());
            d.setSelected_manufacture(device.getSelected_manufacture());
            d.setSelected_model(device.getSelected_model());
            d.setDetected_manufacture(device.getDetected_manufacture());
            d.setRevision(device.getRevision());
            d.setImei(device.getImei());
            d.setIccid(device.getIccid());
            d.setImsi(device.getImsi());
            d.setAdvertisingid(device.getAdvertisingid());
            d.setStart_date_time(device.getStart_date_time());
            d.setEnd_date_time(device.getEnd_date_time());
            d.setPhone_date_time(device.getPhone_date_time());
            d.setUsingclient(device.getUsingclient());
            d.setTimestamp(device.getTimestamp());
            d.setUfed_version(device.getUfed_version());

            d.setIncoming_call_type("Incoming");
            d.setIncoming_call_number(randomPhoneNumber());
            d.setIncoming_call_name(faker.name().fullName());
            d.setIncoming_call_timestamp(stringTimeStamp());
            d.setIncoming_call_duration(randomInt(10, 3600));
            d.setIncoming_call_sha256(sha256(d.toString()));
            list.add(d);
        }
        return list;
    }


    public List<OutgoingCall> generateOutgoingCalls(int count){
        List<OutgoingCall> list = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            OutgoingCall d = new OutgoingCall();
            d.setReport_type(device.getReport_type());
            d.setSelected_manufacture(device.getSelected_manufacture());
            d.setSelected_model(device.getSelected_model());
            d.setDetected_manufacture(device.getDetected_manufacture());
            d.setRevision(device.getRevision());
            d.setImei(device.getImei());
            d.setIccid(device.getIccid());
            d.setImsi(device.getImsi());
            d.setAdvertisingid(device.getAdvertisingid());
            d.setStart_date_time(device.getStart_date_time());
            d.setEnd_date_time(device.getEnd_date_time());
            d.setPhone_date_time(device.getPhone_date_time());
            d.setUsingclient(device.getUsingclient());
            d.setTimestamp(device.getTimestamp());
            d.setUfed_version(device.getUfed_version());

            d.setOutgoing_call_type("Outgoing");
            d.setOutgoing_call_number(randomPhoneNumber());
            d.setOutgoing_call_name(faker.name().fullName());
            d.setOutgoing_call_timestamp(stringTimeStamp());
            d.setOutgoing_call_duration(randomInt(10, 3600));
            d.setOutgoing_call_sha256(sha256(d.toString()));
            list.add(d);
        }
        return list;
    }

    public List<MissedCall> generateMissedCalls(int count){
        List<MissedCall> list = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            MissedCall d = new MissedCall();
            d.setReport_type(device.getReport_type());
            d.setSelected_manufacture(device.getSelected_manufacture());
            d.setSelected_model(device.getSelected_model());
            d.setDetected_manufacture(device.getDetected_manufacture());
            d.setRevision(device.getRevision());
            d.setImei(device.getImei());
            d.setIccid(device.getIccid());
            d.setImsi(device.getImsi());
            d.setAdvertisingid(device.getAdvertisingid());
            d.setStart_date_time(device.getStart_date_time());
            d.setEnd_date_time(device.getEnd_date_time());
            d.setPhone_date_time(device.getPhone_date_time());
            d.setUsingclient(device.getUsingclient());
            d.setTimestamp(device.getTimestamp());
            d.setUfed_version(device.getUfed_version());

            d.setMissed_call_type("Missed");
            d.setMissed_call_number(randomPhoneNumber());
            d.setMissed_call_name(faker.name().fullName());
            d.setMissed_call_timestamp(stringTimeStamp());
            d.setMissed_call_sha256(sha256(d.toString()));
            list.add(d);
        }
        return list;
    }

    public String stringTimeStamp(){
        return _datetime().toString();
    }



}
