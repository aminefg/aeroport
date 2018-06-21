/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gereravions;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.database.databs;

/**
 * FXML Controller class
 *
 * @author tic
 */
public class AddavionController implements Initializable {

    @FXML
    private AnchorPane anchrajtav;
    @FXML
    private JFXButton ajtav;
    @FXML
    private JFXButton exit;
    @FXML
    private JFXTextField idav;
    @FXML
    private JFXTextField nomav;
    @FXML
    private JFXTextField marqueav;
    @FXML
    private JFXTextField compav;
    @FXML
    private JFXTextField maint;
    @FXML
    private JFXComboBox<String> typeav;
    databs data  ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ArrayList<String> typeavion = new ArrayList<String>();
            typeavion.add("marchandise");
            typeavion.add("passagers");    
                        data= new databs();
            ObservableList<String> test = FXCollections.observableArrayList(typeavion);
            typeav.setItems(test);
    
    
    }    

    @FXML
    private void ajouteravion(ActionEvent event) throws SQLException {
          
        String ida=idav.getText();
        String nom=nomav.getText();
        String marq=marqueav.getText();
        String comp=compav.getText();
        String mnt=maint.getText();
        String tp=typeav.getValue();
        if(tp.equals("passagers"))
        {
            String sql="INSERT INTO `avion` (`id_avion`, `nom`, `marque`, `compagnie`, `maint`) VALUES ('"+ida+"', '"+nom+"', '"+marq+"', '"+comp+"', '"+mnt+"') ;";
        System.out.println(sql);
        Connection con= data.getConnection();
        if(IsEmpty(ida) || IsEmpty(nom) || IsEmpty(marq) || IsEmpty(comp) || IsEmpty(mnt) )
        {
        System.out.println("Missing informations !!"); 
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Important Information ");
            alert.setContentText("Please provide the missing information and be sure to use a new avion id !! ");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            int res=data.UpdateAct(sql);
        if (res > 0 )
        {
            TextInputDialog nbperso = new TextInputDialog("nb personnels");
            TextInputDialog nbpasg = new TextInputDialog("nb passagers");
        nbperso.setTitle("Avion Passagers addition infos");
        nbperso.setHeaderText("Passagers NB PERSONNELS");
        nbperso.setContentText("NB PERSONNELS =");
        nbpasg.setHeaderText("Passagers NB  ");
        nbpasg.setContentText("NB PASSAGERS");
        Optional<String> res1 = nbperso.showAndWait();
        Optional<String> res2 = nbpasg.showAndWait();
        if (res1.isPresent() && res2.isPresent()){
        System.out.println("Your NB PERSONNELS : " + res1.get());
        System.out.println("Your NB PASSAGERS: " + res2.get());
        int test=Integer.parseInt(res1.get());
        System.out.println("Your nb perso: "+test);
                
        String sql2="INSERT INTO `avion_passagers` (`id_avion`, `nbrepersonnel`, `nbrepassagers`) VALUES ('"+ida+"', '"+Integer.parseInt(res1.get())+"', '"+Integer.parseInt(res2.get())+"');";
        int resq=data.UpdateAct(sql2);
        if(resq>0)
        {
        System.out.println("Query Executed !!"); 
            Alert alrt=new Alert(Alert.AlertType.INFORMATION);
            alrt.setTitle("PASSAGERS added successfully  !!! ");
            alrt.setContentText("****Thank you AVION PASSAGERS was added !!!******* ");
            alrt.setHeaderText(null);
            alrt.showAndWait();
        }
        }
        }
        else{
            System.out.println("Query cant be Executed !!"); 
            Alert alt=new Alert(Alert.AlertType.ERROR);
            alt.setTitle("************ Sorry Something went wrong ******");
            alt.setContentText("Try Again AVION cant be added !!! ");
            alt.setHeaderText(null);
            alt.showAndWait();
            }
             }
            
            
        }
        if(tp.equals("marchandise"))
        {
            String sql="INSERT INTO `avion` (`id_avion`, `nom`, `marque`, `compagnie`, `maint`) VALUES ('"+ida+"', '"+nom+"', '"+marq+"', '"+comp+"', '"+mnt+"') ;";
        System.out.println(sql);
        Connection con= data.getConnection();
        if(IsEmpty(ida) || IsEmpty(nom) || IsEmpty(marq) || IsEmpty(comp) || IsEmpty(mnt) )
        {
        System.out.println("Missing informations !!"); 
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Important Information ");
            alert.setContentText("Please provide the missing information and be sure to use a new avion id !! ");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            int res=data.UpdateAct(sql);
        if (res > 0 )
        {
            TextInputDialog massem = new TextInputDialog("MasseMax");
            TextInputDialog volm = new TextInputDialog("VolumeMax");
            TextInputDialog nbpers = new TextInputDialog("Nb Personnels");
        massem.setTitle("Marchandise additional infos");
        massem.setHeaderText("Marchandise Masse MAx ");
        massem.setContentText("Masse Max =");
         volm.setHeaderText("Marchandise Vol Max");
        volm.setContentText("Volume max =");
         nbpers.setHeaderText("Marchandise NB PERSONNELS");
        nbpers.setContentText("NB PERSONNELS =");
        Optional<String> res1 = massem.showAndWait();
        Optional<String> res2 = volm.showAndWait();
        Optional<String> res3 = nbpers.showAndWait();
        if (res1.isPresent() && res2.isPresent() && res3.isPresent()){
        String sql2="INSERT INTO `avion_marchandise` (`id_avion`, `masse_max`, `volume_max`, `nbrepersonnel`) VALUES ('"+ida+"', '"+Integer.parseInt(res1.get())+"', '"+Integer.parseInt(res2.get())+"', '"+Integer.parseInt(res3.get())+"') ;";
        int resq=data.UpdateAct(sql2);
        if(resq>0)
        {
        System.out.println("Query Executed !!"); 
            Alert alrt=new Alert(Alert.AlertType.INFORMATION);
            alrt.setTitle("AVION MARCHANDISE added successfully  !!! ");
            alrt.setContentText("****Thank you Marchandise was added !!!******* ");
            alrt.setHeaderText(null);
            alrt.showAndWait();
        }
        }
        }
        else{
            System.out.println("Query cant be Executed !!"); 
            Alert alt=new Alert(Alert.AlertType.ERROR);
            alt.setTitle("************ Sorry Something went wrong ******");
            alt.setContentText("Try Again Marchandise cant be added !!! ");
            alt.setHeaderText(null);
            alt.showAndWait();
            }
             }
        }
    }

    @FXML
    private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchrajtav.getScene().getWindow();
        stag.close();
    }
    public boolean IsEmpty(String s)
    {
        return s.equals("");
    }
}
