package com.elastic.javaingestion.elastic.models;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import static com.elastic.javaingestion.elastic.utils.Helper.DEVICE_INDEX;

@Document(indexName = DEVICE_INDEX)
@Setting(settingPath = "static/settings.json")
public class IncomingCall extends DeviceDocument {
    @Field(type = FieldType.Text)
    private String incoming_call_type;
    @Field(type = FieldType.Text)
    private String incoming_call_number;
    @Field(type = FieldType.Text)
    private String incoming_call_name;
    @Field(type = FieldType.Date)
    private String incoming_call_timestamp;
    @Field(type = FieldType.Integer)
    private Integer incoming_call_duration;
    @Field(type = FieldType.Text)
    private String incoming_call_sha256;

    @Override
    public String toString() {
        return "IncomingCall{" +
                "incoming_call_type='" + incoming_call_type + '\'' +
                ", incoming_call_number='" + incoming_call_number + '\'' +
                ", incoming_call_name='" + incoming_call_name + '\'' +
                ", incoming_call_timestamp='" + incoming_call_timestamp + '\'' +
                ", incoming_call_duration=" + incoming_call_duration +
                ", incoming_call_sha256='" + incoming_call_sha256 + '\'' +
                '}';
    }

    public String getIncoming_call_type() {
        return incoming_call_type;
    }

    public void setIncoming_call_type(String incoming_call_type) {
        this.incoming_call_type = incoming_call_type;
    }

    public String getIncoming_call_number() {
        return incoming_call_number;
    }

    public void setIncoming_call_number(String incoming_call_number) {
        this.incoming_call_number = incoming_call_number;
    }

    public String getIncoming_call_name() {
        return incoming_call_name;
    }

    public void setIncoming_call_name(String incoming_call_name) {
        this.incoming_call_name = incoming_call_name;
    }

    public String getIncoming_call_timestamp() {
        return incoming_call_timestamp;
    }

    public void setIncoming_call_timestamp(String incoming_call_timestamp) {
        this.incoming_call_timestamp = incoming_call_timestamp;
    }

    public Integer getIncoming_call_duration() {
        return incoming_call_duration;
    }

    public void setIncoming_call_duration(Integer incoming_call_duration) {
        this.incoming_call_duration = incoming_call_duration;
    }

    public String getIncoming_call_sha256() {
        return incoming_call_sha256;
    }

    public void setIncoming_call_sha256(String incoming_call_sha256) {
        this.incoming_call_sha256 = incoming_call_sha256;
    }
}
