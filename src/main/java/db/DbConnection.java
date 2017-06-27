package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class DbConnection {

    private String url = "jdbc:mysql://localhost:3307/projetjava";
    private String user = "root";
    private String mdp = "";
    private Connection con;

    public Connection getCon(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }

        try {
            this.con = DriverManager.getConnection(this.url, this.mdp, this.mdp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.con;
    }
}