package com.elastic.javaingestion.elastic.models;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import static com.elastic.javaingestion.elastic.utils.Helper.DEVICE_INDEX;

@Document(indexName = DEVICE_INDEX)
@Setting(settingPath = "static/settings.json")
public class DocumentFile extends DeviceDocument {
    @Field(type = FieldType.Text)
    private String document_name;
    @Field(type = FieldType.Text)
    private String document_stored_name;
    @Field(type = FieldType.Text)
    private String  document_path;
    @Field(type = FieldType.Text)
    private String document_memory;
    @Field(type = FieldType.Text)
    private String document_sha256;
    @Field(type = FieldType.Text)
    private Integer document_size;
    @Field(type = FieldType.Date)
    private String document_date_time;
    @Field(type = FieldType.Date)
    private String document_date_time_modified;

    @Override
    public String toString() {
        return "DocumentFile{" +
                "document_name='" + document_name + '\'' +
                ", document_stored_name='" + document_stored_name + '\'' +
                ", document_path='" + document_path + '\'' +
                ", document_memory='" + document_memory + '\'' +
                ", document_sha256='" + document_sha256 + '\'' +
                ", document_size=" + document_size +
                ", document_date_time='" + document_date_time + '\'' +
                ", document_date_time_modified='" + document_date_time_modified + '\'' +
                '}';
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getDocument_stored_name() {
        return document_stored_name;
    }

    public void setDocument_stored_name(String document_stored_name) {
        this.document_stored_name = document_stored_name;
    }

    public String getDocument_path() {
        return document_path;
    }

    public void setDocument_path(String document_path) {
        this.document_path = document_path;
    }

    public String getDocument_memory() {
        return document_memory;
    }

    public void setDocument_memory(String document_memory) {
        this.document_memory = document_memory;
    }

    public String getDocument_sha256() {
        return document_sha256;
    }

    public void setDocument_sha256(String document_sha256) {
        this.document_sha256 = document_sha256;
    }

    public Integer getDocument_size() {
        return document_size;
    }

    public void setDocument_size(Integer document_size) {
        this.document_size = document_size;
    }

    public String getDocument_date_time() {
        return document_date_time;
    }

    public void setDocument_date_time(String document_date_time) {
        this.document_date_time = document_date_time;
    }

    public String getDocument_date_time_modified() {
        return document_date_time_modified;
    }

    public void setDocument_date_time_modified(String document_date_time_modified) {
        this.document_date_time_modified = document_date_time_modified;
    }
}
