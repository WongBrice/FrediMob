package fr.m2l.sio.fredimob.Classes;

/**
 * Created by Alf on 05/04/2017.
 */

public class Frais {

    private String motif;
    private String trajet;
    private String trajetKM;
    private String trajetPeage;
    private String repas;
    private String heberg;

    public String getMotif() { return motif; }
    public String getTrajet() {
        return trajet;
    }
    public String getTrajetKM() {
        return trajetKM;
    }
    public String getTrajetPeage() {
        return trajetPeage;
    }
    public String getRepas() {
        return repas;
    }
    public String getHeberg() {
        return heberg;
    }

    public void setMotif(String motif) { this.motif = motif; }
    public void setTrajet(String trajet) { this.trajet = trajet; }
    public void setTrajetKM(String trajetKM) {
        this.trajetKM = trajetKM;
    }
    public void setTrajetPeage(String trajetPeage) {
        this.trajetPeage = trajetPeage;
    }
    public void setRepas(String repas) {
        this.repas = repas;
    }
    public void setHeberg(String heberg) {
        this.heberg = heberg;
    }

}
