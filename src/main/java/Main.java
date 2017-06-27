import dao.mysql.ObjectifDAOImpl;
import db.DbConnection;
import modele.Objectif;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by VTanchereau on 27/06/2017.
 */
public class Main {

    public static void main(String [ ] args) {

        ObjectifDAOImpl objDaoImpl = new ObjectifDAOImpl();

        List<Objectif> list = new ArrayList<>();

        list.addAll(objDaoImpl.findAll());

        list.forEach((c)-> {
            System.out.println(c.toString());
        });
    }
}
