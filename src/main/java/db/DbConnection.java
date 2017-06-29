package db;
import utils.PropertiesException;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class DbConnection {

    private String url;
    private String user;
    private String psw;
    private String driver;
    private Connection con;
    private utils.Properties properties;

    public DbConnection () throws DbException{
        try {
            this.properties = utils.Properties.getInstance();
        } catch (PropertiesException e) {
            e.printStackTrace();
            throw new DbException("Impossible de lire le fichier src/main/resources/db.properties.", e);
        }
        url = this.properties.getDbProperties().getProperty("jdbc.url");
        user = this.properties.getDbProperties().getProperty("jdbc.user");
        psw = this.properties.getDbProperties().getProperty("jdbc.psw");
        driver = this.properties.getDbProperties().getProperty("jdbc.driver");
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