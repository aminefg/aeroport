/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gereravions;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class avionmarchandise extends avion {
        protected SimpleIntegerProperty massemax;
        protected SimpleIntegerProperty volumemax;
        protected SimpleIntegerProperty nbperso;

    public avionmarchandise(String idavion, String nom, String marque, String compagnie, Integer maint, Integer massemax,Integer volumemax) {
        super(idavion, nom, marque, compagnie, maint);
        this.massemax=new SimpleIntegerProperty(massemax);
        this.volumemax=new SimpleIntegerProperty(volumemax);
    }
    public avionmarchandise(String idavion, String nom, String marque, String compagnie, Integer maint, Integer massemax,Integer volumemax,Integer nbperso) {
        super(idavion, nom, marque, compagnie, maint);
        this.massemax=new SimpleIntegerProperty(massemax);
        this.volumemax=new SimpleIntegerProperty(volumemax);
        this.nbperso=new SimpleIntegerProperty(nbperso);

    }

    public Integer getMassemax() {
        return massemax.get();
    }

    public Integer getVolumemax() {
        return volumemax.get();
    }

    public Integer getNbperso() {
        return nbperso.get();
    }
    
        
}
