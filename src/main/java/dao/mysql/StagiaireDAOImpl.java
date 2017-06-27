package dao.mysql;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.StagiaireDAO;
import db.ConnectionFactory;
import modele.Stagiaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class StagiaireDAOImpl implements StagiaireDAO {

    private Connection con = ConnectionFactory.getConnection(
            ConnectionFactory.CERFA
    );

    private final String selectQuery = "SELECT * FROM stagiaire INNER JOIN personne ON stagiaire.idStagiaire = personne.idPersonne";
    private final String insertPersonneQuery = "INSERT INTO personne (nom, prenom) VALUES (?, ?)";
    private final String insertStagiaireQuery = "INSERT INTO stagiaire (interne, idStagiaire) VALUES (?, ?)";
    private final String updatePersonneQuery = "UPDATE personne SET nom = ?, prenom = ? WHERE idPersonne = ?";
    private final String updateStagiaireQuery = "UPDATE stagiaire SET interne = ? WHERE idStagiaire = ?";
    private final String deleteStagiaireQuery = "DELETE FROM stagiaire WHERE idStagiaire = ?";
    private final String deletePersonneQuery = "DELETE FROM personne WHERE idPersonne = ?";

    public Stagiaire selectById(int id){
        String selectQueryById = new StringBuilder().append(this.selectQuery).append(" WHERE idStagiaire = ?").toString();
        Stagiaire stagiaire = null;
        try (PreparedStatement stm = con.prepareStatement(selectQueryById)){
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            while (result.next()){
                String nom = result.getString("personne.nom");
                String prenom = result.getString("personne.prenom");
                boolean interne = result.getBoolean("stagiaire.interne");
                stagiaire = new Stagiaire(id, nom, prenom, interne);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return stagiaire;
    }

    public List<Stagiaire> selectAll(){
        List<Stagiaire> listStagiaire = new ArrayList<Stagiaire>();
        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){

            ResultSet result = stm.executeQuery();

            while (result.next()){
                int id = result.getInt("stagiaire.idStagiaire");
                String nom = result.getString("personne.nom");
                String prenom = result.getString("personne.prenom");
                boolean interne = result.getBoolean("stagiaire.interne");
                listStagiaire.add(new Stagiaire(id, nom, prenom, interne));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return listStagiaire;
    }

    public Stagiaire insert(Stagiaire stagiaire){
        int id = 0;
        try (PreparedStatement stm = con.prepareStatement(this.insertPersonneQuery)){
            stm.setString(1, stagiaire.getNom());
            stm.setString(2, stagiaire.getPrenom());
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        try (PreparedStatement stm = con.prepareStatement(this.insertStagiaireQuery)){
            stm.setBoolean(1, stagiaire.isInterne());
            stm.setInt(2, id);
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                stagiaire.setId(generatedKeys.getInt(1));
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return stagiaire;
    }

    public Stagiaire update(Stagiaire stagiaire){
        try (PreparedStatement stm = con.prepareStatement(this.updatePersonneQuery)){
            stm.setString(1, stagiaire.getNom());
            stm.setString(2, stagiaire.getPrenom());
            stm.setInt(3, stagiaire.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        try (PreparedStatement stm = con.prepareStatement(this.updateStagiaireQuery)){
            stm.setBoolean(1, stagiaire.isInterne());
            stm.setInt(2, stagiaire.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return stagiaire;
    }

    public int delete(Stagiaire stagiaire){
        try (PreparedStatement stm = con.prepareStatement(this.deleteStagiaireQuery)){
            stm.setInt(1, stagiaire.getId());

            stm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }

        try (PreparedStatement stm = con.prepareStatement(this.deletePersonneQuery)){
            stm.setInt(1, stagiaire.getId());

            stm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }

        return 1;
    }
}
