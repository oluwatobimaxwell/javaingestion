package com.elastic.javaingestion.elastic.utils;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileHandler {

    public String loadFile(String path) {
        try {
            File file = new File(path);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + path, e);
        }
    }

    public String sha256(String text){
        String uuid = UUID.randomUUID().toString();
        return DigestUtils.sha256Hex(text+uuid).toUpperCase();
    }

    public String uploadFile(String path, String device, String name, String ext){
        try {
            //String uploadDir = "/Applications/XAMPP/xamppfiles/htdocs/projects/elasticsearch/uploads";
            String uploadDir = "/var/www/html/uploads";
            File source = new File(path);
            String encode = sha256(device + name);
            String file_name = device+"_"+encode+"."+ext;
            File dest = new File(uploadDir+"/"+file_name);
            FileCopyUtils.copy(source, dest);
            dest.setReadable(true, false);
            dest.setExecutable(true, false);
            dest.setWritable(true, false);
            return file_name;
        } catch (IOException e) {
            System.out.println("FILE COPY ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public Cloudinary cloudinaryConfig() {
        String cloudName = "dzghxcq0j";
        String apiKey = "722854184156811";
        String apiSecret = "73r-yC7TYkYPom0cTfSuKbCxtAQ";
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

    public String uploadFile(String path) {
        try {
            File uploadedFile = new File(path);

            //Map<String, String> options = new HashMap<>();
            //options.put("allowed_formats", "mp4,ogv,jpg,png,pdf");

            Map uploadResult = cloudinaryConfig()
                    .uploader()
                    .upload(uploadedFile, ObjectUtils.emptyMap());
            System.out.println(uploadResult);
            //return  uploadResult.get("url").toString();
            return uploadResult.get("public_id").toString()+"."+uploadResult.get("format").toString();
        } catch (Exception e) {
            System.out.println("Cloud Error: "+e.getMessage());
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "77AF55EF0E3A1953D63CA16126830A9073009B32E17E9FBFFD63C18D64525ABF.jpg";
    }

    public String uploadFile(String path, String media){
        try {
            File uploadedFile = new File(path);
            Map<String, String> options = new HashMap<>();
            options.put("resource_type", media);


            Map uploadResult = cloudinaryConfig()
                    .uploader()
                    .uploadLarge(uploadedFile, options);
            System.out.println(uploadResult);
            //return  uploadResult.get("url").toString();
            return uploadResult.get("public_id").toString()+"."+uploadResult.get("format").toString();
        } catch (Exception e) {
            System.out.println("Cloud Error: "+e.getMessage());
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return media.equals("video") ? "2105161502000032760_236154426.mp3" : "77AF55EF0E3A1953D63CA16126830A9073009B32E17E9FBFFD63C18D64525ABF.jpg";
    }





}
