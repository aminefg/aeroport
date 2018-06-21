/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gererreservation;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;



public class reservation {
    private SimpleIntegerProperty numc;
    private SimpleStringProperty idvol;
    private SimpleStringProperty dateres;
    private SimpleStringProperty datevol;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty passport;

//    public  vol volair;
    
    
    
    
    public reservation(Integer numc, String idvol, String dateres, String datevol, String nom, String prenom, String passport) {
        this.numc = new SimpleIntegerProperty(numc);
        this.idvol = new SimpleStringProperty(idvol);
        this.dateres = new SimpleStringProperty(dateres);
        this.datevol = new SimpleStringProperty(datevol);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.passport = new SimpleStringProperty(passport);
    }

    public Integer getNumc() {
        return numc.get();
    }

    public String getIdvol() {
        return idvol.get();
    }

    public String getDateres() {
        return dateres.get();
    }

    public String getDatevol() {
        return datevol.get();
    }

    public String getNom() {
        return nom.get();
    }

    public String getPrenom() {
        return prenom.get();
    }

    public String getPassport() {
        return passport.get();
    }
    
}
