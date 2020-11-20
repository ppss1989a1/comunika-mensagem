package br.com.assertiva.comunika.domain.requests;

import br.com.assertiva.comunika.domain.Batch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateMessagesRequest {

    @JsonProperty("batches")
    List<Batch> batches = new ArrayList<>();

    @JsonProperty("numbers")
    List<String> lstNumeros = new ArrayList<>();

    @JsonProperty("message")
    String mensagem;

    public CreateMessagesRequest() {
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public List<String> getLstNumeros() {
        return lstNumeros;
    }

    public void setLstNumeros(List<String> lstNumeros) {
        this.lstNumeros = lstNumeros;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
