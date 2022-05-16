package com.example.NguyenGiaTruong_08D4800065;

public class MayTinhNguyenGiaTruong {
    private String id;
    private String name, loaiMayTinh, hangSX;
    private int namSX,donGia, soLuong;

    public MayTinhNguyenGiaTruong() {
    }

    public MayTinhNguyenGiaTruong(String name, String loaiMayTinh, String hangSX, int namSX, int donGia, int soLuong) {
        this.name = name;
        this.loaiMayTinh = loaiMayTinh;
        this.hangSX = hangSX;
        this.namSX = namSX;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public MayTinhNguyenGiaTruong(String id, String name, String loaiMayTinh, String hangSX, int namSX, int donGia, int soLuong) {
        this.id = id;
        this.name = name;
        this.loaiMayTinh = loaiMayTinh;
        this.hangSX = hangSX;
        this.namSX = namSX;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoaiMayTinh() {
        return loaiMayTinh;
    }

    public void setLoaiMayTinh(String loaiMayTinh) {
        this.loaiMayTinh = loaiMayTinh;
    }

    public String getHangSX() {
        return hangSX;
    }

    public void setHangSX(String hangSX) {
        this.hangSX = hangSX;
    }

    public int getNamSX() {
        return namSX;
    }

    public void setNamSX(int namSX) {
        this.namSX = namSX;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
