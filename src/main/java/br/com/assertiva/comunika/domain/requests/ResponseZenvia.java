package br.com.assertiva.comunika.domain.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseZenvia {

    @JsonProperty(value = "id")
    private String id;

    // De para / zenvia para assertiva
    @JsonProperty("statusCode")
    private String statusCode;

    public ResponseZenvia() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ResponseZenvia{" +
                "id='" + id + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
