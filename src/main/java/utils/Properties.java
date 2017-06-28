package utils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class Properties {

    private java.util.Properties dbProperties;
    private static volatile Properties instance = null;

    public Properties() throws PropertiesException {
        this.dbProperties = new java.util.Properties();

        try (FileInputStream in = new FileInputStream("src/main/resources/db.properties")) {
            dbProperties.load(in);
            in.close();
        } catch (IOException e) {
            throw new PropertiesException("Impossible de lire le fichier src/main/resources/db.properties.", e);
        }
    }

    public static Properties getInstance() throws PropertiesException {
        if (Properties.instance == null){
            synchronized(Properties.class) {
                if (Properties.instance == null) {
                    try{
                        Properties.instance = new Properties();
                    }catch(PropertiesException e){
                        throw e;
                    }
                }
            }
        }
        return Properties.instance;
    }

    public java.util.Properties getDbProperties(){
        return this.dbProperties;
    }
}
