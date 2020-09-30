package models;

public class DongXe {
    private String DongXe;
    private String HangXe;
    private int SoChoNgoi;

    public DongXe(String dongXe, String hangXe, int soChoNgoi) {
        DongXe = dongXe;
        HangXe = hangXe;
        SoChoNgoi = soChoNgoi;
    }

    public DongXe(String hangXe) {
        HangXe = hangXe;
    }

    @Override
    public String toString() {
        return "DongXe{" +
                "DongXe='" + DongXe + '\'' +
                ", HangXe='" + HangXe + '\'' +
                ", SoChoNgoi=" + SoChoNgoi +
                '}';
    }

    public String getDongXe() {
        return DongXe;
    }

    public void setDongXe(String dongXe) {
        DongXe = dongXe;
    }

    public String getHangXe() {
        return HangXe;
    }

    public void setHangXe(String hangXe) {
        HangXe = hangXe;
    }

    public int getSoChoNgoi() {
        return SoChoNgoi;
    }

    public void setSoChoNgoi(int soChoNgoi) {
        SoChoNgoi = soChoNgoi;
    }

    public DongXe() {
    }
}
