package br.com.assertiva.comunika.domain.requests;

import br.com.assertiva.comunika.domain.Batch;

import java.util.ArrayList;
import java.util.List;

public class CreateMessagesRequest {

    List<Batch> batches = new ArrayList<>();

    List<String> numbers = new ArrayList<>();

    Integer campaignId;

    String message;

    public CreateMessagesRequest() {
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
