package SearchLaptopFromReques.services;

import SearchLaptopFromReques.models.Counter;
import SearchLaptopFromReques.models.LaptopEntity;
import SearchLaptopFromReques.models.Statistic;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LaptopService {
    private Connection conn;

    public LaptopService(Connection conn) {
        this.conn = conn;
    }

    // enter LaptopEntity
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

    // enter CounterEntity
    public List<Counter> EntityCounter(String SqlQuerryToDB) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(SqlQuerryToDB);
        List<Counter> counterEntities = new ArrayList<>();

        while (resultSet.next()) {
            counterEntities.add(new Counter(resultSet.getString("maker"),
                            resultSet.getString("quantity")
                    ));
        }
        return counterEntities;
    }

    // enter StatisticEntity
    public List<Statistic> EntityStatistic(String SqlQuerryToDB) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(SqlQuerryToDB);
        List<Statistic> StatisticEntities = new ArrayList<>();

        while (resultSet.next()) {
            StatisticEntities.add(new Statistic(resultSet.getString("maker"),
                    resultSet.getString("sold"),
                    (Double.parseDouble(resultSet.getString("sold"))* Double.parseDouble(resultSet.getString("price")))
            ));
        }
        return StatisticEntities;
    }

    //Follow price
    public List<LaptopEntity> FindLaptopFromPrice(Float price1, Float price2) throws SQLException {

        if (price2 == null && price1 == null) {
            return null;
        }
        String sql = "SELECT * FROM store_cms_plusplus.laptop WHERE price BETWEEN " + price1 + " AND " + price2 + ";";

        return EntityLaptop(sql);
    }

    //Follow Maker and ssd
    public List<LaptopEntity> FindLaptopFromSSD_MAKER(String maker, String ssd) {
        List<LaptopEntity> laptopEntities = new ArrayList<LaptopEntity>();
        if (maker == null && ssd == null) {
            return null;
        }
        String sql1 = "SELECT * FROM store_cms_plusplus.laptop WHERE maker = '" + maker + "' AND ssd = '" + ssd + "';";
        String sql2 = "SELECT * FROM store_cms_plusplus.laptop WHERE maker =  '" + maker + "' OR ssd = '" + ssd + "';";
        try {
            if (maker == null) {
                laptopEntities = EntityLaptop(sql1);
            } else if (ssd == null) {
                laptopEntities = EntityLaptop(sql1);
            } else {
                laptopEntities = EntityLaptop(sql2);
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

    public List<Counter> getCounterByMaker() throws SQLException {
        return EntityCounter("SELECT maker, `type`, COUNT(`type`) As quantity FROM `store_cms_plusplus`.laptop GROUP BY `type` ORDER BY quantity DESC;");
    }

    public List<Statistic>  getStatisticByMaker() throws SQLException {
        return EntityStatistic("SELECT maker, sold, price FROM `store_cms_plusplus`.laptop GROUP BY `maker`;");
    }

    public void DisplayName(List<LaptopEntity> laptopEntityList) {
        for (LaptopEntity lt : laptopEntityList) {
            System.out.println(lt.getName());
        }
    }

    public void InsertDatatoDB(String name, String url, String maker, String type, String ram, String cpu, String ssd,
                               String hdd, Float price, String card, String screen_resolution, Float screen_size,
                               Integer sold) throws SQLException {

        String sql = "insert ignore into `store_cms_plusplus`.laptop(`name`, url, maker, `type`, ram, `cpu`, ssd, hdd, price, card, screen_resolution, screen_size, sold)" +
                "values('"+name+"','"+url+"','"+maker+"','"+type+"','"+ram+"','"+cpu+"','"+ssd+"','"+hdd+"','"+price+"','"+card+"','"+screen_resolution+"','"+screen_size+"','"+sold+"');";
        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    public LaptopEntity UpdateData(String idlaptop, int increasesold){
        Statement statement = null;
        LaptopEntity update = null;
        try {
            String sql1 = "select name, sold, last_updated_timestamp form  `store_cms_plusplus`.laptop where id = "+ idlaptop +";";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql1);

            String sql = "update `store_cms_plusplus`.laptop set sold ="+(increasesold + resultSet.getInt("sold"))+ "where id = "+idlaptop+";";
            statement.execute(sql);

            resultSet = statement.executeQuery(sql1);
            update = new LaptopEntity(resultSet.getString("name"),resultSet.getInt("sold"),resultSet.getTimestamp("last_update_timestamp"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update;
    }
}


