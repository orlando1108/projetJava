package modele;

import modele.impl.Formation;

import java.time.LocalDateTime;

/**
 * Created by VTanchereau on 28/06/2017.
 */
public class Creneau {

    private int id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private boolean interne;
    private Formation formation;

    public Creneau(int id, LocalDateTime dateDebut, LocalDateTime dateFin, boolean interne, Formation formation) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.interne = interne;
        this.formation = formation;
    }

    public Creneau(LocalDateTime dateDebut, LocalDateTime dateFin, boolean interne, Formation formation) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.interne = interne;
        this.formation = formation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isInterne() {
        return interne;
    }

    public void setInterne(boolean interne) {
        this.interne = interne;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
