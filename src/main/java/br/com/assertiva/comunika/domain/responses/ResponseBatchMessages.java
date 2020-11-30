package br.com.assertiva.comunika.domain.responses;

import br.com.assertiva.comunika.domain.Message;

import java.util.ArrayList;
import java.util.List;

public class ResponseBatchMessages {

    List<Message> messages = new ArrayList<>();

    public ResponseBatchMessages() {
    }

    public ResponseBatchMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
