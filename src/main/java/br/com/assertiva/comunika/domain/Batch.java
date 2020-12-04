package br.com.assertiva.comunika.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Batch {

    @JsonProperty("batchId")
    private Integer batchId;

    @JsonProperty("messageAmount")
    private Integer messageAmount;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("schedule")
    private String schedule;

    public Batch() {}

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getMessageAmount() {
        return messageAmount;
    }

    public void setMessageAmount(Integer messageAmount) {
        this.messageAmount = messageAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
