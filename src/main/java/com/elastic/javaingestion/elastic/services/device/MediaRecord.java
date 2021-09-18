package com.elastic.javaingestion.elastic.services.device;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "devices_test_img")
public class MediaRecord {
    @Id
    @Field(type = FieldType.Text)
    private String image_file_memory;
    @Field(type = FieldType.Text)
    private String image_file_size;
    @Field(type = FieldType.Date)
    private String image_file_date_time;
    @Field(type = FieldType.Date)
    private String image_file_date_time_modified;
    @Field(type = FieldType.Text)
    private String image_file_path;
    @Field(type = FieldType.Text)
    private String image_file_name;
    @Field(type = FieldType.Text)
    private String image_file_stored_name;
    @Field(type = FieldType.Text)
    private String image_file_extension;
    @Field(type = FieldType.Keyword)
    private String image_file_base64;
    @Field(type = FieldType.Text)
    private String image_file_imei;
    @Field(type = FieldType.Text)
    private String image_file_identifier;
    @Field(type = FieldType.Text)
    private String imei;

    @Override
    public String toString() {
        return "MediaRecord{" +
                "image_file_memory='" + image_file_memory + '\'' +
                ", image_file_size='" + image_file_size + '\'' +
                ", image_file_date_time='" + image_file_date_time + '\'' +
                ", image_file_date_time_modified='" + image_file_date_time_modified + '\'' +
                ", image_file_path='" + image_file_path + '\'' +
                ", image_file_name='" + image_file_name + '\'' +
                ", image_file_stored_name='" + image_file_stored_name + '\'' +
                ", image_file_extension='" + image_file_extension + '\'' +
                ", image_file_base64='" + image_file_base64 + '\'' +
                ", image_file_imei='" + image_file_imei + '\'' +
                ", image_file_identifier='" + image_file_identifier + '\'' +
                ", imei='" + imei + '\'' +
                '}';
    }

    public String getImage_file_memory() {
        return image_file_memory;
    }

    public void setImage_file_memory(String image_file_memory) {
        this.image_file_memory = image_file_memory;
    }

    public String getImage_file_size() {
        return image_file_size;
    }

    public void setImage_file_size(String image_file_size) {
        this.image_file_size = image_file_size;
    }

    public String getImage_file_date_time() {
        return image_file_date_time;
    }

    public void setImage_file_date_time(String image_file_date_time) {
        this.image_file_date_time = image_file_date_time;
    }

    public String getImage_file_date_time_modified() {
        return image_file_date_time_modified;
    }

    public void setImage_file_date_time_modified(String image_file_date_time_modified) {
        this.image_file_date_time_modified = image_file_date_time_modified;
    }

    public String getImage_file_path() {
        return image_file_path;
    }

    public void setImage_file_path(String image_file_path) {
        this.image_file_path = image_file_path;
    }

    public String getImage_file_name() {
        return image_file_name;
    }

    public void setImage_file_name(String image_file_name) {
        this.image_file_name = image_file_name;
    }

    public String getImage_file_stored_name() {
        return image_file_stored_name;
    }

    public void setImage_file_stored_name(String image_file_stored_name) {
        this.image_file_stored_name = image_file_stored_name;
    }

    public String getImage_file_extension() {
        return image_file_extension;
    }

    public void setImage_file_extension(String image_file_extension) {
        this.image_file_extension = image_file_extension;
    }

    public String getImage_file_base64() {
        return image_file_base64;
    }

    public void setImage_file_base64(String image_file_base64) {
        this.image_file_base64 = image_file_base64;
    }

    public String getImage_file_imei() {
        return image_file_imei;
    }

    public void setImage_file_imei(String image_file_imei) {
        this.image_file_imei = image_file_imei;
    }

    public String getImage_file_identifier() {
        return image_file_identifier;
    }

    public void setImage_file_identifier(String image_file_identifier) {
        this.image_file_identifier = image_file_identifier;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
