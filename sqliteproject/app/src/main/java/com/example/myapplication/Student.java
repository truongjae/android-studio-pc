package com.example.myapplication;

public class Student {
    private int id;
    private String fullName,clazz,address,phone;

    public Student() {
    }

    public Student(int id, String fullName, String clazz, String address, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.clazz = clazz;
        this.address = address;
        this.phone = phone;
    }

    public Student(String fullName, String clazz, String address, String phone) {
        this.fullName = fullName;
        this.clazz = clazz;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", clazz='" + clazz + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
