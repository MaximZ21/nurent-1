

import java.sql.*;
import com.mysql.jdbc.Driver;
class Connector {

    public Connection getConnection(){
            Connection conn = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://10.101.21.21:3306/library?autoReconnect=true&useSSL=false", "maxim", "maxim");
            } catch(Exception ex){
                System.out.println("Exception in Connector: "+ex.getMessage());
            }finally{
                return conn;
            }
    }
}
