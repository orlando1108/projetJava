package db;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class DbConnection {

    private String url = "jdbc:mysql://localhost:3307/projetjava";
    private String user = "root";
    private String mdp = "";
    private Connection connection;

    public Connection connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,mdp);
            return connection;
        } catch (Exception e) {
            return null;
        }
    }
}