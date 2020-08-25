package Service_TkLaptopFromReques;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Service {
    private Connection conn;

    public Service(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<LaptopSvClass> FindLaptopFromPrice(float price1, float price2){
        ArrayList<LaptopSvClass> laptopSvClasses = new ArrayList<LaptopSvClass>();
        if(price2 == 0 && price1 == 0){
            return null;
        }
        String sql = "SELECT * FROM store_cms_plusplus.laptop WHERE price BETWEEN "+price1+" AND "+price2+";";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                laptopSvClasses.add(new LaptopSvClass(resultSet.getString(2),
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laptopSvClasses;
    }

    public ArrayList<LaptopSvClass> FindLaptopFromSSD_MAKER(String maker, String ssd){
        ArrayList<LaptopSvClass> laptopSvClasses = new ArrayList<LaptopSvClass>();
        if(maker == null && ssd == null){
            return null;
        }
        String sql1 = "SELECT * FROM store_cms_plusplus.laptop WHERE maker = '"+maker+"' AND ssd = '"+ssd+"';";
        String sql2 = "SELECT * FROM store_cms_plusplus.laptop WHERE maker =  '"+maker+"' OR ssd = '"+ssd+"';";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = null;
            if (maker == null){
                resultSet = statement.executeQuery(sql1);
            }else  if (ssd == null){
                resultSet = statement.executeQuery(sql1);
            }else{
                resultSet = statement.executeQuery(sql2);
            }

            while (resultSet.next()){
                laptopSvClasses.add(new LaptopSvClass(resultSet.getString(2),
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laptopSvClasses;
    }
}
