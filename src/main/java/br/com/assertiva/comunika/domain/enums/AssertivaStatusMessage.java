package br.com.assertiva.comunika.domain.enums;

public enum AssertivaStatusMessage {

    SENDED_WITH_CONFIRMATION("Envio Confirmado", 1),
    SENDED_WITHOUT_CONFIRMATION("Envio Nã Confirmado", 2),
    WAITING_TO_SEND("Aguardando Envio", 3),
    CANCELED("Cancelada", 4),
    STOPPED("Pausada", 5),
    BLACKLIST("Blacklist", 6),
    ERROR("Erro", 7);

    String description;
    Integer id;

    AssertivaStatusMessage(String description, Integer id) {
        this.description = description;
        this.id = id;
    }

    public static AssertivaStatusMessage valueOf(Integer value) {
        switch (value) {
            case 1:
                return SENDED_WITH_CONFIRMATION;
            case 2:
                return SENDED_WITHOUT_CONFIRMATION;
            case 3:
                return WAITING_TO_SEND;
            case 4:
                return CANCELED;
            case 5:
                return STOPPED;
            case 6:
                return BLACKLIST;
            case 7:
                return ERROR;
            default:
                return null;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
