package dao.impl.mysql;

import dao.intf.FormationDAO;
import db.ConnectionFactory;
import modele.Formation;
import modele.impl.Objectif;
import modele.Specialite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class FormationDAOImpl extends DAOImpl<Formation> implements FormationDAO{

    private Connection con = ConnectionFactory.getConnection(
            ConnectionFactory.CERFA
    );

    private final String selectQuery = "SELECT * FROM formation INNER JOIN specialite ON formation.fk_specialite = specialite.idSpecialite INNER JOIN objectif ON formation.fk_objectif = objectif.idObjectif";
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
                String nomFormation = result.getString("formation.nom");
                int idObjectif = result.getInt("objectif.idObjectif");
                String libelleObjectif = result.getString("objectif.libelle");
                int idSpecialite = result.getInt("specialite.idSpecialite");
                String nomSpecialite = result.getString("specialite.nom");
                String codeSpecialite = result.getString("specialite.code");

                formation = new Formation(id, nomFormation, new Specialite(idSpecialite, nomSpecialite, codeSpecialite), new Objectif(idObjectif, libelleObjectif));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return formation;
    }

    @Override
    public List<Formation> findAll() {
        ArrayList<Formation> listeFormations = new ArrayList<>();

        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){
            ResultSet result = stm.executeQuery();

            while (result.next()){
                int idFormation = result.getInt("objectif.idFormation");
                String nomFormation = result.getString("formation.nom");
                int idObjectif = result.getInt("objectif.idObjectif");
                String libelleObjectif = result.getString("objectif.libelle");
                int idSpecialite = result.getInt("specialite.idSpecialite");
                String nomSpecialite = result.getString("specialite.nom");
                String codeSpecialite = result.getString("specialite.code");

                listeFormations.add(new Formation(idFormation, nomFormation, new Specialite(idSpecialite, nomSpecialite, codeSpecialite), new Objectif(idObjectif, libelleObjectif)));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return listeFormations;
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
