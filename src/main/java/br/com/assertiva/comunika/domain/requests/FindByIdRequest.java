package br.com.assertiva.comunika.domain.requests;

import br.com.assertiva.comunika.domain.Message;

import java.util.ArrayList;
import java.util.List;

public class FindByIdRequest {

    private List<ResponseZenvia> messages = new ArrayList<>();

    public FindByIdRequest() {
    }

    public List<ResponseZenvia> getMessages() {
        return messages;
    }

    public void setMessages(List<ResponseZenvia> messages) {
        this.messages = messages;
    }
}
