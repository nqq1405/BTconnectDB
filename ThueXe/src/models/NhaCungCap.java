package models;

public class NhaCungCap {
    private String MaNhaCC;
    private String TenNhaCC;
    private String DiaChi;
    private String SoDT;
    private String MaSoThue;

    public NhaCungCap(String maNhaCC, String tenNhaCC, String diaChi, String soDT, String maSoThue) {
        MaNhaCC = maNhaCC;
        TenNhaCC = tenNhaCC;
        DiaChi = diaChi;
        SoDT = soDT;
        MaSoThue = maSoThue;
    }

    @Override
    public String toString() {
        return "NhaCungCap{" +
                "MaNhaCC='" + MaNhaCC + '\'' +
                ", TenNhaCC='" + TenNhaCC + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", SoDT='" + SoDT + '\'' +
                ", MaSoThue='" + MaSoThue + '\'' +
                '}';
    }

    public String getMaNhaCC() {
        return MaNhaCC;
    }

    public void setMaNhaCC(String maNhaCC) {
        MaNhaCC = maNhaCC;
    }

    public String getTenNhaCC() {
        return TenNhaCC;
    }

    public void setTenNhaCC(String tenNhaCC) {
        TenNhaCC = tenNhaCC;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String soDT) {
        SoDT = soDT;
    }

    public String getMaSoThue() {
        return MaSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        MaSoThue = maSoThue;
    }
}
