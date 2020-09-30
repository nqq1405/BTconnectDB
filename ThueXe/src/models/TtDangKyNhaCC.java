package models;

import java.sql.Date;

public class TtDangKyNhaCC {
    private String MaDKCC;
    private String MaNhaCC;
    private String TenNhaCC;
    private String DiaChi;
    private String MaSoThue;
    private String TenLoaiDV;
    private String DonGia;
    private String HangXe;
    private Date NgayBatDauCungCap;
    private Date NgayKetThucCungCap;

    public TtDangKyNhaCC(String maDKCC, String maNhaCC, String tenNhaCC, String diaChi, String maSoThue,
                         String tenLoaiDV, String donGia, String hangXe, Date ngayBatDauCungCap, Date ngayKetThucCungCap) {
        MaDKCC = maDKCC;
        MaNhaCC = maNhaCC;
        TenNhaCC = tenNhaCC;
        DiaChi = diaChi;
        MaSoThue = maSoThue;
        TenLoaiDV = tenLoaiDV;
        DonGia = donGia;
        HangXe = hangXe;
        NgayBatDauCungCap = ngayBatDauCungCap;
        NgayKetThucCungCap = ngayKetThucCungCap;
    }


}
