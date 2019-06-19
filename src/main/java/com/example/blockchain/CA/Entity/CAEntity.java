package com.example.blockchain.CA.Entity;

public class CAEntity {
    private String address;
    private String name;
    private String public_key;
    private String private_key;

    public  CAEntity() {

    }

    public CAEntity(String public_key, String private_key) {
        this.public_key = public_key;
        this.private_key = private_key;
    }

    public CAEntity(String address, String public_key, String private_key) {
        String name = "";
        int port = Integer.parseInt(address.split(":")[1]);
        switch (port) {
            case 8081: name = "生产商";break;
            case 8082: name = "质检处";break;
            case 8083: name = "仓库";break;
            case 8084: name = "认证机构";break;
            case 8085: name = "认证机构2";break;
            case 8086: name = "认证机构3";break;
        }
        this.address = address;
        this.name = name;
        this.public_key = public_key;
        this.private_key = private_key;
    }

    public CAEntity(String address, String name, String public_key, String private_key) {
        this.address = address;
        this.name = name;
        this.public_key = public_key;
        this.private_key = private_key;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
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
}
