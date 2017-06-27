package db;

import java.sql.Connection;

/**
 * Created by VTanchereau on 27/06/2017.
 */

public class ConnectionFactory {

    public static final String CERFA = "database";

    public static Connection getConnection(String type) {
        if (CERFA.equals(type)) {
            DbConnection con = new DbConnection();

            return con.getCon();
        }
        return null;
    }

}
