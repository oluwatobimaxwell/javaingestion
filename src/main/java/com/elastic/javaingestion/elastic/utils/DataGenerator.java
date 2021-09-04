package com.elastic.javaingestion.elastic.utils;
import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.StringUtils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DataGenerator {
    String[] types = {"cell", "hdd"};
    String[] revisions = {"8.1.0 O11019 BCDEFH-200224V237"};
    String[] connection_types = {"USB Cable", "GSM", "CDMA", "LTE", "WiMAX"};
    String[] contact_memory = {"Phone", "SIM", "Email"};
    String[] contact_designation = {"Mobile", "Home", "Work", "Other"};
    String[] email_providers = {"gmail.com", "ymail.com", "hotmail.com", "yahoo.co.uk", "yahoo.com"};
    String[] network_providers = {"9mobile APN", "MTN NG", "GLO", "Airtel"};
    Faker faker = new Faker();


    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String[] getRevisions() {
        return revisions;
    }

    public void setRevisions(String[] revisions) {
        this.revisions = revisions;
    }

    public String[] getConnection_types() {
        return connection_types;
    }

    public void setConnection_types(String[] connection_types) {
        this.connection_types = connection_types;
    }

    public String[] getContact_memory() {
        return contact_memory;
    }

    public void setContact_memory(String[] contact_memory) {
        this.contact_memory = contact_memory;
    }

    public String[] getContact_designation() {
        return contact_designation;
    }

    public void setContact_designation(String[] contact_designation) {
        this.contact_designation = contact_designation;
    }

    public String[] getEmail_providers() {
        return email_providers;
    }

    public void setEmail_providers(String[] email_providers) {
        this.email_providers = email_providers;
    }

    public String[] getNetwork_providers() {
        return network_providers;
    }

    public void setNetwork_providers(String[] network_providers) {
        this.network_providers = network_providers;
    }

    public Faker getFaker() {
        return faker;
    }

    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    public JSONObject selectedDevices(){
        try {
            Path pth = Paths.get("src/main/resources/static/selected-devices.json");
            BufferedReader reader = Files.newBufferedReader(pth);
            StringBuilder str = new StringBuilder();
            reader.lines().forEach(line -> { str.append(line); });
            System.out.println("JSON READER: "+str);
            JSONArray data = new JSONArray(str.toString());
            return (JSONObject) data.get(randomInt(0, data.length() - 1));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public int randomInt(int min, int max) {
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

    public String stringTimeStamp(){
        return _datetime().toString();
    }



}
