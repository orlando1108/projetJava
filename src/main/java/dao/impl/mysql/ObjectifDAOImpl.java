package dao.impl.mysql;

import dao.intf.ObjectifDAO;
import db.ConnectionFactory;
import modele.impl.Objectif;
import modele.intf.IFormation;
import modele.proxy.ProxyFormation;

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

public class ObjectifDAOImpl extends DAOImpl<Objectif> implements ObjectifDAO {

    private final String selectQuery = "" +
            "SELECT * FROM objectif " +
            "INNER JOIN formation ON formation.fk_objectif = objectif.idObjectif";
    private final String insertQuery = "INSERT INTO objectif (libelle) VALUES (?)";
    private final String updateQuery = "UPDATE objectif SET libelle = ? WHERE idObjectif = ?";
    private final String deleteQuery = "DELETE FROM objectif WHERE idObjectif = ?";

    public Objectif findById(int id) {
        String selectQueryById = new StringBuilder().append(this.selectQuery).append(" WHERE idObjectif = ?").toString();
        Objectif objectif = null;
        try (PreparedStatement stm = con.prepareStatement(selectQueryById)){
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            while (result.next()){
                if (objectif == null){
                    String libelle = result.getString("libelle");
                    objectif = new Objectif(id, libelle);
                }

                int idFormation = result.getInt("formation.idFormation");
                IFormation formation = new ProxyFormation(idFormation);
                List<IFormation> listTemp = objectif.getListFormations();
                listTemp.add(formation);
                objectif.setListFormations(listTemp);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return objectif;
    }

    public List<Objectif> findAll(){
        HashMap<Integer, Objectif> idToObjectif= new HashMap<>();

        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){
            ResultSet result = stm.executeQuery();

            while (result.next()){
                Objectif objectif;

                int id = result.getInt("idObjectif");

                if (!idToObjectif.containsKey(id)){
                    String libelle = result.getString("libelle");
                    objectif = new Objectif(id,libelle);
                }
                else{
                    objectif = idToObjectif.get(id);
                }

                int idFormation = result.getInt("formation.idFormation");
                IFormation formation = new ProxyFormation(idFormation);
                List<IFormation> listTemp = objectif.getListFormations();
                listTemp.add(formation);
                objectif.setListFormations(listTemp);

                idToObjectif.put(id, objectif);
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return new ArrayList<>(idToObjectif.values());
    }

    public Objectif insert(Objectif objectif) {
        try (PreparedStatement stm = con.prepareStatement(this.insertQuery)){
            stm.setString(1, objectif.getLibelle());

            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                objectif.setId(generatedKeys.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return objectif;
    }

    public Objectif update(Objectif objectif){
        try (PreparedStatement stm = con.prepareStatement(this.updateQuery)){
            stm.setString(1, objectif.getLibelle());
            stm.setInt(2, objectif.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return objectif;
    }

    public int delete(Objectif objectif){
        try (PreparedStatement stm = con.prepareStatement(this.deleteQuery)){
            stm.setInt(1, objectif.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
