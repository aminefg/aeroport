/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class clientview {
    private SimpleIntegerProperty num;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty datenaiss;
    private SimpleStringProperty passport;

    public clientview(Integer num, String nom, String prenom, String datenaiss, String passport) {
        this.num = new SimpleIntegerProperty(num);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.datenaiss = new SimpleStringProperty(datenaiss);
        this.passport = new SimpleStringProperty(passport);
    }

    public Integer getNum() {
        return num.get();
    }

    public void setNum(SimpleIntegerProperty num) {
        this.num = num;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(SimpleStringProperty nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(SimpleStringProperty prenom) {
        this.prenom = prenom;
    }

    public String getDatenaiss() {
        return datenaiss.get();
    }

    public void setDatenaiss(SimpleStringProperty datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getPassport() {
        return passport.get();
    }

    public void setPassport(SimpleStringProperty passport) {
        this.passport = passport;
    }
    

    
}
