package com.example.NguyenGiaTruong_08D4800065;

public class NguyenGiaTruong_Xe {
    private String bienKiemSoat;
    private String tenChuXe;
    private String hangXe;
    private int trongTai;
    private String hinhThucKinhDoanh;

    public NguyenGiaTruong_Xe(String tenChuXe, String hangXe, int trongTai, String hinhThucKinhDoanh) {
        this.tenChuXe = tenChuXe;
        this.hangXe = hangXe;
        this.trongTai = trongTai;
        this.hinhThucKinhDoanh = hinhThucKinhDoanh;
    }

    public NguyenGiaTruong_Xe(String bienKiemSoat, String tenChuXe, String hangXe, int trongTai, String hinhThucKinhDoanh) {
        this.bienKiemSoat = bienKiemSoat;
        this.tenChuXe = tenChuXe;
        this.hangXe = hangXe;
        this.trongTai = trongTai;
        this.hinhThucKinhDoanh = hinhThucKinhDoanh;
    }

    public String getBienKiemSoat() {
        return bienKiemSoat;
    }

    public void setBienKiemSoat(String bienKiemSoat) {
        this.bienKiemSoat = bienKiemSoat;
    }

    public String getTenChuXe() {
        return tenChuXe;
    }

    public void setTenChuXe(String tenChuXe) {
        this.tenChuXe = tenChuXe;
    }

    public String getHangXe() {
        return hangXe;
    }

    public void setHangXe(String hangXe) {
        this.hangXe = hangXe;
    }

    public int getTrongTai() {
        return trongTai;
    }

    public void setTrongTai(int trongTai) {
        this.trongTai = trongTai;
    }

    public String getHinhThucKinhDoanh() {
        return hinhThucKinhDoanh;
    }

    public void setHinhThucKinhDoanh(String hinhThucKinhDoanh) {
        this.hinhThucKinhDoanh = hinhThucKinhDoanh;
    }

    public NguyenGiaTruong_Xe() {
    }

}
