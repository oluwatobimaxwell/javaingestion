package com.elastic.javaingestion.elastic.models;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import static com.elastic.javaingestion.elastic.utils.Helper.DEVICE_INDEX;

@Document(indexName = DEVICE_INDEX)
@Setting(settingPath = "static/settings.json")
public class MissedCall  extends DeviceDocument {
    @Field(type = FieldType.Text)
    private String missed_call_type;
    @Field(type = FieldType.Text)
    private String missed_call_number;
    @Field(type = FieldType.Text)
    private String missed_call_name;
    @Field(type = FieldType.Date)
    private String missed_call_timestamp;
    @Field(type = FieldType.Integer)
    private Integer missed_call_duration;
    @Field(type = FieldType.Text)
    private String missed_call_sha256;

    public String getMissed_call_type() {
        return missed_call_type;
    }

    public void setMissed_call_type(String missed_call_type) {
        this.missed_call_type = missed_call_type;
    }

    public String getMissed_call_number() {
        return missed_call_number;
    }

    public void setMissed_call_number(String missed_call_number) {
        this.missed_call_number = missed_call_number;
    }

    public String getMissed_call_name() {
        return missed_call_name;
    }

    public void setMissed_call_name(String missed_call_name) {
        this.missed_call_name = missed_call_name;
    }

    public String getMissed_call_timestamp() {
        return missed_call_timestamp;
    }

    public void setMissed_call_timestamp(String missed_call_timestamp) {
        this.missed_call_timestamp = missed_call_timestamp;
    }

    public Integer getMissed_call_duration() {
        return missed_call_duration;
    }

    public void setMissed_call_duration(Integer missed_call_duration) {
        this.missed_call_duration = missed_call_duration;
    }

    public String getMissed_call_sha256() {
        return missed_call_sha256;
    }

    public void setMissed_call_sha256(String missed_call_sha256) {
        this.missed_call_sha256 = missed_call_sha256;
    }

    @Override
    public String toString() {
        return "MissedCall{" +
                "missed_call_type='" + missed_call_type + '\'' +
                ", missed_call_number='" + missed_call_number + '\'' +
                ", missed_call_name='" + missed_call_name + '\'' +
                ", missed_call_timestamp='" + missed_call_timestamp + '\'' +
                ", missed_call_duration=" + missed_call_duration +
                ", missed_call_sha256='" + missed_call_sha256 + '\'' +
                '}';
    }
}
