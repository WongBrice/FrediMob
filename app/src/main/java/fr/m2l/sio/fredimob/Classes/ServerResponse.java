package fr.m2l.sio.fredimob.Classes;

/**
 * Created by Alf on 04/04/2017.
 */

public class ServerResponse {
    private String result;
    private String message;
    private User user;
    private Frais frais;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
    public Frais getFrais() { return frais; }
}
