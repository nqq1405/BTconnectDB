import models.DongXe;
import models.NhaCungCap;
import models.TtDangKyNhaCC;
import services.ThueXeServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class test {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found !!");
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/thue_xe_cms", "root", "quang123");
            System.out.println("SQL Connection to database established!");
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console"  + e);
            return;
        }

        ThueXeServices tXS = new ThueXeServices(connection);

        System.out.println();
        try {
            List<DongXe> dsdx5c = tXS.DsDongXeCo5ChoTroLen();
            System.out.println("1.Danh Sach Dong Xe Tren 5 Cho La:");

            tXS.DisplayDx(dsdx5c);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            List<NhaCungCap> dsHangGia = tXS.DsNhaCungCapTheoHangVaGia();
            System.out.println("2.thông tin của các nhà cung cấp ĐÃ TỪNG đăng ký cung cấp những dòng xe\n" +
                    "thuộc hãng xe “Toyota” với mức phí có đơn giá là 15.000 VNĐ/km hoặc những dòng xe\n" +
                    "thuộc hãng xe “KIA” với mức phí có đơn giá là 20.000 VNĐ/km La:");

            tXS.DisplayNcc(dsHangGia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            List<NhaCungCap> dsNccSapXep = tXS.DsNhaCungCapSapXepTheoTenVaMST();
            System.out.println("3.Danh Sach thông tin toàn bộ nhà cung cấp được sắp xếp tăng dần theo tên nhà cung\n" +
                    "cấp và giảm dần theo mã số thuế La:");

            tXS.DisplayNcc(dsNccSapXep);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Set<Map.Entry<String, Integer>> ds1 = tXS.SoLanNhaCCDK().entrySet();
            System.out.println("4. số lần đăng ký cung cấp phương tiện tương ứng cho từng nhà cung cấp với\n" +
                    "yêu cầu chỉ đếm cho những nhà cung cấp thực hiện đăng ký cung cấp có ngày bắt đầu\n" +
                    "cung cấp là “20/11/2015”");
            for (Map.Entry<String, Integer> d : ds1) {
                System.out.println(d.getKey() + " || " + d.getValue());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            List<DongXe> ds = tXS.DsHangXeall();
            System.out.println("5.Danh Sach tên của toàn bộ các hãng xe có trong cơ sở dữ liệu với yêu cầu mỗi hãng xe\n" +
                    "chỉ được liệt kê một lần La:");

            tXS.DisplayDx(ds);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            List<TtDangKyNhaCC> ds = tXS.DsTtDkCuaNhaCungCap();
            System.out.println("6.Danh Sach MaDKCC, MaNhaCC, TenNhaCC, DiaChi, MaSoThue, TenLoaiDV, DonGia,\n" +
                    "HangXe, NgayBatDauCC, NgayKetThucCC của tất cả các lần đăng ký cung cấp phương\n" +
                    "tiện với yêu cầu những nhà cung cấp nào chưa từng thực hiện đăng ký cung cấp phương\n" +
                    "tiện thì cũng liệt kê thông tin những nhà cung cấp đó ra La:");

            tXS.DisplayTtDkNhaCC(ds);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            List<NhaCungCap> dsNccDongxe = tXS.DsNhaCungCapTheoDaDkTheoDongXe();
            System.out.println("7.Danh Sach thông tin của các nhà cung cấp đã từng đăng ký cung cấp phương tiện\n" +
                    "thuộc dòng xe “Hiace” hoặc từng đăng ký cung cấp phương tiện thuộc dòng xe “Cerato”La:");

            for (NhaCungCap d: dsNccDongxe) {
                System.out.println(d.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            List<NhaCungCap> dsChuaDk = tXS.DsNhaCungCapTheoChuaDkCC();
            System.out.println("8.Danh Sach hông tin của các nhà cung cấp chưa từng thực hiện đăng ký cung cấp\n" +
                    "phương tiện lần nào cả La:");

            for (NhaCungCap d: dsChuaDk) {
                System.out.println(d.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
