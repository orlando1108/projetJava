package dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import db.ConnectionFactory;
import modele.Stagiaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class StagiaireDAOImpl implements StagiaireDAO {

    private Connection con = ConnectionFactory.getConnection(
            ConnectionFactory.CERFA
    );

    private final String selectQuery = "SELECT * FROM stagiaire INNER JOIN personne ON stagiaire.fk_personne = personne.idPersonne";
    private final String insertQuery = "INSERT INTO objectif (libelle) VALUES";
    private final String updateQuery = "UPDATE objectif SET libelle = ? WHERE idObjectif = ?";
    private final String deleteQuery = "DELETE FROM objectif WHERE idObjectif = ?";

    public Stagiaire selectById(int id){
        String selectQueryById = new StringBuilder().append(this.selectQuery).append(" WHERE idStagiaire = ?").toString();
        Stagiaire stagiaire = null;
        try {
            PreparedStatement stm = con.prepareStatement(selectQueryById);
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

        return objectif;
    }

    public List<Stagiaire> selectAll(){

    }

    public Stagiaire insert(Stagiaire objectif){

    }

    public Stagiaire update(Stagiaire objectif){

    }

    public int delete(Stagiaire objectif){

    }
}
