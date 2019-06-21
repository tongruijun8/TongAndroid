package com.trj.tlib.bean;

/**
 * 手机联系人的信息
 */
public class ContactsInfo {

    private String name;
    private String telPhone;

    public ContactsInfo() {
    }

    public ContactsInfo(String name, String telPhone) {
        this.name = name;
        this.telPhone = telPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }
}
