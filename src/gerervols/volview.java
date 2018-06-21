/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerervols;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author tic
 */
public class volview {
    private SimpleStringProperty idvol;
    private SimpleStringProperty depart;
    private SimpleStringProperty arrivee;
    private SimpleStringProperty hdep;
    private SimpleStringProperty hariv;
    private SimpleStringProperty date;

    public volview(String idvol, String depart, String arrivee, String hdep, String hariv, String date) {
        this.idvol = new SimpleStringProperty(idvol);
        this.depart = new SimpleStringProperty(depart);
        this.arrivee = new SimpleStringProperty(arrivee);
        this.hdep = new SimpleStringProperty(hdep);
        this.hariv = new SimpleStringProperty(hariv);
        this.date = new SimpleStringProperty(date);
    }

    public String getIdvol() {
        return idvol.get();
    }

    public String getDepart() {
        return depart.get();
    }

    public String getArrivee() {
        return arrivee.get();
    }

    public String getHdep() {
        return hdep.get();
    }

    public String getHariv() {
        return hariv.get();
    }

    public String getDate() {
        return date.get();
    }
  
}
