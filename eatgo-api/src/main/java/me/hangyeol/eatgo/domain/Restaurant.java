package me.hangyeol.eatgo.domain;

public class Restaurant {

    private final Long id;
    private final String name;
    private final String address;


    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInformation() {
        return name + " in " + address;
    }
    public String getAddress() {
        return address;
    }

}
