import dao.impl.mysql.ObjectifDAOImpl;
import modele.impl.Objectif;

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
