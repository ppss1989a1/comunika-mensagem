package br.com.assertiva.comunika.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Batch {

    @JsonProperty("batch")
    Integer batch;

    @JsonProperty("messageAmount")
    Integer messageAmount;

    @JsonProperty("status")
    Integer status;

    public Batch() {}



}
