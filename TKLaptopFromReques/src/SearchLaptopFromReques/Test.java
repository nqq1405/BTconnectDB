package SearchLaptopFromReques;

import SearchLaptopFromReques.models.Counter;
import SearchLaptopFromReques.models.LaptopEntity;
import SearchLaptopFromReques.models.Statistic;
import SearchLaptopFromReques.services.LaptopService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Test {
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
                    .getConnection("jdbc:mysql://localhost:3306/store_cms_plusplus", "root", "quang123");
            System.out.println("SQL Connection to database established!");
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console"  + e);
            return;
        }

        LaptopService laptopService = new LaptopService(connection);

//        List<LaptopEntity> laptopEntityListPrice = null;
//        try {
//            laptopEntityListPrice = laptopService.FindLaptopFromPrice(10000000f,40000000f);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        System.out.println("Laptop theo gía từ 10.000.000 => 40.000.000 là: ");
//        laptopService.DisplayName(laptopEntityListPrice);
//

        List<LaptopEntity> laptopEntityClassesListSSD_MAKER = laptopService.FindLaptopFromSSD_MAKER("HP","8gb");
        System.out.println("Laptop theo hãng và ssd là:");
        laptopService.DisplayName(laptopEntityClassesListSSD_MAKER);

//        System.out.println("loai laptop theo loai cua moi hang la");
//        try {
//            for (Counter c :laptopService.getCounterByMaker()) {
//                System.out.println(c.toString());
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

//        System.out.println("thống kê số lượng, số tiền bán được của mỗi hãng la: ");
//        try {
//            for (Statistic s :laptopService.getStatisticByMaker()) {
//                System.out.println(s.toString());
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

//        try {
//            laptopService.InsertDatatoDB("HP 15s du1077TX i7 10510U/8GB/512GB/2GB MX130/Win10 (1R8E3PA)",
//                        "https://www.dienmayxanh.com/laptop/hp-15s-du1077tx-i7-1r8e3pa",
//                        "HP",
//                        null,
//                        "8gb",
//                        "Intel Core i710510U Comet Lake",
//                        "512gb",
//                        null,
//                        18990000f,
//                        "NVIDIA Geforce MX130, 2GB",
//                        "1920 x 1080",
//                        15.6f,
//                        40);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }


    }
}
