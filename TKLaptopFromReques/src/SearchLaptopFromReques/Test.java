package Service_TkLaptopFromReques;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

        Service service = new Service(connection);

        List<LaptopEntityClass> laptopEntityClassListprice = service.FindLaptopFromPrice(10000000f,40000000f);

        System.out.println("Laptop theo gía từ 10.000.000 => 40.000.000 là: ");
        for (LaptopEntityClass l : laptopEntityClassListprice) {
            l.DisplayName();
        }
        List<LaptopEntityClass> laptopEntityClassesListSSD_MAKER = service.FindLaptopFromSSD_MAKER("ACER","");
        System.out.println("Laptop theo hãng và ssd là:");
        for (LaptopEntityClass l: laptopEntityClassesListSSD_MAKER) {
            l.DisplayName();
        }
    }
}
