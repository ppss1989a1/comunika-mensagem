package br.com.assertiva.comunika.domain.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RequestResponseZenvia {

    @JsonProperty(value = "messages")
    List<ResponseZenvia> lstResponseSms = new ArrayList<>();

    public RequestResponseZenvia() {
    }

    public List<ResponseZenvia> getLstResponseSms() {
        return lstResponseSms;
    }

    public void setLstResponseSms(List<ResponseZenvia> lstResponseSms) {
        this.lstResponseSms = lstResponseSms;
    }
}
