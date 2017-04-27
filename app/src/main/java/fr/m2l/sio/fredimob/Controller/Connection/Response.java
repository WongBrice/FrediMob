package fr.m2l.sio.fredimob.Controller.Connection;

import java.util.ArrayList;
import java.util.List;

import fr.m2l.sio.fredimob.model.Frais;

/**
 * Created by Alf on 25/04/2017.
 */

public class Response {
    private List<Frais> frais = new ArrayList<Frais>();

    public List<Frais> getFrais() {
        return frais;
    }
}
