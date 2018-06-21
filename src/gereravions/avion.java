/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gereravions;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class avion {
        protected SimpleStringProperty idavion;
        protected SimpleStringProperty nom;
        protected SimpleStringProperty marque;
        protected SimpleStringProperty compagnie;
        protected SimpleIntegerProperty maint;
        protected String type;


    public avion(String idavion, String nom, String marque, String compagnie, Integer maint) {
        this.idavion = new SimpleStringProperty(idavion);
        this.nom = new SimpleStringProperty(nom);
        this.marque = new SimpleStringProperty(marque);
        this.compagnie = new SimpleStringProperty(compagnie);
        this.maint = new SimpleIntegerProperty(maint);
    }

    public String getIdavion() {
        return idavion.get();
    }

    public String getNom() {
        return nom.get();
    }

    public String getMarque() {
        return marque.get();
    }

    public String getCompagnie() {
        return compagnie.get();
    }

    public Integer getMaint() {
        return maint.get();
    }

    public String getType() {
        return type=this.getClass().getSimpleName();
    }
    
        
    
}
