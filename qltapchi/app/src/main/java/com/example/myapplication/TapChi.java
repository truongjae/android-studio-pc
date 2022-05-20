package com.example.myapplication;

public class TapChi {
    private int id;
    private String name, loai;
    private int soXB;
    private String nhaXB;
    private int donGia;
    public TapChi() {
    }

    public TapChi(String name, String loai, int soXB, String nhaXB, int donGia) {
        this.name = name;
        this.loai = loai;
        this.soXB = soXB;
        this.nhaXB = nhaXB;
        this.donGia = donGia;
    }



    public TapChi(int id, String name, String loai, int soXB, String nhaXB, int donGia) {
        this.id = id;
        this.name = name;
        this.loai = loai;
        this.soXB = soXB;
        this.nhaXB = nhaXB;
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getSoXB() {
        return soXB;
    }

    public void setSoXB(int soXB) {
        this.soXB = soXB;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
}
