package dao.impl.mysql;

import dao.intf.CreneauDAO;
import modele.impl.Creneau;
import modele.intf.IFormateur;
import modele.intf.IFormation;
import modele.intf.IStagiaire;
import modele.proxy.ProxyFormateur;
import modele.proxy.ProxyFormation;
import modele.proxy.ProxyStagiaire;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class CreneauDAOImpl extends DAOImpl<Creneau> implements CreneauDAO {

    private final String selectQuery =  "SELECT * FROM creneau " +
            "INNER JOIN creneauformateur ON creneau.idCreneau = creneauformateur.fk_creneau " +
            "INNER JOIN creneaustagiaire ON creneau.idCreneau = creneaustagiaire.fk_creneau";
    private final String insertQuery = "INSERT INTO creneau (dateDebut, dateFin, interne, fk_formation) VALUES (?, ?, ?, ?)";
    private final String updateQuery =  "UPDATE creneau SET dateDebut  = ?, " +
                                        "dateFin  = ?, interne = ?, fk_formation = ? WHERE idCreneau = ?";
    private final String deleteQuery = "DELETE FROM creneau WHERE idCreneau = ?";

    @Override
    public Creneau findById(int id) {
        String selectQueryById = new StringBuilder().append(this.selectQuery).append(" WHERE idCreneau = ?").toString();
        Creneau creneau = null;
        try (PreparedStatement stm = con.prepareStatement(selectQueryById)){
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            HashMap<Integer, List<Integer>> idCreneauToFormateur = new HashMap<>();
            HashMap<Integer, List<Integer>> idCreneauToStagiaire = new HashMap<>();

            while (result.next()){
                if (creneau == null){
                    LocalDateTime dateDebut = result.getTimestamp("creneau.dateDebut").toLocalDateTime();
                    LocalDateTime dateFin = result.getTimestamp("creneau.dateFin").toLocalDateTime();
                    boolean interne = result.getBoolean("creneau.interne");

                    int idFormation = result.getInt("creneau.fk_formation");
                    IFormation formation = new ProxyFormation(idFormation);

                    creneau = new Creneau(id, dateDebut, dateFin, interne, formation);
                    idCreneauToFormateur.put(id, new ArrayList<>());
                    idCreneauToStagiaire.put(id, new ArrayList<>());
                }

                int idFormateur = result.getInt("creneauformateur.fk_formateur");
                if(!idCreneauToFormateur.get(id).contains(idFormateur)){
                    IFormateur formateur = new ProxyFormateur(idFormateur);
                    List<IFormateur> listTemp = creneau.getListFormateurs();
                    listTemp.add(formateur);
                    creneau.setListFormateurs(listTemp);
                    List<Integer> listTempIdFormateur = idCreneauToFormateur.get(id);
                    listTempIdFormateur.add(idFormateur);
                    idCreneauToFormateur.put(id, listTempIdFormateur);
                }

                int idStagiaire = result.getInt("creneaustagiaire.fk_stagiaire");
                if(!idCreneauToStagiaire.get(id).contains(idStagiaire)){
                    IStagiaire stagiaire = new ProxyStagiaire(idStagiaire);
                    List<IStagiaire> listTemp = creneau.getListStagiaires();
                    listTemp.add(stagiaire);
                    creneau.setListStagiaires(listTemp);
                    List<Integer> listTempIdStagiaires = idCreneauToStagiaire.get(id);
                    listTempIdStagiaires.add(idStagiaire);
                    idCreneauToStagiaire.put(id, listTempIdStagiaires);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return creneau;
    }

    @Override
    public List<Creneau> findAll() {
        HashMap<Integer, Creneau> idToCreneau = new HashMap<>();

        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){
            ResultSet result = stm.executeQuery();

            HashMap<Integer, List<Integer>> idCreneauToFormateur = new HashMap<>();
            HashMap<Integer, List<Integer>> idCreneauToStagiaire = new HashMap<>();

            while (result.next()){
                Creneau creneau;
                Integer id = result.getInt("creneau.fk_formation");
                if (!idToCreneau.keySet().contains(id)) {
                    LocalDateTime dateDebut = result.getTimestamp("creneau.dateDebut").toLocalDateTime();
                    LocalDateTime dateFin = result.getTimestamp("creneau.dateFin").toLocalDateTime();
                    boolean interne = result.getBoolean("creneau.interne");

                    int idFormation = result.getInt("creneau.fk_formation");
                    IFormation formation = new ProxyFormation(idFormation);

                    creneau = new Creneau(id, dateDebut, dateFin, interne, formation);

                    idCreneauToFormateur.put(id, new ArrayList<>());
                    idCreneauToStagiaire.put(id, new ArrayList<>());
                }else{
                    creneau = idToCreneau.get(id);
                }

                int idFormateur = result.getInt("creneauformateur.fk_formateur");
                if(!idCreneauToFormateur.get(id).contains(idFormateur)){
                    IFormateur formateur = new ProxyFormateur(idFormateur);
                    List<IFormateur> listTemp = creneau.getListFormateurs();
                    listTemp.add(formateur);
                    creneau.setListFormateurs(listTemp);
                    List<Integer> listTempIdFormateur = idCreneauToFormateur.get(id);
                    listTempIdFormateur.add(idFormateur);
                    idCreneauToFormateur.put(id, listTempIdFormateur);
                }

                int idStagiaire = result.getInt("creneaustagiaire.fk_stagiaire");
                if(!idCreneauToStagiaire.get(id).contains(idStagiaire)){
                    IStagiaire stagiaire = new ProxyStagiaire(idStagiaire);
                    List<IStagiaire> listTemp = creneau.getListStagiaires();
                    listTemp.add(stagiaire);
                    creneau.setListStagiaires(listTemp);
                    List<Integer> listTempIdStagiaires = idCreneauToStagiaire.get(id);
                    listTempIdStagiaires.add(idStagiaire);
                    idCreneauToStagiaire.put(id, listTempIdStagiaires);
                }
                idToCreneau.put(id, creneau);
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return new ArrayList<>(idToCreneau.values());
    }

    @Override
    public Creneau insert(Creneau creneau) {
        try (PreparedStatement stm = con.prepareStatement(this.insertQuery)){
            stm.setDate(1, Date.valueOf(creneau.getDateDebut().toString()));
            stm.setDate(2, Date.valueOf(creneau.getDateFin().toString()));
            stm.setBoolean(3, creneau.isInterne());
            stm.setInt(4, creneau.getFormation().getId());

            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                creneau.setId(generatedKeys.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return creneau;
    }

    @Override
    public Creneau update(Creneau creneau) {
        try (PreparedStatement stm = con.prepareStatement(this.updateQuery)){
            stm.setDate(1, Date.valueOf(creneau.getDateDebut().toString()));
            stm.setDate(2, Date.valueOf(creneau.getDateFin().toString()));
            stm.setBoolean(3, creneau.isInterne());
            stm.setInt(4, creneau.getFormation().getId());
            stm.setInt(5, creneau.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return creneau;
    }

    @Override
    public int delete(Creneau creneau) {
        try (PreparedStatement stm = con.prepareStatement(this.deleteQuery)){
            stm.setInt(1, creneau.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
