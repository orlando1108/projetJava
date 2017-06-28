package dao;

import dao.impl.mysql.ObjectifDAOImpl;
import dao.intf.DAO;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public abstract class DAOFactory {

    public static DAO getDAO(DAONames type){

        switch (type){
            case Objectif:
                return new ObjectifDAOImpl();

            default:
                return null;
        }
    }
}
