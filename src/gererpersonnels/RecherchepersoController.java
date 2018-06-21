/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gererpersonnels;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MainfxmlController;
import org.controlsfx.control.textfield.TextFields;
import project.database.databs;
public class RecherchepersoController implements Initializable  {
@FXML
    private TextField rechercheperso;
    @FXML
    private TableView<personnel> tablen;
    @FXML
    private TableColumn<personnel, String> idp;
    @FXML
    private TableColumn<personnel, String> nm;
    @FXML
    private TableColumn<personnel, String> prm;
    @FXML
    private TableColumn<personnel, Integer> ag;
    @FXML
    private TableColumn<naviguant, Integer> maxsm;
    @FXML
    private TableColumn<personnel,Double> salaire;
    databs data; 
    @FXML
    private TableColumn<personnel, String> type;
      @FXML
    private TableColumn<sol, Integer> nbheures;
    @FXML
    private Button rechercherp;
    @FXML
    private TableColumn<naviguant, String> idvol;
    @FXML
    private JFXButton exit;
    @FXML
    private AnchorPane anchrech;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data= new databs();
        ArrayList<String> arp= new ArrayList<>();
        idp.setCellValueFactory(new PropertyValueFactory<personnel,String>("idpers"));
        nm.setCellValueFactory(new PropertyValueFactory<personnel,String>("nom"));
        prm.setCellValueFactory(new PropertyValueFactory<personnel,String>("prenom"));
        ag.setCellValueFactory(new PropertyValueFactory<personnel,Integer>("age"));
        type.setCellValueFactory(new PropertyValueFactory<personnel,String>("type"));
        idvol.setCellValueFactory(new PropertyValueFactory<naviguant,String>("volid"));
        maxsm.setCellValueFactory(new PropertyValueFactory<naviguant,Integer>("maxsomme"));
        salaire.setCellValueFactory(new PropertyValueFactory<personnel,Double>("salaire"));
        nbheures.setCellValueFactory(new PropertyValueFactory<sol,Integer>("nbheures"));

        arp=listepersonnels();
        TextFields.bindAutoCompletion(rechercheperso, arp);
       
    }  
    private ArrayList<String> listepersonnels()
    {
         String sql="SELECT idperso FROM personnel   ; ";
                 
                 System.out.println(sql);
                 String idp; 
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 ArrayList<String> arrp=new ArrayList<> ();
                 ArrayList<personnel> arr=new ArrayList<>();
                 boolean t=true;
                try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                     idp=res.getString(1);
                     arrp.add(idp);
                     t=res.next();
                     
                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                return arrp;
    }
    
    private void affichernaviguant(String idperso,int nbvol)
        {
                 String sql="SELECT personnel.idperso,personnel.nom,personnel.prenom,personnel.age,naviguant.id_vol,naviguant.maxsomme FROM personnel,naviguant where naviguant.idperso=personnel.idperso and naviguant.idperso='"+idperso+"'  ; ";
                 
                 System.out.println(sql);
                 String idp; String nom;String prnom;Integer age;String idv;Integer maxsom; double sal;
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 ArrayList<naviguant> arrnavig= new ArrayList<> ();
             
                 boolean t=true;
                try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                    idp=res.getString(1);
                    nom=res.getString(2);
                    prnom=res.getString(3);
                    age=res.getInt(4);
                    idv=res.getString(5);
                    System.out.println(idv);
                    maxsom=res.getInt(6);
                    naviguant n=new naviguant(idp, nom, prnom, age, idv, maxsom);
                    sal=n.calculesalaire(nbvol);
                    naviguant np= new naviguant(idp, nom, prnom, age, idv, maxsom, sal);
                    arrnavig.add(np);
                     t=res.next();
                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        ObservableList<personnel> test = FXCollections.observableArrayList(arrnavig);
                        tablen.setItems(test);
        }
private void affichersol(String idperso)
        {
                 String sql="SELECT personnel.idperso,personnel.nom,personnel.prenom,personnel.age,sol.nbheures FROM personnel,sol where sol.idperso=personnel.idperso and sol.idperso='"+idperso+"'  ; ";
                 
                 System.out.println(sql);
                 String idp; String nom;String prnom;Integer age;String idv;Integer nbheures; double sal;
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 ArrayList<sol> arrsol= new ArrayList<> ();
             
                 boolean t=true;
                try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                    idp=res.getString(1);
                    nom=res.getString(2);
                    prnom=res.getString(3);
                    age=res.getInt(4);
                    nbheures=res.getInt(5);
                    sol n=new sol(idp, nom, prnom, age);
                    sal=n.calculesalaire(nbheures);
                    sol np=new sol(idp, nom, prnom, age, nbheures, sal);
                    arrsol.add(np);
                     t=res.next();
                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        ObservableList<personnel> test = FXCollections.observableArrayList(arrsol);
                        tablen.setItems(test);
                    
        }

public int typepersonne(String id)
    {
        String typ=""; int type = 0;
         String sql="SELECT naviguant.idperso FROM personnel,naviguant where personnel.idperso=naviguant.idperso and naviguant.idperso='"+id+"'  ; ";
                 
                 System.out.println(sql);
                 String idp; 
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 ArrayList<String> arrp=new ArrayList<> ();
                    
                 boolean t=true; 
                try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                     idp=res.getString(1);
                     System.out.println(idp);
                     if(!idp.equals(""))
                     {arrp.add(idp); System.out.println("test !!!!!!!      ");
                     type=1;
                     }
                     t=res.next();
                     
                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                return type;
        } 
   
public int calculenbvol(String id)
   {
        int nb = 0;
         String sql="SELECT COUNT(naviguant.id_vol) FROM `naviguant` WHERE naviguant.idperso='"+id+"' ; ";
                 
                 System.out.println(sql);
                 String idp; 
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 boolean t=true; 
                try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                     nb=res.getInt(1);
                     
                     t=res.next();
                     
                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                        }
       return nb;
   }
                        

    @FXML
    private void rechercheperso(ActionEvent event) {
       int nb; String id;int nbvol;
       id=rechercheperso.getText();
       nb=typepersonne(id);
       System.out.print(nb);
       if(nb==1)
       {   nbvol=calculenbvol(id);
           System.out.println("nbvol = "+nbvol);
           affichernaviguant(id,nb);}
       if(nb==0)
       affichersol(rechercheperso.getText());
    }

    @FXML
    private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchrech.getScene().getWindow();
        stag.close();
    }
        
    }
