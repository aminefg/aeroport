/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gererreservation;

import addclient.AddclientController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MainfxmlController;
import project.database.databs;

/**
 * FXML Controller class
 *
 * @author tic
 */
public class AjouterreservationController implements Initializable {

    @FXML
    private AnchorPane anchrajtres;
    @FXML
    private JFXComboBox<Integer> numc;
    @FXML
    private JFXComboBox<String> idvol;
    @FXML
    private Button exit;
            databs data  ;
    @FXML
    private Button valideres;
    @FXML
    private JFXDatePicker dateres;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    data= new databs();
                    ArrayList<Integer> arr=new ArrayList<> ();
                    
                    ArrayList<String> arrid=new ArrayList<> ();
        try {
            arr=LoadNumc();
            arrid=LoadIdvol();
        } catch (SQLException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                        }
            ObservableList<Integer> test = FXCollections.observableArrayList(arr);
            numc.setItems(test);
            ObservableList<String> idvolist = FXCollections.observableArrayList(arrid);
            idvol.setItems(idvolist);
    }    
        public ArrayList<Integer> LoadNumc() throws SQLException
        {
        String sql="SELECT client.num FROM client ";
        System.out.println(sql);Integer numc;
        Connection con= data.getConnection();
        ResultSet res=data.ExecuteAct(sql);
        ArrayList<Integer> arr=new ArrayList<> ();
        boolean test=true; 
        while(test)
        {
            numc=res.getInt(1);
            arr.add(numc);
            test=res.next();
        }
        return arr;
        }
        public ArrayList<String> LoadIdvol() throws SQLException
        {
        String sql="SELECT vol.id_vol FROM vol ";
        System.out.println(sql);String numc;
        Connection con= data.getConnection();
        ResultSet res=data.ExecuteAct(sql);
        ArrayList<String> arr=new ArrayList<> ();
        boolean test=true; 
        while(test)
        {
            numc=res.getString(1);
            arr.add(numc);
            test=res.next();
        }
        return arr;
        }
    @FXML
    private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchrajtres.getScene().getWindow();
        stag.close();
    }

    @FXML
    private void AjouterReservation(ActionEvent event) {
        Integer num; String volid;
        num=numc.getValue();
        volid=idvol.getValue();
        LocalDate dat=dateres.getValue();
        if(!VerifRes(num, volid) || num.equals("") || volid.equals("") )
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong or Missing Informations");
            alert.setContentText("Missing infos | Client with the same id already has a reservation ");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else{
            try {
                String sql=" INSERT INTO `reservation` (`num`, `id_vol`, `dateres`) VALUES ('"+num+"', '"+volid+"', '"+dat+"');";
                System.out.println(sql);
                Connection con= data.getConnection();
                int res=data.UpdateAct(sql);
                if (res > 0 )
                {
                    System.out.println("Query Executed !!");
                    Alert alrt=new Alert(Alert.AlertType.INFORMATION);
                    alrt.setTitle("Reservation added successfully  !!! ");
                    alrt.setContentText("****Reservation Ajouter avéc success !!!******* ");
                    alrt.setHeaderText(null);
                    alrt.showAndWait();
                }
                else{
                    System.out.println("Query cant be Executed !!");
                    Alert alt=new Alert(Alert.AlertType.ERROR);
                    alt.setTitle("************ Sorry Something went wrong ******");
                    alt.setContentText("Try Again Reservation cant be added !!! ");
                    alt.setHeaderText(null);
                    alt.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AjouterreservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        
    }
    public boolean VerifRes(Integer num, String volid ) /* Verify if a client already has a reservation */
    {
        int nb=0;
            String sql="SELECT reservation.num,reservation.id_vol FROM `reservation` where reservation.num='"+num+"' and reservation.id_vol='"+volid+"'  ";
            Connection con= data.getConnection();
            ResultSet res;boolean test=true; 
        try {
            res=data.ExecuteAct(sql);
            while(test )
            {
                if(!res.getString(2).equals(""))
                { nb++; }
                test=res.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddclientController.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nb==0;  

    }
    
}
