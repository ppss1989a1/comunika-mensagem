package br.com.assertiva.comunika.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "message")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    String id;
    Integer batch;
    String number;
    String message;
    Integer status;
    String created_at;

    public Message() {
    }
}
