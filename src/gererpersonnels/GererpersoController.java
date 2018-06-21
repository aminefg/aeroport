/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gererpersonnels;

import com.sun.javafx.scene.control.skin.TextFieldSkin;
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
public class GererpersoController implements Initializable {
    @FXML
    private AnchorPane anchrgrp;

    @FXML
    private Button ajtp;
    @FXML
    private Button exit;
    @FXML
    private TableView<personnel> tablepersonnel;
    @FXML
    private TableColumn<personnel, String> idpers;
    @FXML
    private TableColumn<personnel, String> nom;
    @FXML
    private TableColumn<personnel, String> prenom;
    @FXML
    private TableColumn<personnel, Integer> age;
    
    private TableColumn<personnel, String> type;
    databs data; 
    @FXML
    private Button searchp;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            data= new databs();
            
        ArrayList<String> arp=new ArrayList<>(); 
        idpers.setCellValueFactory(new PropertyValueFactory<personnel,String>("idpers"));
        nom.setCellValueFactory(new PropertyValueFactory<personnel,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<personnel,String>("prenom"));
        age.setCellValueFactory(new PropertyValueFactory<personnel,Integer>("age"));
    
            arp=afficherpersonnel();
    }    
        @FXML
        private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchrgrp.getScene().getWindow();
        stag.close();
         }
        @FXML
        private void ajouterpersonnel(ActionEvent event) {
            String path="/addpersonnel/addpersonnel.fxml";
            loadwindow(path);
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
        private ArrayList<String> afficherpersonnel()
        {
                /* String sql="SELECT personnel.idperso , personnel.nom , personnel.prenom ,personnel.age , salaire.nbheure , naviguant.id_vol , naviguant.maxsomme FROM personnel , naviguant , salaire  WHERE naviguant.idperso=personnel.idperso AND personnel.idperso=salaire.idperso; ";*/
                 String sql="SELECT * from personnel";
                 String idp; String nom;String prnom;Integer age;
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 ArrayList<personnel> arr= new ArrayList<> ();
                 ArrayList<String> arrp=new ArrayList<> ();
                 boolean t=true;
               try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                    idp=res.getString(1);
                    arrp.add(idp);
                    nom=res.getString(2);
                    prnom=res.getString(3);
                    age=res.getInt(4);
                    arr.add(new personnel(idp, nom, prnom, age) {
                        @Override
                        protected double calculesalaire(int nbheure) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                   
                     t=res.next();

                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        ObservableList<personnel> navl = FXCollections.observableArrayList(arr);
                        tablepersonnel.setItems(navl);
                        
                   
                        return arrp;
        } 

  

    public TableView<personnel> getTablepersonnel() {
        return tablepersonnel;
    }

    public TableColumn<personnel, String> getIdpers() {
        return idpers;
    }

    public TableColumn<personnel, String> getNom() {
        return nom;
    }

    public TableColumn<personnel, String> getPrenom() {
        return prenom;
    }

    public TableColumn<personnel, Integer> getAge() {
        return age;
    }

    public TableColumn<personnel, String> getType() {
        return type;
    }

    @FXML
    private void searchpersonnel(ActionEvent event) {
        try{
            Parent parent=FXMLLoader.load(getClass().getResource("/gererpersonnels/rechercheperso.fxml"));
            Scene scene=new Scene(parent);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
              }
            catch(IOException e){e.printStackTrace();}
            
    }

         


}
