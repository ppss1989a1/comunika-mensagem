package br.com.assertiva.comunika.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "mensagem")
public class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    String id;
    Integer loteId;
    String numero;
    String mensagem;
    Integer status;
    String inclusao;

    public Mensagem() {
    }

    public Integer getLoteId() {
        return loteId;
    }

    public void setLoteId(Integer loteId) {
        this.loteId = loteId;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInclusao() {
        return inclusao;
    }

    public void setInclusao(String inclusao) {
        this.inclusao = inclusao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
