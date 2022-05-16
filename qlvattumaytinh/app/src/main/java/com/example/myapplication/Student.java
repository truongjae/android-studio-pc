package com.example.myapplication;

public class Student {
    private int id;
    private String name, donViTinh, donGia;

    public Student() {
    }

    public Student(int id, String name, String donViTinh, String donGia) {
        this.id = id;
        this.name = name;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
    }

    public Student(String name, String donViTinh, String donGia) {
        this.name = name;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", fullName='" + name + '\'' +
//                ", gender='" + donViTinh + '\'' +
//                ", phone='" + donGia + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

}
