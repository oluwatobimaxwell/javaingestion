package com.elastic.javaingestion.elastic.models;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import static com.elastic.javaingestion.elastic.utils.Helper.DEVICE_INDEX;

@Document(indexName = DEVICE_INDEX)
@Setting(settingPath = "static/settings.json")
public class OutgoingCall extends DeviceDocument {
    @Field(type = FieldType.Text)
    private String outgoing_call_type;
    @Field(type = FieldType.Text)
    private String outgoing_call_number;
    @Field(type = FieldType.Text)
    private String outgoing_call_name;
    @Field(type = FieldType.Date)
    private String outgoing_call_timestamp;
    @Field(type = FieldType.Integer)
    private Integer outgoing_call_duration;
    @Field(type = FieldType.Text)
    private String outgoing_call_sha256;

    public String getOutgoing_call_type() {
        return outgoing_call_type;
    }

    public void setOutgoing_call_type(String outgoing_call_type) {
        this.outgoing_call_type = outgoing_call_type;
    }

    public String getOutgoing_call_number() {
        return outgoing_call_number;
    }

    public void setOutgoing_call_number(String outgoing_call_number) {
        this.outgoing_call_number = outgoing_call_number;
    }

    public String getOutgoing_call_name() {
        return outgoing_call_name;
    }

    public void setOutgoing_call_name(String outgoing_call_name) {
        this.outgoing_call_name = outgoing_call_name;
    }

    public String getOutgoing_call_timestamp() {
        return outgoing_call_timestamp;
    }

    public void setOutgoing_call_timestamp(String outgoing_call_timestamp) {
        this.outgoing_call_timestamp = outgoing_call_timestamp;
    }

    public Integer getOutgoing_call_duration() {
        return outgoing_call_duration;
    }

    public void setOutgoing_call_duration(Integer outgoing_call_duration) {
        this.outgoing_call_duration = outgoing_call_duration;
    }

    public String getOutgoing_call_sha256() {
        return outgoing_call_sha256;
    }

    public void setOutgoing_call_sha256(String outgoing_call_sha256) {
        this.outgoing_call_sha256 = outgoing_call_sha256;
    }

    @Override
    public String toString() {
        return "OutgoingCall{" +
                "outgoing_call_type='" + outgoing_call_type + '\'' +
                ", outgoing_call_number='" + outgoing_call_number + '\'' +
                ", outgoing_call_name='" + outgoing_call_name + '\'' +
                ", outgoing_call_timestamp='" + outgoing_call_timestamp + '\'' +
                ", outgoing_call_duration=" + outgoing_call_duration +
                ", outgoing_call_sha256='" + outgoing_call_sha256 + '\'' +
                '}';
    }
}
