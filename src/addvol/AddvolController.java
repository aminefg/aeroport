/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addvol;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import gererpersonnels.personnel;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.database.databs;
public class AddvolController implements Initializable {
    databs data  ;
    @FXML
    private AnchorPane anchradv;
    @FXML
    private Button ajtvol;
    @FXML
    private TextField idvol;
    @FXML
    private JFXTimePicker hdep;
    @FXML
    private JFXTimePicker harv;
    @FXML
    private JFXButton exit1;
    @FXML
    private JFXComboBox<String> aerodep;
    @FXML
    private JFXComboBox<String> aeroariv;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> idavion;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            data= new databs();
            ArrayList<String> arr=new ArrayList<> ();
            ArrayList<String> arrav=new ArrayList<>();
        try {
            arr=LoadAero();
            arrav=LoadAvion();
        } catch (SQLException ex) {
            Logger.getLogger(AddvolController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList<String> test = FXCollections.observableArrayList(arr);
            ObservableList<String> volav = FXCollections.observableArrayList(arrav);

            aerodep.setItems(test); 
            aeroariv.setItems(test);
            idavion.setItems(volav);
    }
        public ArrayList<String> LoadAero() throws SQLException
        {
        String sql="SELECT aeroport.nom FROM aeroport ";
        System.out.println(sql);String nom;
        Connection con= data.getConnection();
        ResultSet res=data.ExecuteAct(sql);
        ArrayList<String> arr=new ArrayList<> ();
        boolean test=true; 
        while(test)
        {
            nom=res.getString(1);
            arr.add(nom);
            test=res.next();
        }
        return arr;
        }
        public ArrayList<String> LoadAvion() throws SQLException
        {
        String sql="SELECT avion.id_avion FROM `avion`  ";
        System.out.println(sql);String idav;
        Connection con= data.getConnection();
        ResultSet res=data.ExecuteAct(sql);
        ArrayList<String> arr=new ArrayList<> ();
        boolean test=true; 
        while(test)
        {
            idav=res.getString(1);
            arr.add(idav);
            test=res.next();
        }
        return arr;
        } 
        @FXML
        private void ajoutervol(ActionEvent event)throws SQLException {
        String idv=idvol.getText();
        String dep=aerodep.getValue();
        String ariv=aeroariv.getValue();
        
        LocalDate dat=date.getValue();
        LocalTime har=harv.getValue();
        LocalTime hdp=hdep.getValue();
        String h1=hdp.toString();
        String h2=har.toString();
        
        String sql="INSERT INTO `vol` (`id_vol`, `nom_aero_dep`, `nom_aero_ariv`, `datevol`, `heuredepart`, `heurearrivee`, `id_avion`) VALUES('"+idv+"','"+dep+"','"+ariv+"','"+dat+"','"+h1+"','"+h2+"' , '"+idavion.getValue()+"') ;";
        System.out.println(sql);
        Connection con= data.getConnection();
        if(IsEmpty(idv) || IsEmpty(dep) || IsEmpty(ariv) || ValidAeroport(dep, ariv) || !VerifIdvol(idv) || IsEmpty(idavion.getValue()))
        {
        System.out.println("Missing informations !!"); 
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Important Information ");
            alert.setContentText("Please provide the missing information or check that aero dep is diff then aero ariv or check id vol  !! ");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            int res=data.UpdateAct(sql);
        if (res > 0 )
        { 
            System.out.println("Query Executed !!"); 
            Alert alrt=new Alert(Alert.AlertType.INFORMATION);
            alrt.setTitle("Vol added successfully  !!! ");
            alrt.setContentText("****Vol added !!!******* ");
            alrt.setHeaderText(null);
            alrt.showAndWait();
        }
        else{
            System.out.println("Query cant be Executed !!"); 
            Alert alt=new Alert(Alert.AlertType.ERROR);
            alt.setTitle("************ Sorry Something went wrong ******");
            alt.setContentText("Try Again vol cant be added !!! ");
            alt.setHeaderText(null);
            alt.showAndWait();
            }
             }
        }
         
        @FXML
        private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchradv.getScene().getWindow();
        stag.close();
         }
    public boolean IsEmpty(String s)
    {
        return s.equals("");
    }
    public boolean ValidAeroport(String dep, String ariv)
    {
        return dep.equals(ariv);
    }
    public boolean VerifIdvol(String id )
    {
        String sql="SELECT vol.id_vol FROM vol where vol.id_vol='"+id+"'";
        ResultSet res; Integer nb=0;
        Connection con= data.getConnection(); 
        boolean test=true;
        try {
            res=data.ExecuteAct(sql);
            while(test)
            {
                if(!res.getString(1).equals(""))
                {nb++;}
                test=res.next();
                          
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddvolController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nb==0;
        
    }
}
