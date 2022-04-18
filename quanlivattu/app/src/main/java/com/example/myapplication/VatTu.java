package com.example.myapplication;

public class VatTu {
    private int id,donGia;
    private String name,hangSanXuat,donViTinh;

    public VatTu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHangSanXuat() {
        return hangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        this.hangSanXuat = hangSanXuat;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public VatTu(int donGia, String name, String hangSanXuat, String donViTinh) {
        this.donGia = donGia;
        this.name = name;
        this.hangSanXuat = hangSanXuat;
        this.donViTinh = donViTinh;
    }

    public VatTu(int id, int donGia, String name, String hangSanXuat, String donViTinh) {
        this.id = id;
        this.donGia = donGia;
        this.name = name;
        this.hangSanXuat = hangSanXuat;
        this.donViTinh = donViTinh;
    }
}
