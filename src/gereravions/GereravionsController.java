/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gereravions;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.database.databs;

public class GereravionsController implements Initializable {

    @FXML
    private AnchorPane anchravion;
    @FXML
    private TableColumn<avion, String> idav;
    @FXML
    private TableColumn<avion, String> nomav;
    @FXML
    private TableColumn<avion, String> marqueav;
    @FXML
    private TableColumn<avion,String> compav;
    @FXML
    private TableColumn<avion,Integer> maint;
    @FXML
    private TableColumn<avionmarchandise, Integer> nbpersav;
    @FXML
    private TableColumn<avionpassagers, Integer> nbpassg;
    @FXML
    private TableColumn<avionmarchandise, Integer> masse;
    @FXML
    private TableColumn<avionmarchandise, Integer> volmax;
    @FXML
    private TableColumn<avion,String> typeav;
    @FXML
    private TableColumn<avionpassagers, Integer> nbpersavp;
    databs data; 
    @FXML
    private TableView<avion> tableavion;
    @FXML
    private JFXComboBox<String> avionid;
    @FXML
    private JFXButton ajtavion;
    @FXML
    private JFXButton exit;
    @FXML
    private JFXButton actualiser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data= new databs();
         ArrayList<String> arrp=new ArrayList<> ();
         ArrayList<String> arp= new ArrayList<>();
        idav.setCellValueFactory(new PropertyValueFactory<avion,String>("idavion"));
        nomav.setCellValueFactory(new PropertyValueFactory<avion,String>("nom"));
        marqueav.setCellValueFactory(new PropertyValueFactory<avion,String>("marque"));
        compav.setCellValueFactory(new PropertyValueFactory<avion,String>("compagnie"));
        maint.setCellValueFactory(new PropertyValueFactory<avion,Integer>("maint"));
        typeav.setCellValueFactory(new PropertyValueFactory<avion,String>("type"));
        masse.setCellValueFactory(new PropertyValueFactory<avionmarchandise,Integer>("massemax"));
        volmax.setCellValueFactory(new PropertyValueFactory<avionmarchandise,Integer>("volumemax"));
        nbpersav.setCellValueFactory(new PropertyValueFactory<avionmarchandise,Integer>("nbperso"));
        nbpersavp.setCellValueFactory(new PropertyValueFactory<avionpassagers,Integer>("nbperso"));
        nbpassg.setCellValueFactory(new PropertyValueFactory<avionpassagers,Integer>("nbpassagers"));
        arp=afficheravion();
        ObservableList<String> test = FXCollections.observableArrayList(arp);
        avionid.setItems(test);

        
                }

     private ArrayList<String> afficheravion()
        {
                 String sql="SELECT * from avion ";
                 String ida; String nom;String marq;String comp; Integer mnt;
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 ArrayList<avion> arr= new ArrayList<> ();
                 ArrayList<String> arrp=new ArrayList<> ();
                 boolean t=true;
               try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                    ida=res.getString(1);
                    nom=res.getString(2); 
                    marq=res.getString(3);
                    comp=res.getString(4);
                    mnt=res.getInt(5);
                    arrp.add(ida);
                    arr.add(new avion(ida, nom, marq, comp, mnt));
                     t=res.next();
                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(GereravionsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
ObservableList<avion> avl = FXCollections.observableArrayList(arr);
                        tableavion.setItems(avl);
                        
                        return arrp;
        }
     public void  afficherpassagers(String ida)
        {
                 String sql="SELECT avion.id_avion,avion.nom,avion.marque,avion.compagnie,avion.maint,avion_passagers.nbrepassagers,avion_passagers.nbrepersonnel FROM avion,avion_passagers WHERE avion.id_avion=avion_passagers.id_avion AND avion_passagers.id_avion='"+ida+"' ";
                 String idav; String nom;String marq;String comp; Integer mnt,nbpsg,nbpers;
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 ArrayList<avionpassagers> arr= new ArrayList<> ();
                 ArrayList<String> arrp=new ArrayList<> ();
                 boolean t=true;
               try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                    idav=res.getString(1);
                    nom=res.getString(2); 
                    marq=res.getString(3);
                    comp=res.getString(4);
                    mnt=res.getInt(5);
                    nbpsg=res.getInt(6);
                    nbpers=res.getInt(7);
                    arr.add(new avionpassagers(idav, nom, marq, comp, mnt, nbpers, nbpsg));
                     t=res.next();
                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(GereravionsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ObservableList<avion> avl = FXCollections.observableArrayList(arr);
                        tableavion.setItems(avl);
                        
        }
       
     public void  affichermarchandise(String ida)
        {
                 String sql="SELECT avion.id_avion,avion.nom,avion.marque,avion.compagnie,avion.maint,avion_marchandise.masse_max, avion_marchandise.volume_max, avion_marchandise.nbrepersonnel  FROM avion,avion_marchandise WHERE avion.id_avion=avion_marchandise.id_avion AND avion_marchandise.id_avion='"+ida+"' ";
                 String idav; String nom;String marq;String comp; Integer mnt,masse,volume,nbpers;
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 ArrayList<avionmarchandise> arr= new ArrayList<> ();
                 ArrayList<String> arrp=new ArrayList<> ();
                 boolean t=true;
               try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                    idav=res.getString(1);
                    nom=res.getString(2); 
                    marq=res.getString(3);
                    comp=res.getString(4);
                    mnt=res.getInt(5);
                    masse=res.getInt(6);
                    volume=res.getInt(7);
                    nbpers=res.getInt(8);
                    arr.add(new avionmarchandise(idav, nom, marq, comp, mnt, masse, volume, nbpers));
                     t=res.next();
                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(GereravionsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ObservableList<avion> avl = FXCollections.observableArrayList(arr);
                        tableavion.setItems(avl);
                        
        }
     
     
     
    
public boolean typepavion(String id) /*verifie si une avion est une avion passager*/ 
    {
         int nb = 0;
         String sql="SELECT avion_passagers.id_avion FROM avion,avion_passagers where avion.id_avion=avion_passagers.id_avion and avion_passagers.id_avion='"+id+"'  ; ";
                 
                 System.out.println(sql);
                 String ida; 
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 ArrayList<String> arrp=new ArrayList<> ();
                    
                 boolean t=true; 
                try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                     ida=res.getString(1);
                     System.out.println(ida);
                     if(!ida.equals(""))
                     {arrp.add(ida); System.out.println("Avion passagers !!!!!!!      ");
                     nb++;
                     }
                     t=res.next();
                     
                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(GereravionsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                return nb==1;
        } 
    
  
    @FXML
    private void loadavion(MouseEvent event) {
        String id=avionid.getValue();
        if(typepavion(id))
        {afficherpassagers(id);}
        else if(!typepavion(id))
        {affichermarchandise(id);}
    }
private void loadwindow(String path)  {
            try{
            Parent parent=FXMLLoader.load(getClass().getResource(path));
            Scene scene=new Scene(parent);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
              }
            catch(IOException e){e.printStackTrace();}
            }
    @FXML
    private void ajouteravion(ActionEvent event) {
            String path="/gereravions/addavion.fxml";
            loadwindow(path);
    }

    @FXML
    private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchravion.getScene().getWindow();
        stag.close();
    }

    @FXML
    private void refresh(ActionEvent event) {
         ArrayList<String> arp= new ArrayList<>();
        arp=afficheravion();
        ObservableList<String> test = FXCollections.observableArrayList(arp);
        avionid.setItems(test);
    }
    
}
