/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addpersonnel;

import com.jfoenix.controls.JFXComboBox;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.database.databs;


public class AddpersonnelController implements Initializable {
    databs data  ;
    @FXML
    private AnchorPane anchradp;
    @FXML
    private Button addp;
    @FXML
    private Button exit;
    @FXML
    private TextField idp;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private JFXComboBox<String> typeperso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            data= new databs();
            ArrayList<String> typep = new ArrayList<String>();
            typep.add("naviguant");
            typep.add("sol");
            ObservableList<String> test = FXCollections.observableArrayList(typep);
            typeperso.setItems(test);
    }
    @FXML
      private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchradp.getScene().getWindow();
        stag.close();
         }
    @FXML
    private void ajouterpersonnel(ActionEvent event) throws SQLException {
        
        String nm=nom.getText();
        String prnm=prenom.getText();
        String ag=age.getText();
        String idper=idp.getText();
        String tp=typeperso.getValue();
        if(tp.equals("naviguant"))
        {
            String sql="INSERT INTO `personnel` (`idperso`, `nom`, `prenom`, `age`) VALUES('"+idper+"','"+nm+"','"+prnm+"','"+ag+"') ;";
        System.out.println(sql);
        Connection con= data.getConnection();
        if(IsEmpty(nm) || IsEmpty(prnm) || IsEmpty(ag) || IsEmpty(idper) || !VerifIdPerso(idper) )
        {
        System.out.println("Missing informations !!"); 
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Important Information ");
            alert.setContentText("Please provide the missing information and be sure to use a new id !! ");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            int res=data.UpdateAct(sql);
        if (res > 0 )
        {
            TextInputDialog maxsomme = new TextInputDialog("");
            TextInputDialog volid = new TextInputDialog("");
        maxsomme.setTitle("Naviguant addition infos");
        maxsomme.setHeaderText("NAVIGUANT MAX SOMME ");
        maxsomme.setContentText("MAX SOMME =");
        volid.setHeaderText("NAVIGUANT VOL ID  ");
        volid.setContentText("VOLID");
        Optional<String> res1 = maxsomme.showAndWait();
        Optional<String> res2 = volid.showAndWait();
        if (res1.isPresent() && res2.isPresent()){
        System.out.println("Your maxsomme: " + res1.get());
        System.out.println("Your volid: " + res2.get());
        int test=Integer.parseInt(res1.get());
        System.out.println("Your volid: "+test);
                
        String sql2="INSERT INTO `naviguant` (`idperso`, `id_vol`, `maxsomme`) VALUES ('"+idper+"', '"+res2.get()+"', '"+Integer.parseInt(res1.get())+"');";
        int resq=data.UpdateAct(sql2);
        if(resq>0)
        {
        System.out.println("Query Executed !!"); 
            Alert alrt=new Alert(Alert.AlertType.INFORMATION);
            alrt.setTitle("Navigant added successfully  !!! ");
            alrt.setContentText("****Thank you Naviguant was added !!!******* ");
            alrt.setHeaderText(null);
            alrt.showAndWait();
        }
        }
        }
        else{
            System.out.println("Query cant be Executed !!"); 
            Alert alt=new Alert(Alert.AlertType.ERROR);
            alt.setTitle("************ Sorry Something went wrong ******");
            alt.setContentText("Try Again naviguant cant be added !!! ");
            alt.setHeaderText(null);
            alt.showAndWait();
            }
             }
            
            
        }
        if(tp.equals("sol"))
        {
            String sql="INSERT INTO `personnel` (`idperso`, `nom`, `prenom`, `age`) VALUES('"+idper+"','"+nm+"','"+prnm+"','"+ag+"') ;";
        System.out.println(sql);
        Connection con= data.getConnection();
        if(IsEmpty(nm) || IsEmpty(prnm) || IsEmpty(ag) || IsEmpty(idper) || !VerifIdPerso(idper) )
        {
        System.out.println("Missing informations !!"); 
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Important Information ");
            alert.setContentText("Please provide the missing information and be sure to use a new id !! ");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            int res=data.UpdateAct(sql);
        if (res > 0 )
        {
            TextInputDialog nbheurs = new TextInputDialog("");
        nbheurs.setTitle("SOL additional infos");
        nbheurs.setHeaderText("SOL NB OF HOURS ");
        nbheurs.setContentText("NB HOURS =");
        Optional<String> res1 = nbheurs.showAndWait();
        if (res1.isPresent()){
        System.out.println("Your maxsomme: " + res1.get());
        String sql2="INSERT INTO `sol` (`idperso`, `nbheures`) VALUES ('"+idper+"','"+Integer.parseInt(res1.get())+"');";
        int resq=data.UpdateAct(sql2);
        if(resq>0)
        {
        System.out.println("Query Executed !!"); 
            Alert alrt=new Alert(Alert.AlertType.INFORMATION);
            alrt.setTitle("Sol added successfully  !!! ");
            alrt.setContentText("****Thank you Sol employee was added !!!******* ");
            alrt.setHeaderText(null);
            alrt.showAndWait();
        }
        }
        }
        else{
            System.out.println("Query cant be Executed !!"); 
            Alert alt=new Alert(Alert.AlertType.ERROR);
            alt.setTitle("************ Sorry Something went wrong ******");
            alt.setContentText("Try Again SOL cant be added !!! ");
            alt.setHeaderText(null);
            alt.showAndWait();
            }
             }
            
            
        }
        
        
        /*
         */
    }

   
    public boolean IsEmpty(String s)
    {
        return s.equals("");
    }
    public void VerifType()
    {
        
        TextInputDialog maxsomme = new TextInputDialog("maxsomme");
        TextInputDialog volid = new TextInputDialog("vol id");
        maxsomme.setTitle("Naviguant addition infos");
        maxsomme.setHeaderText("NAVIGUANT MAX SOMME ");
        maxsomme.setContentText("MAX SOMME =");
        volid.setHeaderText("NAVIGUANT VOL ID  ");
        volid.setContentText("VOLID");

        // Traditional way to get the response value.
        Optional<String> res1 = maxsomme.showAndWait();
        Optional<String> res2 = volid.showAndWait();
        if (res1.isPresent() && res2.isPresent()){
        System.out.println("Your maxsomme: " + res1.get());
        System.out.println("Your volid: " + res2.get());
        int test=Integer.parseInt(res1.get());
        System.out.println("Your volid: "+test);
                }
    }
    public boolean VerifIdPerso(String id) /* Verify if an employee already exists with the same idd */
    {
        int nb=0;
            String sql="SELECT personnel.idperso FROM personnel where personnel.idperso='"+id+"' ";
            Connection con= data.getConnection();
            ResultSet res;boolean test=true; 
        try {
            res=data.ExecuteAct(sql);
            while(test)
            {
                if(!res.getString(1).equals(""))
                { nb++;}
                test=res.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddpersonnelController.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nb==0;  

    }

    
}