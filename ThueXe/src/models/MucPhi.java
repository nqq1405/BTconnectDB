package models;

public class MucPhi {
    private String MaMP;
    private String DonGia;
    private String MoTa;

    public MucPhi(String maMP, String donGia, String moTa) {
        MaMP = maMP;
        DonGia = donGia;
        MoTa = moTa;
    }

    @Override
    public String toString() {
        return "MucPhi{" +
                "MaMP='" + MaMP + '\'' +
                ", DonGia='" + DonGia + '\'' +
                ", MoTa='" + MoTa + '\'' +
                '}';
    }

    public MucPhi() {
    }
}
