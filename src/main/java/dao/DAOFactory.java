package dao;

import dao.impl.mysql.*;
import dao.intf.DAO;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public abstract class DAOFactory {

    public static DAO getDAO(DAONames type){

        switch (type){
            case Objectif:
                return new ObjectifDAOImpl();
            case Creneau:
                return new CreneauDAOImpl();
            case Financement:
                return new FinancementDAOImpl();
            case Formateur:
                return new FormateurDAOImpl();
            case Formation:
                return new FormationDAOImpl();
            case Specialite:
                return new SpecialiteDAOImpl();
            case Stagiaire:
                return new StagiaireDAOImpl();

            default:
                return null;
        }
    }
}
