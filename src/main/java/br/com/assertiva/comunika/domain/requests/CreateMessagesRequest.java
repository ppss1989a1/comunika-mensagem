package br.com.assertiva.comunika.domain.requests;

import br.com.assertiva.comunika.domain.Batch;
import br.com.assertiva.comunika.domain.Campaign;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CreateMessagesRequest {

    @JsonProperty("batches")
    List<Batch> batches = new ArrayList<>();

    @JsonProperty("numbers")
    List<String> numbers = new ArrayList<>();

    @JsonProperty("campaign")
    Campaign campaign;

    @JsonProperty("message")
    String message;

    @JsonProperty("blacklistNumbers")
    List<String> blacklistNumbers = new ArrayList<>();

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

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getBlacklistNumbers() {
        return blacklistNumbers;
    }

    public void setBlacklistNumbers(List<String> blacklistNumbers) {
        this.blacklistNumbers = blacklistNumbers;
    }
}
