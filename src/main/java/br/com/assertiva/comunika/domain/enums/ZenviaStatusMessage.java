package br.com.assertiva.comunika.domain.enums;

public enum ZenviaStatusMessage {

    OK(0, "Ok"),
    SCHEDULED(1, "Scheduled"),
    SENT(2, "Sent"),
    DELIVERED(3, "Delivered"),
    NOT(4, "Not Received"),
    BLOCKED_NO_COVERAGE(5, "Blocked - No Coverage"),
    BLOCKED_BLACK_LISTED(6, "Blocked - Black listed"),
    BLOCKED_INVALID_NUMBER(7, "Blocked - Invalid Number"),
    BLOCKED_CONTENT_NOT_ALLOWED_OR_EXPIRED(8, "Blocked - Content not allowed / Message Expired"),
    BLOCKED(9, "Blocked"),
    ERROR(10, "Error");

    Integer id;
    String description;

    ZenviaStatusMessage(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static ZenviaStatusMessage valueOf(Integer value) {
        switch (value) {
            case 0:
                return OK;
            case 1:
                return SCHEDULED;
            case 2:
                return SENT;
            case 3:
                return DELIVERED;
            case 4:
                return NOT;
            case 5:
                return BLOCKED_NO_COVERAGE;
            case 6:
                return BLOCKED_BLACK_LISTED;
            case 7:
                return BLOCKED_INVALID_NUMBER;
            case 8:
                return BLOCKED_CONTENT_NOT_ALLOWED_OR_EXPIRED;
            case 9:
                return BLOCKED;
            case 10:
                return ERROR;
            default:
                return null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
