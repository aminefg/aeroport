
package gererpersonnels;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public  class naviguant extends personnel {
    
        protected SimpleStringProperty volid;
        protected SimpleIntegerProperty maxsomme;
        protected SimpleDoubleProperty salaire;


    public naviguant(String idpers, String nom, String prenom, Integer age, String volid,Integer maxsomme) {
        super(idpers, nom, prenom, age);
        this.volid=new SimpleStringProperty(volid);
        this.maxsomme=new SimpleIntegerProperty(maxsomme);
    }
    public naviguant(String idpers, String nom, String prenom, Integer age, String volid,Integer maxsomme , Double salaire) {
        super(idpers, nom, prenom, age);
        this.volid=new SimpleStringProperty(volid);
        this.maxsomme=new SimpleIntegerProperty(maxsomme);
        this.salaire=new SimpleDoubleProperty(salaire);
       
    }
    @Override
    public double calculesalaire(int nbvol)
    {
        return nbvol*200+50;
    }

    public String getVolid() {
        return volid.get();
    }

    public Double getSalaire() {
        return salaire.get();
    }

    public Integer getMaxsomme() {
        return maxsomme.get();
    }


    
}
