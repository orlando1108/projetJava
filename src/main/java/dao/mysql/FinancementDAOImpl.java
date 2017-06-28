package dao.mysql;

import dao.FinancementDAO;
import db.ConnectionFactory;
import modele.Financement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class FinancementDAOImpl implements FinancementDAO {

    private Connection con = ConnectionFactory.getConnection(
            ConnectionFactory.CERFA
    );

    private final String selectQuery = "SELECT idFinancement, libelle FROM financement";
    private final String insertQuery = "INSERT INTO financement (libelle) VALUES (?)";
    private final String updateQuery = "UPDATE financement SET libelle = ? WHERE idFinancement = ?";
    private final String deleteQuery = "DELETE FROM financement WHERE idFinancement = ?";


    @Override
    public Financement findById(int id) {
        String selectQueryById = new StringBuilder().append(this.selectQuery).append(" WHERE idFinancement  = ?").toString();
        Financement financement = null;
        try (PreparedStatement stm = con.prepareStatement(selectQueryById)){
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            while (result.next()){
                String libelle = result.getString("libelle");
                financement = new Financement(id, libelle);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return financement;
    }

    @Override
    public List<Financement> findAll() {
        ArrayList<Financement> listeFinancement = new ArrayList<>();

        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){
            ResultSet result = stm.executeQuery();

            while (result.next()){
                int id = result.getInt("idFinancement");
                String libelle = result.getString("libelle");

                listeFinancement.add(new Financement(id, libelle));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return listeFinancement;
    }

    @Override
    public Financement insert(Financement financement) {
        try (PreparedStatement stm = con.prepareStatement(this.insertQuery)){
            stm.setString(1, financement.getLibelle());

            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                financement.setId(generatedKeys.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return financement;
    }

    @Override
    public Financement update(Financement financement) {
        try (PreparedStatement stm = con.prepareStatement(this.updateQuery)){
            stm.setString(1, financement.getLibelle());
            stm.setInt(2, financement.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return financement;
    }

    @Override
    public int delete(Financement financement) {
        try (PreparedStatement stm = con.prepareStatement(this.deleteQuery)){
            stm.setInt(1, financement.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
