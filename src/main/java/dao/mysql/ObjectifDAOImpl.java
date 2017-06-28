package dao.mysql;

import dao.ObjectifDAO;
import db.ConnectionFactory;
import modele.impl.Objectif;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */

public class ObjectifDAOImpl implements ObjectifDAO {

    private Connection con = ConnectionFactory.getConnection(
            ConnectionFactory.CERFA
    );

    private final String selectQuery = "SELECT idObjectif, libelle FROM objectif";
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
                String libelle = result.getString("libelle");
                objectif = new Objectif(id, libelle);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return objectif;
    }

    public List<Objectif> findAll(){
        ArrayList<Objectif> listeObjectif = new ArrayList<>();

        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){
            ResultSet result = stm.executeQuery();

            while (result.next()){
                int id = result.getInt("idObjectif");
                String libelle = result.getString("libelle");

                listeObjectif.add(new Objectif(id, libelle));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return listeObjectif;
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
