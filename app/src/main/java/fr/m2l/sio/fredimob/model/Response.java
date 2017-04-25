package fr.m2l.sio.fredimob.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alf on 25/04/2017.
 */

public class Response {
    private List<AndroidVersion> android = new ArrayList<AndroidVersion>();

    public List<AndroidVersion> getAndroid() {
        return android;
    }
}
