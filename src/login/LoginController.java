/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.database.databs;


/**
 * FXML Controller class
 *
 * @author riadh
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton make;
    @FXML
    private JFXButton cancel;
    @FXML
    private AnchorPane anchrp;
    
    databs data  ;
    @FXML
    private FontAwesomeIconView user;
    @FXML
    private FontAwesomeIconView user1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     data= new databs();

    }  

    @FXML
    private void maked(ActionEvent event) throws SQLException {
        String am=name.getText();
        String pm=password.getText();
        if(verif(am,pm))
        { 
            String path="/main/mainfxml.fxml";
            System.out.println("Hello Adminnnnnnnnnnnnnnnnnnnn");
            loadwindow(path);
             
        }
        else{
             Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("that\'s nottttttttt ok");
            alert.setContentText("Wrong credentials !!! ");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    private void canceld(ActionEvent event) {
        Stage stag=(Stage) anchrp.getScene().getWindow();
       stag.close();
    }
    
    public boolean verif(String nom,String passwd) throws SQLException
    {
        String sql="select * from admin ;";
        System.out.println(sql);
        Connection con= data.getConnection();
        ResultSet res=data.ExecuteAct(sql);
        if(res.getString(1).equals(nom) && res.getString(2).equals(passwd))
        {
            return true;
        }
        
        else {
        return false;}
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
    
}
