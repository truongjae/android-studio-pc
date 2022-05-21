package com.example.myapplication;

public class HoaQuaSach {
    private int id;
    private String name, loai;
    private String donViTinh;
    private int donGia;
    private String noiSX;

    public HoaQuaSach() {
    }

    public HoaQuaSach(int id, String name, String loai, String donViTinh, int donGia, String noiSX) {
        this.id = id;
        this.name = name;
        this.loai = loai;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
        this.noiSX = noiSX;
    }

    public HoaQuaSach(String name, String loai, String donViTinh, int donGia, String noiSX) {
        this.name = name;
        this.loai = loai;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
        this.noiSX = noiSX;
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getNoiSX() {
        return noiSX;
    }

    public void setNoiSX(String noiSX) {
        this.noiSX = noiSX;
    }
}
