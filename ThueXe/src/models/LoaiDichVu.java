package models;

public class LoaiDichVu {
    private String MaLoaiDV;
    private String TenLoaiDV;

    public LoaiDichVu(String maLoaiDV, String tenLoaiDV) {
        MaLoaiDV = maLoaiDV;
        TenLoaiDV = tenLoaiDV;
    }

    @Override
    public String toString() {
        return "LoaiDichVu{" +
                "MaLoaiDV='" + MaLoaiDV + '\'' +
                ", TenLoaiDV='" + TenLoaiDV + '\'' +
                '}';
    }

    public LoaiDichVu() {
    }
}
