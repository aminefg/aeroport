/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gererreservation;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.database.databs;

/**
 * FXML Controller class
 *
 * @author tic
 */
public class GererreservationController implements Initializable {

    @FXML
    private AnchorPane anchrres;
    @FXML
    private Button ajtres;
    @FXML
    private Button annuler;
    @FXML
    private TableView<reservation> tableres;
    @FXML
    private TableColumn<reservation, Integer> numc;
    @FXML
    private TableColumn<reservation, String> idv;
    @FXML
    private TableColumn<reservation, String> datres;
    @FXML
    private TableColumn<reservation, String> datvol;
    @FXML
    private TableColumn<reservation, String> nom;
    @FXML
    private TableColumn<reservation, String> prenom;
    @FXML
    private TableColumn<reservation, String> passp;
    databs data;
    @FXML
    private Button actualiser;
    @FXML
    private JFXButton exit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = new databs();
        numc.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("numc"));
        idv.setCellValueFactory(new PropertyValueFactory<reservation, String>("idvol"));
        datres.setCellValueFactory(new PropertyValueFactory<reservation, String>("dateres"));
        datvol.setCellValueFactory(new PropertyValueFactory<reservation, String>("datevol"));
        nom.setCellValueFactory(new PropertyValueFactory<reservation, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<reservation, String>("prenom"));
        passp.setCellValueFactory(new PropertyValueFactory<reservation, String>("passport"));
        affichereservation();


    }
    @FXML
        private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchrres.getScene().getWindow();
        stag.close();
         }
public void affichereservation() 
{
    String sql="SELECT reservation.num,reservation.id_vol,reservation.dateres,vol.datevol,client.nom,client.prenom,client.passeport FROM reservation,vol,client  WHERE reservation.num=client.num and reservation.id_vol=vol.id_vol";
                 Integer numc;String idvol;String dateres;String datevol; String nom;String prnom;String passeport;
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res; 
                 ArrayList<reservation> arr= new ArrayList<> ();
                 boolean t=true;
                          try {
                res = data.ExecuteAct(sql);
                 while(t )
                 {
                    numc=res.getInt(1);
                    idvol=res.getString(2);
                    System.out.println(idvol);
                    dateres=res.getString(3);
                    datevol=res.getString(4);
                    nom=res.getString(5);
                    prnom=res.getString(6);
                    passeport=res.getString(7);
                    System.out.println(passeport);
                    arr.add(new reservation(numc, idvol, dateres, datevol, nom, prnom, passeport));
                     t=res.next();
                 }
                
                  } catch (SQLException ex) {
            Logger.getLogger(GererreservationController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        ObservableList<reservation> rest = FXCollections.observableArrayList(arr);
                        tableres.setItems(rest);
}

    @FXML
    private void ajouterreservation(ActionEvent event) {
           try{
            Parent parent=FXMLLoader.load(getClass().getResource("/gererreservation/ajouterreservation.fxml"));
            Scene scene=new Scene(parent);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
              }
            catch(IOException e){e.printStackTrace();}
    }

    @FXML
    private void annulerreservation(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Please Confirm Delete Action");
        alert.setHeaderText("A confirmation is needed to delete the reservation");
        alert.setContentText("Choose your option.");
        ButtonType buttonTypeOne = new ButtonType("Delete");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
        int selectedid;
        ObservableList<reservation> selectedclient, allClients;
        allClients = tableres.getItems();
        selectedclient=tableres.getSelectionModel().getSelectedItems();
        selectedid = tableres.getSelectionModel().getSelectedItem().getNumc();
        selectedclient.forEach(allClients::remove);
        DeleteSelected(selectedid);
                } 
    }
    @FXML
    private void refresh(ActionEvent event) {
        affichereservation();
    }
    public void DeleteSelected(int a )
        {
            String sql="DELETE FROM `reservation` where reservation.num ='"+a+"';";
        try {
            if(Verif(sql))
            {
                System.out.println("Reservation deleted Successfully !!");
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Client DELETED");
                alert.setContentText("*****************Client is deleted !!!!********* ");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else {
                System.out.println("Query cant be Executed !!");
                Alert alt=new Alert(Alert.AlertType.ERROR);
                alt.setTitle("************ Sorry Something went wrong ******");
                alt.setContentText("Try Again Client cant be deleted !!! ");
                alt.setHeaderText(null);
                alt.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GererreservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    public boolean Verif(String sql) throws SQLException
    {
        System.out.println(sql);
        Connection con= data.getConnection();
        int res=data.UpdateAct(sql);
        return res>0;
    }

    
}
