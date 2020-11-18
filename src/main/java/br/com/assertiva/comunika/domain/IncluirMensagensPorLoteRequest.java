package br.com.assertiva.comunika.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class IncluirMensagensPorLoteRequest {

    @JsonProperty("batches")
    List<Lote> lstLote = new ArrayList<>();

    @JsonProperty("numbers")
    List<String> lstNumeros = new ArrayList<>();

    @JsonProperty("message")
    String mensagem;

    public IncluirMensagensPorLoteRequest() {
    }

    public List<Lote> getLstLote() {
        return lstLote;
    }

    public void setLstLote(List<Lote> lstLote) {
        this.lstLote = lstLote;
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
