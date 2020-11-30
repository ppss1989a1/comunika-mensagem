package br.com.assertiva.comunika.domain.requests;

import br.com.assertiva.comunika.config.CustomLocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

public class ResponseZenvia {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty("message")
    String message;

    @JsonProperty("phone")
    String phone;

    // id Lote
    @JsonProperty(value = "aggregatedId")
    private String aggregatedId;

    @JsonProperty("flashSms")
    private Boolean flashSms;

    @JsonProperty("callBackOption")
    private String callBackOption;

    @JsonProperty("batchId")
    private Integer batchId;

    @JsonProperty("dataCoding")
    private Integer dataCoding;

    @JsonProperty("schedule")
    private String schedule;

    // De para / zenvia para assertiva
    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("statusDescription")
    private String statusDescription;

    @JsonProperty("detailCode")
    private String detailCode;

    @JsonProperty("detailDescription")
    private String detailDescription;

    @JsonProperty("campaignId")
    Integer campaignId;

    public ResponseZenvia() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAggregatedId() {
        return aggregatedId;
    }

    public void setAggregatedId(String aggregatedId) {
        this.aggregatedId = aggregatedId;
    }

    public Boolean getFlashSms() {
        return flashSms;
    }

    public void setFlashSms(Boolean flashSms) {
        this.flashSms = flashSms;
    }

    public String getCallBackOption() {
        return callBackOption;
    }

    public void setCallBackOption(String callBackOption) {
        this.callBackOption = callBackOption;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getDataCoding() {
        return dataCoding;
    }

    public void setDataCoding(Integer dataCoding) {
        this.dataCoding = dataCoding;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
