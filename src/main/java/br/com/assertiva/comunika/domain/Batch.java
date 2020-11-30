package br.com.assertiva.comunika.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Batch {

    @JsonProperty("batchId")
    Integer batchId;

    @JsonProperty("messageAmount")
    Integer messageAmount;

    @JsonProperty("status")
    Integer status;

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
}
