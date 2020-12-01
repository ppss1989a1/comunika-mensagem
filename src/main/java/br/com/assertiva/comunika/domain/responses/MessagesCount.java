package br.com.assertiva.comunika.domain.responses;

public class MessagesCount {

    Long sendedWithConfirmation, sendedWithoutConfirmation, waitingToSend, canceled, paused, blacklist, error;

    public MessagesCount() {
    }

    public MessagesCount(Long sendedWithConfirmation, Long sendedWithoutConfirmation, Long waitingToSend, Long canceled, Long paused, Long blacklist, Long error) {
        this.sendedWithConfirmation = sendedWithConfirmation;
        this.sendedWithoutConfirmation = sendedWithoutConfirmation;
        this.waitingToSend = waitingToSend;
        this.canceled = canceled;
        this.paused = paused;
        this.blacklist = blacklist;
        this.error = error;
    }

    public Long getSendedWithConfirmation() {
        return sendedWithConfirmation;
    }

    public void setSendedWithConfirmation(Long sendedWithConfirmation) {
        this.sendedWithConfirmation = sendedWithConfirmation;
    }

    public Long getSendedWithoutConfirmation() {
        return sendedWithoutConfirmation;
    }

    public void setSendedWithoutConfirmation(Long sendedWithoutConfirmation) {
        this.sendedWithoutConfirmation = sendedWithoutConfirmation;
    }

    public Long getWaitingToSend() {
        return waitingToSend;
    }

    public void setWaitingToSend(Long waitingToSend) {
        this.waitingToSend = waitingToSend;
    }

    public Long getCanceled() {
        return canceled;
    }

    public void setCanceled(Long canceled) {
        this.canceled = canceled;
    }

    public Long getPaused() {
        return paused;
    }

    public void setPaused(Long paused) {
        this.paused = paused;
    }

    public Long getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Long blacklist) {
        this.blacklist = blacklist;
    }

    public Long getError() {
        return error;
    }

    public void setError(Long error) {
        this.error = error;
    }
}
