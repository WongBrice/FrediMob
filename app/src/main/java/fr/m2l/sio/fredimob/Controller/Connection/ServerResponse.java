package fr.m2l.sio.fredimob.Controller.Connection;

import fr.m2l.sio.fredimob.model.Frais;
import fr.m2l.sio.fredimob.model.User;

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
