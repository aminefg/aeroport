
package gererpersonnels;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public abstract class personnel {
        protected SimpleStringProperty idpers;
        protected SimpleStringProperty nom;
        protected SimpleStringProperty prenom;
        protected SimpleIntegerProperty age;
        protected String type;


    protected abstract double calculesalaire(int a);
    public void nimp()
    {
        
    }
    

    public personnel(String idpers, String nom, String prenom, Integer age) {
        this.idpers = new SimpleStringProperty(idpers);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.age = new SimpleIntegerProperty(age);
    }


       public String getIdpers() {
        return idpers.get();
    }

    public String getNom() {
        return nom.get();
    }

    public String getPrenom() {
        return prenom.get();
    }

    public Integer getAge() {
        return age.get();
    }
   /* public Integer getNbheure() {
        return nbheure.get();
    }*/

    public String getType() {
        return type=this.getClass().getSimpleName();
    }
        
}
