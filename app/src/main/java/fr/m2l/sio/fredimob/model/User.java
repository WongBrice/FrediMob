package fr.m2l.sio.fredimob.model;

/**
 * Created by Alf on 04/04/2017.
 */

public class User {

    protected String id;
    protected String name;
    protected String first_name;
    protected String last_name;
    protected String birthdate;
    protected String address;
    protected String city;
    protected String postal_code;
    protected String phone;
    protected String licence;
    protected String ligue;
    protected String type;
    protected String username;
    protected String email;
    protected String password;
    protected String old_password;
    protected String new_password;


    public String getName() {
        return name;
    }
    public String getFirstName() { return first_name; }
    public String getEmail() { return email; }

    public String getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setFirstName(String first_name) { this.first_name = first_name;}
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setPostalCode(String postal_code) {
        this.postal_code = postal_code;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setLicence(String licence) {
        this.licence = licence;
    }
    public void setLigue(String ligue) {
        this.ligue = ligue;
    }
    public void setType(String type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
