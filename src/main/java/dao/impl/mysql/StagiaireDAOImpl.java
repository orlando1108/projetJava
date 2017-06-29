package dao.impl.mysql;

import dao.intf.StagiaireDAO;
import db.ConnectionFactory;
import modele.impl.FinancementStagiaire;
import modele.impl.Stagiaire;
import modele.intf.ICreneau;
import modele.intf.IFinancementStagiaire;
import modele.proxy.ProxyCreneau;
import modele.proxy.ProxyFinancement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by VTanchereau on 27/06/2017.
 */
public class StagiaireDAOImpl extends DAOImpl<Stagiaire> implements StagiaireDAO {

    private final String selectQuery = ""+
            "SELECT * FROM stagiaire "+
            "INNER JOIN personne ON stagiaire.idStagiaire = personne.idPersonne "+
            "INNER JOIN financementstagiaire ON stagiaire.idStagiaire = financementstagiaire.fk_stagiaire "+
            "INNER JOIN creneaustagiaire ON stagiaire.idStagiaire = creneaustagiaire.fk_stagiaire " +
            "GROUP BY stagiaire.idStagiaire, financementstagiaire.fk_financement, creneaustagiaire.fk_creneau";
    private final String insertPersonneQuery = "INSERT INTO personne (nom, prenom) VALUES (?, ?)";
    private final String insertStagiaireQuery = "INSERT INTO stagiaire (interne, idStagiaire) VALUES (?, ?)";
    private final String updatePersonneQuery = "UPDATE personne SET nom = ?, prenom = ? WHERE idPersonne = ?";
    private final String updateStagiaireQuery = "UPDATE stagiaire SET interne = ? WHERE idStagiaire = ?";
    private final String deleteStagiaireQuery = "DELETE FROM stagiaire WHERE idStagiaire = ?";
    private final String deletePersonneQuery = "DELETE FROM personne WHERE idPersonne = ?";

    public Stagiaire findById(int id){
        String selectQueryById = new StringBuilder().append(this.selectQuery).append(" WHERE idStagiaire = ?").toString();
        Stagiaire stagiaire = null;
        try (PreparedStatement stm = con.prepareStatement(selectQueryById)){
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            HashMap<Integer, List<Integer>> idStagiaireToListCreneaux = new HashMap<>();
            HashMap<Integer, List<Integer>> idStagiaireToListFS = new HashMap<>();

            while (result.next()){
                if (stagiaire == null) {
                    String nom = result.getString("personne.nom");
                    String prenom = result.getString("personne.prenom");
                    boolean interne = result.getBoolean("stagiaire.interne");
                    stagiaire = new Stagiaire(id, nom, prenom, interne);
                    idStagiaireToListCreneaux.put(id, new ArrayList<>());
                    idStagiaireToListFS.put(id, new ArrayList<>());
                }
                int idFinancement = result.getInt("financementStagiaire.fk_financement");
                if (!idStagiaireToListFS.get(id).contains(idFinancement)) {
                    LocalDate dateDebut = result.getDate("financementStagiaire.dateDebut").toLocalDate();
                    LocalDate dateFin = null;
                    try{
                        dateFin = result.getDate("financementStagiaire.dateFin").toLocalDate();
                    }catch (NullPointerException e){

                    }
                    FinancementStagiaire fs;
                    if (dateFin == null) {
                        fs = new FinancementStagiaire(stagiaire, new ProxyFinancement(idFinancement), dateDebut);
                    }else {
                        fs = new FinancementStagiaire(stagiaire, new ProxyFinancement(idFinancement), dateDebut, dateFin);
                    }
                    List<IFinancementStagiaire> listTemp = stagiaire.getListFinancementsStagiaires();
                    listTemp.add(fs);
                    stagiaire.setListFinancementsStagiaires(listTemp);
                    List<Integer> listTempIdFS = idStagiaireToListFS.get(id);
                    listTempIdFS.add(idFinancement);
                    idStagiaireToListFS.put(id, listTempIdFS);
                }
                int idCreneau = result.getInt("creneaustagiaire.fk_creneau");
                if(!idStagiaireToListCreneaux.get(id).contains(idCreneau)){
                    ICreneau proxyCreneau = new ProxyCreneau(idCreneau);
                    List<ICreneau> listTemp = stagiaire.getListCreneaux();
                    listTemp.add(proxyCreneau);
                    stagiaire.setListCreneaux(listTemp);
                    List<Integer> listTempIdCreneaux = idStagiaireToListCreneaux.get(id);
                    listTempIdCreneaux.add(idCreneau);
                    idStagiaireToListCreneaux.put(id, listTempIdCreneaux);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return stagiaire;
    }

    public List<Stagiaire> findAll(){
        HashMap<Integer, Stagiaire> idToStagiaire= new HashMap<>();
        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){

            ResultSet result = stm.executeQuery();

            HashMap<Integer, List<Integer>> idStagiaireToListCreneaux = new HashMap<>();
            HashMap<Integer, List<Integer>> idStagiaireToListFS = new HashMap<>();

            while (result.next()) {
                Stagiaire stagiaire;
                Integer id = result.getInt("stagiaire.idStagiaire");
                if (!idToStagiaire.keySet().contains(id)) {
                    String nom = result.getString("personne.nom");
                    String prenom = result.getString("personne.prenom");
                    boolean interne = result.getBoolean("stagiaire.interne");
                    stagiaire = new Stagiaire(id, nom, prenom, interne);
                    idStagiaireToListCreneaux.put(id, new ArrayList<>());
                    idStagiaireToListFS.put(id, new ArrayList<>());
                }else{
                    stagiaire = idToStagiaire.get(id);
                }
                int idFinancement = result.getInt("financementStagiaire.fk_financement");
                if (!idStagiaireToListFS.get(id).contains(idFinancement)) {
                    LocalDate dateDebut = result.getDate("financementStagiaire.dateDebut").toLocalDate();
                    LocalDate dateFin = null;
                    try{
                        dateFin = result.getDate("financementStagiaire.dateFin").toLocalDate();
                    }catch (NullPointerException e){

                    }
                    FinancementStagiaire fs;
                    if (dateFin == null) {
                        fs = new FinancementStagiaire(stagiaire, new ProxyFinancement(idFinancement), dateDebut);
                    }else {
                        fs = new FinancementStagiaire(stagiaire, new ProxyFinancement(idFinancement), dateDebut, dateFin);
                    }
                    List<IFinancementStagiaire> listTemp = stagiaire.getListFinancementsStagiaires();
                    listTemp.add(fs);
                    stagiaire.setListFinancementsStagiaires(listTemp);
                    List<Integer> listTempIdFS = idStagiaireToListFS.get(id);
                    listTempIdFS.add(idFinancement);
                    idStagiaireToListFS.put(id, listTempIdFS);
                }
                int idCreneau = result.getInt("creneaustagiaire.fk_creneau");
                if (!idStagiaireToListCreneaux.get(id).contains(idCreneau)) {
                    ProxyCreneau proxyCreneau = new ProxyCreneau(idCreneau);
                    List<ICreneau> listTemp = stagiaire.getListCreneaux();
                    listTemp.add(proxyCreneau);
                    stagiaire.setListCreneaux(listTemp);
                    List<Integer> listTempIdCreneaux = idStagiaireToListCreneaux.get(id);
                    listTempIdCreneaux.add(idCreneau);
                    idStagiaireToListCreneaux.put(id, listTempIdCreneaux);
                }
                idToStagiaire.put(id, stagiaire);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return new ArrayList<>(idToStagiaire.values());
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
