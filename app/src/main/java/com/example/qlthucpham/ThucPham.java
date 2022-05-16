package com.example.qlthucpham;

public class ThucPham {
    private int id;
    private String name;
    private String donViTinh;
    private float donGia;

    public ThucPham() {
    }

    public ThucPham(String name, String donViTinh, float donGia) {
        this.name = name;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
    }

    public ThucPham(int id, String name, String donViTinh, float donGia) {
        this.id = id;
        this.name = name;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
    }

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

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }
}
