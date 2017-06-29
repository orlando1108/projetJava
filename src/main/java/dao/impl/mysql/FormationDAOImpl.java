package dao.impl.mysql;

import dao.intf.FormationDAO;
import db.ConnectionFactory;
import modele.impl.Creneau;
import modele.impl.Formation;
import modele.impl.Objectif;
import modele.impl.Specialite;
import modele.intf.ICreneau;
import modele.intf.IObjectif;
import modele.intf.ISpecialite;
import modele.proxy.ProxyCreneau;
import modele.proxy.ProxyObjectif;
import modele.proxy.ProxySpecialite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class FormationDAOImpl extends DAOImpl<Formation> implements FormationDAO{

    private Connection con = ConnectionFactory.getConnection(
            ConnectionFactory.CERFA
    );

    private final String selectQuery = ""+
            "SELECT * FROM formation "+
            "INNER JOIN creneau ON formation.idFormation = creneau.fk_formation";
    private final String insertQuery = "INSERT INTO formation (nom, fk_specialite, fk_objectif) VALUES (?, ?, ?)";
    private final String updateQuery = "UPDATE formation SET nom = ?, fk_specialite = ?, fk_objectif = ? WHERE idFormation = ?";
    private final String deleteQuery = "DELETE FROM formation WHERE idFormation = ?";

    @Override
    public Formation findById(int id) {
        String selectQueryById = new StringBuilder().append(this.selectQuery).append(" WHERE idFormation = ?").toString();
        Formation formation = null;
        try (PreparedStatement stm = con.prepareStatement(selectQueryById)){
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            while (result.next()){

                if (formation == null) {
                    String nomFormation = result.getString("formation.nom");
                    int idObjectif = result.getInt("formation.fk_objectif");
                    int idSpecialite = result.getInt("formation.fk_specialite");
                    ISpecialite specialite = new ProxySpecialite(idSpecialite);
                    IObjectif objectif = new ProxyObjectif(idObjectif);
                    formation = new Formation(id, nomFormation, specialite, objectif);
                }
                int idCreneau = result.getInt("creneau.idCreneau");
                ICreneau creneau = new ProxyCreneau(idCreneau);
                List<ICreneau> listTemp = formation.getListCreneaux();
                listTemp.add(creneau);
                formation.setListCreneaux(listTemp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return formation;
    }

    @Override
    public List<Formation> findAll() {
        HashMap<Integer, Formation> idToFormation= new HashMap<>();

        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){
            ResultSet result = stm.executeQuery();

            while (result.next()){
                Formation formation;
                int id = result.getInt("objectif.idFormation");
                if (!idToFormation.containsKey(id)){
                    String nomFormation = result.getString("formation.nom");
                    int idObjectif = result.getInt("formation.fk_objectif");
                    int idSpecialite = result.getInt("formation.fk_specialite");
                    ISpecialite specialite = new ProxySpecialite(idSpecialite);
                    IObjectif objectif = new ProxyObjectif(idObjectif);
                    formation = new Formation(id, nomFormation, specialite, objectif);
                }else{
                    formation = idToFormation.get(id);
                }

                int idCreneau = result.getInt("creneau.idCreneau");
                ICreneau creneau = new ProxyCreneau(idCreneau);
                List<ICreneau> listTemp = formation.getListCreneaux();
                listTemp.add(creneau);
                formation.setListCreneaux(listTemp);

                idToFormation.put(id, formation);
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return new ArrayList<>(idToFormation.values());
    }

    @Override
    public Formation insert(Formation formation) {
        try (PreparedStatement stm = con.prepareStatement(this.insertQuery)){
            stm.setString(1, formation.getNom());
            stm.setInt(2, formation.getSpecialite().getId());
            stm.setInt(3, formation.getObjectif().getId());

            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                formation.setId(generatedKeys.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return formation;
    }

    @Override
    public Formation update(Formation formation) {
        try (PreparedStatement stm = con.prepareStatement(this.updateQuery)){
            stm.setString(1, formation.getNom());
            stm.setInt(2, formation.getSpecialite().getId());
            stm.setInt(3, formation.getObjectif().getId());
            stm.setInt(4, formation.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return formation;
    }

    @Override
    public int delete(Formation formation) {
        try (PreparedStatement stm = con.prepareStatement(this.deleteQuery)){
            stm.setInt(1, formation.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
