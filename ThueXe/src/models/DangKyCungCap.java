package models;

import java.sql.Date;

public class DangKyCungCap {
    private String MaDKCC;
    private Date NgayBatDauCungCap;
    private Date NgayKetThucCungCap;
    private int SoLuongXeDangKy;
    private String MaNhaCC;
    private String MaLoaiDV;
    private String DongXe;
    private String MaMP;

    public DangKyCungCap(String maDKCC, Date ngayBatDauCungCap, Date ngayKetThucCungCap, int soLuongXeDangKy,
                         String maNhaCC, String maLoaiDV, String dongXe, String maMP) {
        MaDKCC = maDKCC;
        NgayBatDauCungCap = ngayBatDauCungCap;
        NgayKetThucCungCap = ngayKetThucCungCap;
        SoLuongXeDangKy = soLuongXeDangKy;
        MaNhaCC = maNhaCC;
        MaLoaiDV = maLoaiDV;
        DongXe = dongXe;
        MaMP = maMP;
    }

    public DangKyCungCap() {
    }
}
