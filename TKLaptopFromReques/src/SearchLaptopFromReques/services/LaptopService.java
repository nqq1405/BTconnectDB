package SearchLaptopFromReques.services;

import SearchLaptopFromReques.models.LaptopEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LaptopService {
    private Connection conn;

    public LaptopService(Connection conn) {
        this.conn = conn;
    }

    public List<LaptopEntity> EntityLaptop(String SqlQuerryToDB) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(SqlQuerryToDB);
        List<LaptopEntity> laptopEntities = new ArrayList<>();

        while (resultSet.next()) {
            laptopEntities.add(new LaptopEntity(resultSet.getString(2),
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
        return laptopEntities;
    }

    //Follow price
    public List<LaptopEntity> FindLaptopFromPrice(Float price1, Float price2) {
        List<LaptopEntity> laptopEntities = new ArrayList<LaptopEntity>();
        if (price2 == null && price1 == null) {
            return null;
        }
        String sql = "SELECT * FROM store_cms_plusplus.laptop WHERE price BETWEEN " + price1 + " AND " + price2 + ";";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            EntityLaptop(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laptopEntities;
    }

    //Follow Maker and ssd
    public List<LaptopEntity> FindLaptopFromSSD_MAKER(String maker, String ssd) {
        ArrayList<LaptopEntity> laptopEntities = new ArrayList<LaptopEntity>();
        if (maker == null && ssd == null) {
            return null;
        }
        String sql1 = "SELECT * FROM store_cms_plusplus.laptop WHERE maker = '" + maker + "' AND ssd = '" + ssd + "';";
        String sql2 = "SELECT * FROM store_cms_plusplus.laptop WHERE maker =  '" + maker + "' OR ssd = '" + ssd + "';";
        try {
            if (maker == null) {
                EntityLaptop(sql1);
            } else if (ssd == null) {
                EntityLaptop(sql1);
            } else {
                EntityLaptop(sql2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laptopEntities;
    }

    private String and(String sqlquerrywhere) {
        if (sqlquerrywhere.endsWith("WHERE")) {
            return " ";
        } else return "AND";
    }

    private String querryfromprice(float pricefrom, float priceto) {
        if (pricefrom == -1 && priceto == -1) {
            return "1 = 1";
        } else {
            if (pricefrom == -1) {
                return "price <= " + priceto;
            }
            if (priceto == -1) {
                return "price >= " + pricefrom;
            }
            return "price BETWEEN " + pricefrom + " AND " + priceto;
        }
    }

    //Follow User From Fontend
    public List<LaptopEntity> FindLaptopFromFilter(float pricefrom, float priceto, String maker, Float ScreenSize, String ram, String cpu, String type, String card, String selectsort, Boolean sort) {
        List<LaptopEntity> laptopList = new ArrayList<>();

        String SqlQuerryToDB = "SELECT * FROM store_cms_plusplus.laptop WHERE";
        if (pricefrom != -1 || priceto != -1) {
            SqlQuerryToDB += querryfromprice(pricefrom, priceto);
        }
        if (maker != null) {
            SqlQuerryToDB += and(SqlQuerryToDB) + "maker = '" + maker + "'";
        }
        if (ScreenSize != null){
            SqlQuerryToDB += and(SqlQuerryToDB) + "screen_size = '" + ScreenSize.floatValue() + "'";
        }
        if (ram.isEmpty()){
            SqlQuerryToDB += and(SqlQuerryToDB) + "screen_size = '" + ram + "'";
        }
        if (cpu.isEmpty()){
            SqlQuerryToDB += and(SqlQuerryToDB) + "cpu LIKE '%"+ cpu + "%'";
        }
        if (type.isEmpty()){
            SqlQuerryToDB += and(SqlQuerryToDB) + "type LIKE '%"+ type + "%'";
        }
        if (card.isEmpty()){
            SqlQuerryToDB += and(SqlQuerryToDB) + "card LIKE '%"+ card + "%'";
        }
        if (selectsort.isEmpty()){
            SqlQuerryToDB += "ORDER BY" + selectsort;
            if (sort || sort == null){
                SqlQuerryToDB += "ASC";
            }else if (sort == false){ SqlQuerryToDB += "DESC";}
        }
        SqlQuerryToDB +=";";
        try {
            laptopList = EntityLaptop(SqlQuerryToDB);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laptopList;
    }

    public List<LaptopEntity> FindLaptopsold() {
        List<LaptopEntity> laptopList = new ArrayList<>();
        String SqlQuerryToDB = "SELECT * FROM store_cms_plusplus.laptop ORDER BY sold DESC LIMIT 10";
        try {
           laptopList = EntityLaptop(SqlQuerryToDB);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laptopList;
    }

    public void DisplayName(List<LaptopEntity> laptopEntityList){
        for (LaptopEntity lt: laptopEntityList) {
            System.out.println(lt.getName());
        }
    }
}


