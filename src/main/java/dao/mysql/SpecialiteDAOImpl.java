package dao.mysql;

import dao.SpecialiteDAO;
import db.ConnectionFactory;
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
public class SpecialiteDAOImpl implements SpecialiteDAO{

    private Connection con = ConnectionFactory.getConnection(
            ConnectionFactory.CERFA
    );

    private final String selectQuery = "SELECT idSpecialite, nom, code FROM specialite";
    private final String insertQuery = "INSERT INTO specialite (nom, code) VALUES (?, ?)";
    private final String updateQuery = "UPDATE specialite SET nom = ?, code = ? WHERE idSpecialite = ?";
    private final String deleteQuery = "DELETE FROM specialite WHERE idSpecialite = ?";

    @Override
    public Specialite findById(int id) {
        String selectQueryById = new StringBuilder().append(this.selectQuery).append(" WHERE idSpecialite = ?").toString();
        Specialite specialite = null;
        try (PreparedStatement stm = con.prepareStatement(selectQueryById)){
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            while (result.next()){
                String nom = result.getString("nom");
                String code = result.getString("code");
                specialite = new Specialite(id, nom, code);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return specialite;
    }

    @Override
    public List<Specialite> findAll() {
        ArrayList<Specialite> listeSpecialites = new ArrayList<>();

        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){
            ResultSet result = stm.executeQuery();

            while (result.next()){
                int id = result.getInt("idSpecialite");
                String nom = result.getString("nom");
                String code = result.getString("code");

                listeSpecialites.add(new Specialite(id, nom, code));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return listeSpecialites;
    }

    @Override
    public Specialite insert(Specialite specialite) {
        try (PreparedStatement stm = con.prepareStatement(this.insertQuery)){
            stm.setString(1, specialite.getNom());
            stm.setString(2, specialite.getCode());

            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                specialite.setId(generatedKeys.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return specialite;
    }

    @Override
    public Specialite update(Specialite specialite) {
        try (PreparedStatement stm = con.prepareStatement(this.updateQuery)){
            stm.setString(1, specialite.getNom());
            stm.setString(2, specialite.getCode());
            stm.setInt(3, specialite.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return specialite;
    }

    @Override
    public int delete(Specialite specialite) {
        try (PreparedStatement stm = con.prepareStatement(this.deleteQuery)){
            stm.setInt(1, specialite.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
