package db;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class DbConnection {

    private String url;
    private String user;
    private String psw;
    private String driver;
    private Connection con;

    public DbConnection () throws DbException{
        Properties dbProperties = new Properties();

        try (FileInputStream in = new FileInputStream("src/main/resources/db.properties")) {
            dbProperties.load(in);
            in.close();
            
            url = dbProperties.getProperty("jdbc.url");
            user = dbProperties.getProperty("jdbc.user");
            psw = dbProperties.getProperty("jdbc.psw");
            driver = dbProperties.getProperty("jdbc.driver");
        } catch (IOException e) {
            throw new DbException("Impossible de lire le fichier src/main/resources/db.properties.", e);
        }
    }

    public Connection getCon() throws DbException{

        try {
            Class.forName(this.driver);
        } catch (Exception e) {
            throw new DbException(new StringBuilder().append("Impossible de charger le driver ").append(this.driver).toString(), e);
        }

        try {
            this.con = DriverManager.getConnection(this.url, this.user, this.psw);
        } catch (Exception e) {
            throw new DbException("Impossible de se connecter à la base de donnée.", e);
        }

        return this.con;
    }
}