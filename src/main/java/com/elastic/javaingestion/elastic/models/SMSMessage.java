package com.elastic.javaingestion.elastic.models;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import static com.elastic.javaingestion.elastic.utils.Helper.DEVICE_INDEX;


@Document(indexName = DEVICE_INDEX)
@Setting(settingPath = "static/settings.json")
public class SMSMessage extends DeviceDocument {
    @Field(type = FieldType.Keyword)
    private String sms_message_number;
    @Field(type = FieldType.Text)
    private String sms_message_name;
    @Field(type = FieldType.Date)
    private String sms_message_timestamp;
    @Field(type = FieldType.Text)
    private String sms_message_status;
    @Field(type = FieldType.Text)
    private String sms_message_folder;
    @Field(type = FieldType.Text)
    private String sms_message_storage;
    @Field(type = FieldType.Text)
    private String sms_message_type;
    @Field(type = FieldType.Text)
    private String sms_message_text;
    @Field(type = FieldType.Keyword)
    private String sms_message_smsc;
    @Field(type = FieldType.Text)
    private String sms_message_sha256;

    public String getSms_message_number() {
        return sms_message_number;
    }

    public void setSms_message_number(String sms_message_number) {
        this.sms_message_number = sms_message_number;
    }

    public String getSms_message_name() {
        return sms_message_name;
    }

    public void setSms_message_name(String sms_message_name) {
        this.sms_message_name = sms_message_name;
    }

    public String getSms_message_timestamp() {
        return sms_message_timestamp;
    }

    public void setSms_message_timestamp(String sms_message_timestamp) {
        this.sms_message_timestamp = sms_message_timestamp;
    }

    public String getSms_message_status() {
        return sms_message_status;
    }

    public void setSms_message_status(String sms_message_status) {
        this.sms_message_status = sms_message_status;
    }

    public String getSms_message_folder() {
        return sms_message_folder;
    }

    public void setSms_message_folder(String sms_message_folder) {
        this.sms_message_folder = sms_message_folder;
    }

    public String getSms_message_storage() {
        return sms_message_storage;
    }

    public void setSms_message_storage(String sms_message_storage) {
        this.sms_message_storage = sms_message_storage;
    }

    public String getSms_message_type() {
        return sms_message_type;
    }

    public void setSms_message_type(String sms_message_type) {
        this.sms_message_type = sms_message_type;
    }

    public String getSms_message_text() {
        return sms_message_text;
    }

    public void setSms_message_text(String sms_message_text) {
        this.sms_message_text = sms_message_text;
    }

    public String getSms_message_smsc() {
        return sms_message_smsc;
    }

    public void setSms_message_smsc(String sms_message_smsc) {
        this.sms_message_smsc = sms_message_smsc;
    }

    public String getSms_message_sha256() {
        return sms_message_sha256;
    }

    public void setSms_message_sha256(String sms_message_sha256) {
        this.sms_message_sha256 = sms_message_sha256;
    }

    @Override
    public String toString() {
        return "SMSMessage{" +
                "sms_message_number='" + sms_message_number + '\'' +
                ", sms_message_name='" + sms_message_name + '\'' +
                ", sms_message_timestamp='" + sms_message_timestamp + '\'' +
                ", sms_message_status='" + sms_message_status + '\'' +
                ", sms_message_folder='" + sms_message_folder + '\'' +
                ", sms_message_storage='" + sms_message_storage + '\'' +
                ", sms_message_type='" + sms_message_type + '\'' +
                ", sms_message_text='" + sms_message_text + '\'' +
                ", sms_message_smsc='" + sms_message_smsc + '\'' +
                ", sms_message_sha256='" + sms_message_sha256 + '\'' +
                '}';
    }
}
