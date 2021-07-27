package com.elastic.javaingestion.elastic.models;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import static com.elastic.javaingestion.elastic.utils.Helper.DEVICE_INDEX;

@Document(indexName = DEVICE_INDEX)
@Setting(settingPath = "static/settings.json")
public class ImageFile extends DeviceDocument {

    @Field(type = FieldType.Text)
    private String image_name;
    @Field(type = FieldType.Text)
    private String image_stored_name;
    @Field(type = FieldType.Text)
    private String image_thumb_location;
    @Field(type = FieldType.Text)
    private String image_path;
    @Field(type = FieldType.Text)
    private String image_memory;
    @Field(type = FieldType.Text)
    private Integer image_size;
    @Field(type = FieldType.Text)
    private String image_date_time;
    @Field(type = FieldType.Text)
    private String image_date_time_modified;
    @Field(type = FieldType.Text)
    private String image_sha256;
    @Field(type = FieldType.Text)
    private String image_meta;

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_stored_name() {
        return image_stored_name;
    }

    public void setImage_stored_name(String image_stored_name) {
        this.image_stored_name = image_stored_name;
    }

    public String getImage_thumb_location() {
        return image_thumb_location;
    }

    public void setImage_thumb_location(String image_thumb_location) {
        this.image_thumb_location = image_thumb_location;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_memory() {
        return image_memory;
    }

    public void setImage_memory(String image_memory) {
        this.image_memory = image_memory;
    }

    public Integer getImage_size() {
        return image_size;
    }

    public void setImage_size(Integer image_size) {
        this.image_size = image_size;
    }

    public String getImage_date_time() {
        return image_date_time;
    }

    public void setImage_date_time(String image_date_time) {
        this.image_date_time = image_date_time;
    }

    public String getImage_date_time_modified() {
        return image_date_time_modified;
    }

    public void setImage_date_time_modified(String image_date_time_modified) {
        this.image_date_time_modified = image_date_time_modified;
    }

    public String getImage_sha256() {
        return image_sha256;
    }

    public void setImage_sha256(String image_sha256) {
        this.image_sha256 = image_sha256;
    }

    public String getImage_meta() {
        return image_meta;
    }

    public void setImage_meta(String image_meta) {
        this.image_meta = image_meta;
    }

    @Override
    public String toString() {
        return "ImageFile{" +
                "image_name='" + image_name + '\'' +
                ", image_stored_name='" + image_stored_name + '\'' +
                ", image_thumb_location='" + image_thumb_location + '\'' +
                ", image_path='" + image_path + '\'' +
                ", image_memory='" + image_memory + '\'' +
                ", image_size=" + image_size +
                ", image_date_time=" + image_date_time +
                ", image_date_time_modified=" + image_date_time_modified +
                ", image_sha256='" + image_sha256 + '\'' +
                ", image_meta='" + image_meta + '\'' +
                '}';
    }
}
