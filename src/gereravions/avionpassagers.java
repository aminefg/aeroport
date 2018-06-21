/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gereravions;

import javafx.beans.property.SimpleIntegerProperty;

public class avionpassagers extends avion {
    protected SimpleIntegerProperty nbperso;
    protected SimpleIntegerProperty nbpassagers;

    public avionpassagers(String idavion, String nom, String marque, String compagnie, Integer maint, Integer nbperso,Integer nbpassagers) {
        super(idavion, nom, marque, compagnie, maint);
        this.nbperso=new SimpleIntegerProperty(nbperso);
        this.nbpassagers=new SimpleIntegerProperty(nbpassagers);
    }

    public Integer getNbperso() {
        return nbperso.get();
    }

    public Integer getNbpassagers() {
        return nbpassagers.get();
    }
    
    
    
    
}
