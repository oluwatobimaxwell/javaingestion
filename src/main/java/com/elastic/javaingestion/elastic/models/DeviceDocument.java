package com.elastic.javaingestion.elastic.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Map;

import static com.elastic.javaingestion.elastic.utils.Helper.DEVICE_INDEX;

@Document(indexName = DEVICE_INDEX)
@Setting(settingPath = "static/settings.json")
public class DeviceDocument {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String timestamp;
    @Field(type = FieldType.Keyword)
    private String report_type;
    @Field(type = FieldType.Text)
    private String selected_manufacture;
    @Field(type = FieldType.Text)
    private String selected_model;
    @Field(type = FieldType.Text)
    private String detected_manufacture;
    @Field(type = FieldType.Text)
    private String detected_model;
    @Field(type = FieldType.Text)
    private String revision;
    @Field(type = FieldType.Keyword)
    private String imei;
    @Field(type = FieldType.Keyword)
    private String iccid;
    @Field(type = FieldType.Keyword)
    private String imsi;
    @Field(type = FieldType.Keyword)
    private String advertisingid;
    @Field(type = FieldType.Date)
    private Long start_date_time;
    @Field(type = FieldType.Date)
    private Long end_date_time;
    @Field(type = FieldType.Date)
    private Long phone_date_time;
    @Field(type = FieldType.Keyword)
    private String connection_type;
    @Field(type = FieldType.Object)
    private Map<String, String> ufed_version;
    @Field(type = FieldType.Integer)
    private Integer usingclient;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getSelected_manufacture() {
        return selected_manufacture;
    }

    public void setSelected_manufacture(String selected_manufacture) {
        this.selected_manufacture = selected_manufacture;
    }

    public String getSelected_model() {
        return selected_model;
    }

    public void setSelected_model(String selected_model) {
        this.selected_model = selected_model;
    }

    public String getDetected_manufacture() {
        return detected_manufacture;
    }

    public void setDetected_manufacture(String detected_manufacture) {
        this.detected_manufacture = detected_manufacture;
    }

    public String getDetected_model() {
        return detected_model;
    }

    public void setDetected_model(String detected_model) {
        this.detected_model = detected_model;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getAdvertisingid() {
        return advertisingid;
    }

    public void setAdvertisingid(String advertisingid) {
        this.advertisingid = advertisingid;
    }

    public Long getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(Long start_date_time) {
        this.start_date_time = start_date_time;
    }

    public Long getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(Long end_date_time) {
        this.end_date_time = end_date_time;
    }

    public Long getPhone_date_time() {
        return phone_date_time;
    }

    public void setPhone_date_time(Long phone_date_time) {
        this.phone_date_time = phone_date_time;
    }

    public String getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(String connection_type) {
        this.connection_type = connection_type;
    }

    public Map<String, String> getUfed_version() {
        return ufed_version;
    }

    public void setUfed_version(Map<String, String> ufed_version) {
        this.ufed_version = ufed_version;
    }

    public Integer getUsingclient() {
        return usingclient;
    }

    public void setUsingclient(Integer usingclient) {
        this.usingclient = usingclient;
    }
}
