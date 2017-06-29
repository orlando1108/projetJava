package dao.impl.mysql;

import dao.intf.FinancementDAO;
import modele.impl.Financement;
import modele.impl.FinancementStagiaire;
import modele.intf.IFinancementStagiaire;
import modele.proxy.ProxyFinancement;
import modele.proxy.ProxyStagiaire;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class FinancementDAOImpl extends DAOImpl<Financement> implements FinancementDAO {

    private final String selectQuery = "" +
            "SELECT * FROM financement " +
            "INNER JOIN financementstagiaire ON financement.idFinancement = financementstagiaire.fk_financement";
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
                if (financement == null) {
                    String libelle = result.getString("financement.libelle");
                    financement = new Financement(id, libelle);
                }
                int idStagiaire = result.getInt("financementStagiaire.fk_stagiaire");
                LocalDate dateDebut = result.getDate("financementStagiaire.dateDebut").toLocalDate();
                LocalDate dateFin = null;
                try{
                    dateFin = result.getDate("financementStagiaire.dateFin").toLocalDate();
                }catch (NullPointerException e){

                }
                FinancementStagiaire fs;
                if (dateFin == null) {
                    fs = new FinancementStagiaire(new ProxyStagiaire(idStagiaire), financement, dateDebut);
                }else {
                    fs = new FinancementStagiaire(new ProxyStagiaire(idStagiaire), financement, dateDebut, dateFin);
                }
                List<IFinancementStagiaire> listTemp = financement.getListFinancementsStagiaires();
                listTemp.add(fs);
                financement.setListFinancementsStagiaires(listTemp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return financement;
    }

    @Override
    public List<Financement> findAll() {
        HashMap<Integer, Financement> idToFinancement = new HashMap<>();

        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)) {
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                Financement financement;
                Integer id = result.getInt("financement.idFinancement");
                if (idToFinancement.containsKey(id)) {
                    String libelle = result.getString("libelle");
                    financement = new Financement(id, libelle);
                } else {
                    financement = idToFinancement.get(id);
                }
                int idStagiaire = result.getInt("financementStagiaire.fk_stagiaire");
                LocalDate dateDebut = result.getDate("financementStagiaire.dateDebut").toLocalDate();
                LocalDate dateFin = null;
                try {
                    dateFin = result.getDate("financementStagiaire.dateFin").toLocalDate();
                } catch (NullPointerException e) {

                }
                FinancementStagiaire fs;
                if (dateFin == null) {
                    fs = new FinancementStagiaire(new ProxyStagiaire(idStagiaire), financement, dateDebut);
                } else {
                    fs = new FinancementStagiaire(new ProxyStagiaire(idStagiaire), financement, dateDebut, dateFin);
                }
                List<IFinancementStagiaire> listTemp = financement.getListFinancementsStagiaires();
                listTemp.add(fs);
                financement.setListFinancementsStagiaires(listTemp);

                idToFinancement.put(id, financement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return new ArrayList<>(idToFinancement.values());
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
