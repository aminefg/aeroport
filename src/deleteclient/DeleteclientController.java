/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deleteclient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MainfxmlController;
import main.clientview;
import project.database.databs;

/**
 * FXML Controller class
 *
 * @author tic
 */
public class DeleteclientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchrdlt;
    private TextField cid;
    @FXML
    private TextField pass;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private Button deleteclt;
    databs data;        
    @FXML
    private JFXButton exit1;
    @FXML
    private JFXComboBox<Integer> numclientc;        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            data= new databs();
                  ArrayList<Integer> arr=new ArrayList<> ();
        try {
            arr=LoadNumc();
        } catch (SQLException ex) {ex.printStackTrace();}
            ObservableList<Integer> test = FXCollections.observableArrayList(arr);
            numclientc.setItems(test); 
    }
        
       public ArrayList<Integer> LoadNumc() throws SQLException
        {
        String sql="SELECT client.num FROM client ";
        System.out.println(sql);Integer num;
        Connection con= data.getConnection();
        ResultSet res=data.ExecuteAct(sql);
        ArrayList<Integer> arr=new ArrayList<> ();
        boolean test=true; 
        while(test)
        {
            num=res.getInt(1);
            arr.add(num);
            test=res.next();
        }
        return arr;
        } 
    
    
    
        @FXML
        private void deleteclient(ActionEvent event) throws SQLException {
        String clid=cid.getText();
        String nm=nom.getText();
        String prnm=prenom.getText();
        String passp=pass.getText();
        String sql="DELETE FROM `client` where client.nom='"+nm+"' and client.prenom='"+prnm+"' and client.passeport='"+passp+"';";
        if(verif(sql) )
        {
        System.out.println("Client deleted Successfully !!"); 
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
    }
             
        @FXML
        private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchrdlt.getScene().getWindow();
        stag.close();
         }
        public boolean verif(String sql) throws SQLException
    {
        System.out.println(sql);
        Connection con= data.getConnection();
        int res=data.UpdateAct(sql);
        return res>0;

    }
        public int getselectedid(int a)
        {
            return a;
        }
        public void deleteselected(int a )
        {
            String sql="DELETE FROM `client` where client.num ='"+a+"';";
        try {
            if(verif(sql))
            {
                System.out.println("Client deleted Successfully !!");
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
            Logger.getLogger(DeleteclientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    @FXML
    private void showclientinfo(MouseEvent event) {
        String sql="SELECT client.nom,client.prenom,client.passeport FROM client where client.num='"+numclientc.getValue()+"' ";
        System.out.println(sql);
        Connection con= data.getConnection();

        ResultSet res;
        try {
            res = data.ExecuteAct(sql);
            boolean test=true; 
        while(test)
        {
            nom.setText(res.getString(1));
            prenom.setText(res.getString(2));
            pass.setText(res.getString(3));
            test=res.next();
        }
        } catch (SQLException ex) {
            Logger.getLogger(DeleteclientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


        }
    

