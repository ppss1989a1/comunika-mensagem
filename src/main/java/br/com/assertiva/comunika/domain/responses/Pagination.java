package br.com.assertiva.comunika.domain.responses;

import br.com.assertiva.comunika.domain.dto.MessagePagination;
import br.com.assertiva.comunika.domain.dto.Page;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    List<MessagePagination> messages = new ArrayList<>();

    Page page;

    public Pagination(List<MessagePagination> messages, Page page) {
        this.messages = messages;
        this.page = page;
    }

    public List<MessagePagination> getmessages() {
        return messages;
    }

    public void setmessages(List<MessagePagination> messages) {
        this.messages = messages;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
