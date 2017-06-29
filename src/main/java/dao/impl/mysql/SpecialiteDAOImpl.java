package dao.impl.mysql;

import dao.intf.SpecialiteDAO;
import modele.impl.Specialite;
import modele.intf.IFormation;
import modele.proxy.ProxyFormation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class SpecialiteDAOImpl extends DAOImpl<Specialite> implements SpecialiteDAO{

    private final String selectQuery = "" +
            "SELECT specialite.idSpecialite, specialite.nom, specialite.code, formation.idFormation FROM specialite " +
            "INNER JOIN formation ON formation.fk_specialite = specialite.idSpecialite";
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
                if(specialite == null) {
                    String nom = result.getString("nom");
                    String code = result.getString("code");
                    specialite = new Specialite(id, nom, code);
                }

                int idFormation = result.getInt("formation.IdFormation");
                IFormation formation = new ProxyFormation(idFormation);
                List<IFormation> listTemp = specialite.getListFormations();
                listTemp.add(formation);
                specialite.setListFormations(listTemp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return specialite;
    }

    @Override
    public List<Specialite> findAll() {
        HashMap<Integer, Specialite> idToSpecialite= new HashMap<>();
        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){
            ResultSet result = stm.executeQuery();

            while (result.next()){
                Specialite specialite;
                int id = result.getInt("idSpecialite");
                if(!idToSpecialite.containsKey(id)){
                    String nom = result.getString("nom");
                    String code = result.getString("code");
                    specialite = new Specialite(id, nom, code);
                }else{
                    specialite = idToSpecialite.get(id);
                }
                int idFormation = result.getInt("formation.IdFormation");
                IFormation formation = new ProxyFormation(idFormation);
                List<IFormation> listTemp = specialite.getListFormations();
                listTemp.add(formation);
                specialite.setListFormations(listTemp);
                idToSpecialite.put(id,specialite);
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return new ArrayList<>(idToSpecialite.values());
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
