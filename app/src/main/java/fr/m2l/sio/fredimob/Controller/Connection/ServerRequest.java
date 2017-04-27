package fr.m2l.sio.fredimob.Controller.Connection;

import fr.m2l.sio.fredimob.model.Frais;
import fr.m2l.sio.fredimob.model.User;

/**
 * Created by Alf on 04/04/2017.
 */

public class ServerRequest {

    private String operation;
    private User user;
    private Frais frais;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFrais(Frais frais) { this.frais = frais; }
}
