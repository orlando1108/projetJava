package dao.mysql;

import dao.CreneauDAO;
import db.ConnectionFactory;
import modele.Creneau;
import modele.Formation;
import modele.impl.Objectif;
import modele.Specialite;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class CreneauDAOImpl implements CreneauDAO {

    private Connection con = ConnectionFactory.getConnection(
            ConnectionFactory.CERFA
    );

    private final String selectQuery =  "SELECT * FROM creneau " +
            "INNER JOIN " +
            "formation ON creneau.fk_formation = formation.idFormation " +
            "INNER JOIN " +
            "specialite ON formation.fk_specialite = specialite.idSpecialite " +
            "INNER JOIN " +
            "objectif ON formation.fk_objectif = objectif.idObjectif";
    private final String insertQuery = "INSERT INTO creneau (dateDebut, dateFin, interne, fk_formation) VALUES (?, ?, ?, ?)";
    private final String updateQuery =  "UPDATE creneau SET dateDebut  = ?, " +
                                        "dateFin  = ?, interne = ?, fk_formation = ? WHERE idCreneau = ?";
    private final String deleteQuery = "DELETE FROM creneau WHERE idCreneau = ?";

    @Override
    public Creneau findById(int id) {
        String selectQueryById = new StringBuilder().append(this.selectQuery).append(" WHERE idObjectif = ?").toString();
        Creneau creneau = null;
        try (PreparedStatement stm = con.prepareStatement(selectQueryById)){
            stm.setInt(1, id);

            ResultSet result = stm.executeQuery();

            while (result.next()){
                LocalDateTime dateDebut = result.getObject("creneau.dateDebut", LocalDateTime.class);
                LocalDateTime dateFin = result.getObject("creneau.dateFin", LocalDateTime.class);
                boolean interne = result.getBoolean("creneau.interne");

                int idFormation = result.getInt("formation.idFormation");
                String nomFormation = result.getString("formation.nom");

                int idObjectif = result.getInt("objectif.idObjectif");
                String libelleObjectif = result.getString("objectif.libelle");

                int idSpecialite = result.getInt("specialite.idSpecialite");
                String nomSpecialite = result.getString("specialite.nom");
                String codeSpecialite = result.getString("specialite.code");

                Formation formation = new Formation(id, nomFormation, new Specialite(idSpecialite, nomSpecialite, codeSpecialite), new Objectif(idObjectif, libelleObjectif));

                creneau = new Creneau(id, dateDebut, dateFin, interne, formation);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return creneau;
    }

    @Override
    public List<Creneau> findAll() {
        ArrayList<Creneau> listeCreneau = new ArrayList<>();

        try (PreparedStatement stm = con.prepareStatement(this.selectQuery)){
            ResultSet result = stm.executeQuery();

            while (result.next()){
                int id = result.getInt("idObjectif");
                LocalDateTime dateDebut = result.getObject("creneau.dateDebut", LocalDateTime.class);
                LocalDateTime dateFin = result.getObject("creneau.dateFin", LocalDateTime.class);
                boolean interne = result.getBoolean("creneau.interne");

                int idFormation = result.getInt("formation.idFormation");
                String nomFormation = result.getString("formation.nom");

                int idObjectif = result.getInt("objectif.idObjectif");
                String libelleObjectif = result.getString("objectif.libelle");

                int idSpecialite = result.getInt("specialite.idSpecialite");
                String nomSpecialite = result.getString("specialite.nom");
                String codeSpecialite = result.getString("specialite.code");

                Formation formation = new Formation(id, nomFormation, new Specialite(idSpecialite, nomSpecialite, codeSpecialite), new Objectif(idObjectif, libelleObjectif));

                listeCreneau.add(new Creneau(id, dateDebut, dateFin, interne, formation));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return listeCreneau;
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
