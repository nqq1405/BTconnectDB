package SearchLaptopFromReques.services;

import SearchLaptopFromReques.models.LaptopEntityClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Service {
    private Connection conn;

    public Service(Connection conn) {
        this.conn = conn;
    }

    public void EntityLaptop(ResultSet resultSet, List<LaptopEntityClass> laptopEntityClasses) throws SQLException {
        while (resultSet.next()) {
            laptopEntityClasses.add(new LaptopEntityClass(resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9),
                            Float.parseFloat(resultSet.getString(10)),
                            resultSet.getString(11),
                            resultSet.getString(12),
                            Float.parseFloat(resultSet.getString(13)),
                            Integer.parseInt(resultSet.getString(14))
                    )
            );
        }
    }

    //Follow price
    public List<LaptopEntityClass> FindLaptopFromPrice(Float price1, Float price2) {
        List<LaptopEntityClass> laptopEntityClasses = new ArrayList<LaptopEntityClass>();
        if (price2 == null && price1 == null) {
            return null;
        }
        String sql = "SELECT * FROM store_cms_plusplus.laptop WHERE price BETWEEN " + price1 + " AND " + price2 + ";";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            EntityLaptop(resultSet, laptopEntityClasses);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laptopEntityClasses;
    }

    //Follow Maker and ssd
    public List<LaptopEntityClass> FindLaptopFromSSD_MAKER(String maker, String ssd) {
        ArrayList<LaptopEntityClass> laptopEntityClasses = new ArrayList<LaptopEntityClass>();
        if (maker == null && ssd == null) {
            return null;
        }
        String sql1 = "SELECT * FROM store_cms_plusplus.laptop WHERE maker = '" + maker + "' AND ssd = '" + ssd + "';";
        String sql2 = "SELECT * FROM store_cms_plusplus.laptop WHERE maker =  '" + maker + "' OR ssd = '" + ssd + "';";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet;
            if (maker == null) {
                resultSet = statement.executeQuery(sql1);
            } else if (ssd == null) {
                resultSet = statement.executeQuery(sql1);
            } else {
                resultSet = statement.executeQuery(sql2);
            }

            EntityLaptop(resultSet, laptopEntityClasses);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laptopEntityClasses;
    }

    //Follow User From Fontend
    public List<LaptopEntityClass> FindLaptopFromReques(){
        List<LaptopEntityClass> laptopList = new ArrayList<>();
        String QuerryPrice = "WHERE price BETWEEN 10000000 AND 15000000;"; // or 3tr-10tr or 10tr-15tr or 15tr->
        String QuerryMakerAcer = "WHERE maker = 'acer';"; // or APPLE or HP or ASUS
        String QuerryScreenSize14inch = "WHERE screeen_size = 14"; // or 15,6 or 13
        String QuerryRam4gb = "WHERE ram = 4gb"; // or 8gb
        String QuerryCPUIntel = "WHERE cpu LIKE 'Intel%'"; // or intel pentium% or intel core%(i3i5i7)-(H,HQ,U,QM,M,MQ) or AMD ryzen%
        String QuerryType = "WHERE type LIKE 'macbook%'"; //or vivobook or acer or Nitro
        String QuerrySort = "ORDER BY price DESC"; // or Ram or Price or ssd or screen_size or sold or screen_resolution
        String QuerryCard = "WHERE card LIKE 'Intel%"; // or NVIDA%( GeForce GTX% - RTX) or AMD%

        String SqlQuerryToDB = "SELECT * FROM store_cms_plusplus.laptop" ;

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuerryToDB);

            EntityLaptop(resultSet, laptopList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laptopList;
    }

}


