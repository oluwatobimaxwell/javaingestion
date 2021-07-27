package com.elastic.javaingestion.elastic.models;


import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Map;

import static com.elastic.javaingestion.elastic.utils.Helper.DEVICE_INDEX;

@Document(indexName = DEVICE_INDEX)
@Setting(settingPath = "static/settings.json")
public class Contact extends DeviceDocument {

    @Field(type = FieldType.Text)
    private String contact_name;
    @Field(type = FieldType.Text)
    private String contact_memory;
    @Field(type = FieldType.Object)
    private Map<String, String> contact_phone_number;
    @Field(type = FieldType.Object)
    private Map<String, String> contact_extra_field;
    @Field(type = FieldType.Text)
    private String contact_sha256;

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_memory() {
        return contact_memory;
    }

    public void setContact_memory(String contact_memory) {
        this.contact_memory = contact_memory;
    }

    public Map<String, String> getContact_phone_number() {
        return contact_phone_number;
    }

    public void setContact_phone_number(Map<String, String> contact_phone_number) {
        this.contact_phone_number = contact_phone_number;
    }

    public Map<String, String> getContact_extra_field() {
        return contact_extra_field;
    }

    public void setContact_extra_field(Map<String, String> contact_extra_field) {
        this.contact_extra_field = contact_extra_field;
    }

    public String getContact_sha256() {
        return contact_sha256;
    }

    public void setContact_sha256(String contact_sha256) {
        this.contact_sha256 = contact_sha256;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contact_name='" + contact_name + '\'' +
                ", contact_memory='" + contact_memory + '\'' +
                ", contact_phone_number=" + contact_phone_number +
                ", contact_extra_field=" + contact_extra_field +
                ", contact_sha256='" + contact_sha256 + '\'' +
                '}';
    }
}
