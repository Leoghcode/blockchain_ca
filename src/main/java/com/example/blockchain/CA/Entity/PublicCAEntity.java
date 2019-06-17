package com.example.blockchain.CA.Entity;

public class PublicCAEntity {
    private String address;
    private String name;

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    private String public_key;

    public PublicCAEntity(CAEntity caEntity) {
        this.address = caEntity.getAddress();
        this.name = caEntity.getName();
        this.public_key = caEntity.getPublic_key();
    }

    public PublicCAEntity(String address, String name, String public_key) {
        this.address = address;
        this.name = name;
        this.public_key = public_key;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublic_key() {
        return public_key;
    }
}
