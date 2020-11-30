package br.com.assertiva.comunika.domain.requests;

import br.com.assertiva.comunika.domain.Message;

import java.util.ArrayList;
import java.util.List;

public class FindByIdRequest {

    List<Message> messages = new ArrayList<>();

    public FindByIdRequest() {
    }

    public FindByIdRequest(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
