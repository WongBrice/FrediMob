package fr.m2l.sio.fredimob.model;

/**
 * Created by Alf on 05/04/2017.
 */

public class Frais {

    private String id;
    private String trajet;
    private String km;
    private String peage;
    private String repas;
    private String heberg;
    private String motif;
    private String cout;
    private String createdAt;

    public String getId() {
        return id;
    }
    public String getTrajet() {
        return trajet;
    }
    public String getKm() {
        return km;
    }
    public String getPeage() {
        return peage;
    }
    public String getRepas() {
        return repas;
    }
    public String getHeberg() {
        return heberg;
    }
    public String getMotif() {
        return motif;
    }
    public String getCout() {
        return cout;
    }
    public String getCreatedAt() { return createdAt; }

    public void setTrajet(String trajet) { this.trajet = trajet; }
    public void setKm(String km) { this.km = km; }
    public void setPeage(String peage) { this.peage = peage; }
    public void setRepas(String repas) { this.repas = repas; }
    public void setHeberg(String heberg) { this.heberg = heberg; }
    public void setMotif(String motif) { this.motif = motif; }
    public void setCout(String cout) { this.cout = cout; }
}
