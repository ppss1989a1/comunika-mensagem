package br.com.assertiva.comunika.domain;

public class Campaign {

    Integer id;
    Integer routeId;

    public Campaign() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }
}
