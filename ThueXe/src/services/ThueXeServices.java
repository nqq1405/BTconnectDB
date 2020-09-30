package services;

import models.DongXe;
import models.NhaCungCap;
import models.TtDangKyNhaCC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThueXeServices {
    private Connection conn;

    public ThueXeServices(Connection conn) {
        this.conn = conn;
    }

    public List<DongXe> DsDongXeCo5ChoTroLen() throws SQLException {
        List<DongXe> ls = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select DongXe, HangXe, SoChoNgoi  from `thue_xe_cms`.`dongxe` where SoChoNgoi > 5;");

        while (resultSet.next()) {
            ls.add(new DongXe(resultSet.getString("DongXe"),
                    resultSet.getString("HangXe"),
                    resultSet.getInt("SoChoNgoi")));
        }
        return ls;
    }

    public List<DongXe> DsHangXeall() throws SQLException {
        List<DongXe> ls = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select distinct `HangXe` from `dongxe`;");

        while (resultSet.next()) {
            ls.add(new DongXe(resultSet.getString("HangXe")));
        }
        return ls;
    }

    public List<NhaCungCap> DsNhaCungCap(String sql) throws SQLException {
        List<NhaCungCap> ls = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            ls.add(new NhaCungCap(resultSet.getString("MaNhaCC"),
                    resultSet.getString("TenNhaCC"),
                    resultSet.getString("DiaChi"),
                    resultSet.getString("SoDT"),
                    resultSet.getString("MaSoThue")
            ));
        }
        return ls;
    }

    public List<NhaCungCap> DsNhaCungCapTheoHangVaGia() throws SQLException {
        return DsNhaCungCap("select `dangkycungcap`.MaNhaCC, TenNhaCC, DiaChi, SoDT, MaSoThue from `dangkycungcap`\n" +
                "inner join `thue_xe_cms`.`nhacungcap` on `nhacungcap`.MaNhaCC =  `dangkycungcap`.MaNhaCC\n" +
                "inner join `thue_xe_cms`.`dongxe` on  `dongxe`.DongXe = `dangkycungcap`.DongXe\n" +
                "inner join `thue_xe_cms`.`mucphi` on  `mucphi`.MaMP = `dangkycungcap`.MaMP\n" +
                "where (`dongxe`.HangXe = \"Toyota\" and `mucphi`.DonGia = \"15000\") or (`dongxe`.HangXe = \"KIA\" and `mucphi`.DonGia = \"20000\")\n" +
                ";");
    }

    public List<NhaCungCap> DsNhaCungCapSapXepTheoTenVaMST() throws SQLException {
        return DsNhaCungCap("select * from `nhacungcap` order by `nhacungcap`.TenNhaCC ASC , `nhacungcap`.MaSoThue DESC ;");
    }

    public List<NhaCungCap> DsNhaCungCapTheoDaDkTheoDongXe() throws SQLException {
        return DsNhaCungCap("select DISTINCT `dangkycungcap`.MaNhaCC, TenNhaCC, DiaChi, SoDT, MaSoThue from `dangkycungcap`\n" +
                "inner join `nhacungcap` on `nhacungcap`.MaNhaCC =  `dangkycungcap`.MaNhaCC\n" +
                "inner join `thue_xe_cms`.`dongxe` on  `dongxe`.DongXe = `dangkycungcap`.DongXe\n" +
                "where `dongxe`.DongXe = \"Hiace\" or `dongxe`.DongXe = \"Cerato\"\n" +
                ";");
    }

    public List<NhaCungCap> DsNhaCungCapTheoChuaDkCC() throws SQLException {
        return DsNhaCungCap("SELECT ncc.MaNhaCC, TenNhaCC, DiaChi, SoDT, MaSoThue  from `dangkycungcap` as dk\n" +
                "RIGHT join `nhacungcap` as ncc on ncc.MaNhaCC = dk.MaNhaCC \n" +
                "WHERE dk.MaDKCC is null ;");
    }

    public Map<String, Integer> SoLanNhaCCDK() throws SQLException {
        Map<String, Integer> ls = new HashMap<>();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select  `nhacungcap`.TenNhaCC, count(`dangkycungcap`.MaNhaCC) as SoLanNhaCCDK  from `dangkycungcap` \n" +
                "inner join `nhacungcap` on `nhacungcap`.MaNhaCC =  `dangkycungcap`.MaNhaCC\n" +
                "where `dangkycungcap`.NgayBatDauCungCap >= \"2015-11-20\"\n" +
                "GROUP BY `dangkycungcap`.MaNhaCC;");

        while (resultSet.next()) {
            ls.put(resultSet.getString("TenNhaCC"),resultSet.getInt("SoLanNhaCC"));
        }
        return ls;
    }

    public List<TtDangKyNhaCC> DsTtDkCuaNhaCungCap() throws SQLException {
        List<TtDangKyNhaCC> ls = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select dk.MaDKCC,  ncc.MaNhaCC , ncc.TenNhaCC, ncc.DiaChi, ncc.MaSoThue, ldv.TenLoaiDV, mp.DonGia,\n" +
                "dx.HangXe, dk.NgayBatDauCungCap, dk.NgayKetThucCungCap from `dangkycungcap` as dk\n" +
                "inner join `thue_xe_cms`.`dongxe` as dx on  `dx`.DongXe = `dk`.DongXe\n" +
                "inner join `thue_xe_cms`.`mucphi` as mp on  `mp`.MaMP = `dk`.MaMP\n" +
                "inner join `thue_xe_cms`.`loaidichvu` as ldv on ldv.MaLoaiDV  = `dk`.MaLoaiDV\n" +
                "right join `nhacungcap` as ncc on `ncc`.MaNhaCC =  `dk`.MaNhaCC \n" +
                ";");

        while (resultSet.next()) {
            ls.add(new TtDangKyNhaCC(resultSet.getString("MaDKCC"),
                    resultSet.getString("MaNhaCC"),
                    resultSet.getString("TenNhaCC"),
                    resultSet.getString("DiaChi"),
                    resultSet.getString("MaSoThue"),
                    resultSet.getString("TenLoaiDV"),
                    resultSet.getString("DonGia"),
                    resultSet.getString("HangXe"),
                    resultSet.getDate("NgayBatDauCungCap"),
                    resultSet.getDate("NgayKetThucCungCap")
            ));
        }
        return ls;
    }

    public void DisplayNcc(List<NhaCungCap> ds){
        for (NhaCungCap d: ds) {
            System.out.println(d.toString());
        }
    }

    public void DisplayDx(List<DongXe> ds){
        for (DongXe d: ds) {
            System.out.println(d.toString());
        }
    }

    public void DisplayTtDkNhaCC(List<TtDangKyNhaCC> ds){

    }
}
