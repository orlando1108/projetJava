package dao.impl.mysql;

import dao.intf.FormateurDAO;
import db.ConnectionFactory;
import modele.impl.Formateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class FormateurDAOImpl implements FormateurDAO {
    private Connection con = ConnectionFactory.getConnection(
            ConnectionFactory.CERFA
    );

    private final String selectQuery = "SELECT * FROM formateur INNER JOIN personne ON formateur.idFormateur = personne.idPersonne";
    private final String insertPersonneQuery = "INSERT INTO personne (nom, prenom) VALUES (?, ?)";
    private final String insertFormateurQuery = "INSERT INTO formateur (interne, idFormateur) VALUES (?, ?)";
    private final String updatePersonneQuery = "UPDATE personne SET nom = ?, prenom = ? WHERE idPersonne = ?";
    private final String updateFormateurQuery = "UPDATE formateur SET interne = ? WHERE idFormateur = ?";
    private final String deleteFormateurQuery = "DELETE FROM formateur WHERE idFormateur = ?";
    private final String deletePersonneQuery = "DELETE FROM personne WHERE idPersonne = ?";

    public Formateur findById(int id){
        String selectQueryById = new StringBuilder().append(this.selectQuery).append(" WHERE idFormateur = ?").toString();
        Formateur formateur = null;
        try (PreparedStatement stm = con.prepareStatement(selectQueryById)){
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            while (result.next()){
                String nom = result.getString("personne.nom");
                String prenom = result.getString("personne.prenom");
                boolean interne = result.getBoolean("formateur.interne");
                formateur = new Formateur(id, nom, prenom, interne);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return formateur;
    }

    public List<Formateur> findAll(){
        List<Formateur> listFormateur = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){

            ResultSet result = stm.executeQuery();

            while (result.next()){
                int id = result.getInt("formateur.idFormateur");
                String nom = result.getString("personne.nom");
                String prenom = result.getString("personne.prenom");
                boolean interne = result.getBoolean("formateur.interne");
                listFormateur.add(new Formateur(id, nom, prenom, interne));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return listFormateur;
    }

    public Formateur insert(Formateur formateur){
        int id = 0;
        try (PreparedStatement stm = con.prepareStatement(this.insertPersonneQuery)){
            stm.setString(1, formateur.getNom());
            stm.setString(2, formateur.getPrenom());
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

        try (PreparedStatement stm = con.prepareStatement(this.insertFormateurQuery)){
            stm.setBoolean(1, formateur.isInterne());
            stm.setInt(2, id);
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                formateur.setId(generatedKeys.getInt(1));
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return formateur;
    }

    public Formateur update(Formateur formateur){
        try (PreparedStatement stm = con.prepareStatement(this.updatePersonneQuery)){
            stm.setString(1, formateur.getNom());
            stm.setString(2, formateur.getPrenom());
            stm.setInt(3, formateur.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        try (PreparedStatement stm = con.prepareStatement(this.updateFormateurQuery)){
            stm.setBoolean(1, formateur.isInterne());
            stm.setInt(2, formateur.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return formateur;
    }

    public int delete(Formateur formateur){
        try (PreparedStatement stm = con.prepareStatement(this.deleteFormateurQuery)){
            stm.setInt(1, formateur.getId());

            stm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }

        try (PreparedStatement stm = con.prepareStatement(this.deletePersonneQuery)){
            stm.setInt(1, formateur.getId());

            stm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }

        return 1;
    }
}
