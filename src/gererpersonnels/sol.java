/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gererpersonnels;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author tic
 */
public  class  sol extends personnel {
            protected SimpleIntegerProperty nbheures;
            
        protected SimpleDoubleProperty salaire;

    public Double getSalaire() {
        return salaire.get();
    }


    public sol(String idpers, String nom, String prenom, Integer age) {
        super(idpers, nom, prenom, age);
       

    }

    public sol(String idpers, String nom, String prenom, Integer age, Integer nbheures,Double salaire) {
        super(idpers, nom, prenom, age);
        this.nbheures=new SimpleIntegerProperty(nbheures);
        this.salaire=new SimpleDoubleProperty(salaire);

    }

    public Integer getNbheures() {
        return nbheures.get();
    }

  
    @Override
    public double calculesalaire(int nbheure)
    {
        return nbheure*6+100;
    }
    
}

