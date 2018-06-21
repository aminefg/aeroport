/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerervols;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MainfxmlController;
import project.database.databs;

public class GerervolsController implements Initializable {
    @FXML
    private AnchorPane anchrgrv;
    @FXML
    private Button ajtvol;
    @FXML
    private Button suppvol;
     @FXML
    private TableView<volview> tablevol;
    @FXML
    private TableColumn<volview, String> idvol;
    @FXML
    private TableColumn<volview, String> depart;
    @FXML
    private TableColumn<volview, String> arrivee;
    @FXML
    private TableColumn<volview, String> hdep;
    @FXML
    private TableColumn<volview, String> hariv;
    @FXML
    private TableColumn<volview, String> date;
    databs data  ;
    @FXML
    private JFXButton exit1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          data= new databs();  
        idvol.setCellValueFactory(new PropertyValueFactory<volview,String>("idvol"));
        depart.setCellValueFactory(new PropertyValueFactory<volview,String>("depart"));
        arrivee.setCellValueFactory(new PropertyValueFactory<volview,String>("arrivee"));
        hdep.setCellValueFactory(new PropertyValueFactory<volview,String>("hdep"));
        hariv.setCellValueFactory(new PropertyValueFactory<volview,String>("hariv"));
        date.setCellValueFactory(new PropertyValueFactory<volview,String>("date"));
        affichervol();
    }    
      @FXML
      private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchrgrv.getScene().getWindow();
        stag.close();
         }
       @FXML
      private void ajoutervol(ActionEvent event) {
        String path="/addvol/addvol.fxml";
           loadwindow(path);
         }
       @FXML
      private void supprimervol(ActionEvent event) {
        String path="/deletevol/deletevol.fxml";
           loadwindow(path);
         }
      private void affichervol()
        {
                String sql="select * from vol ;";
                 String idv; String dep;String ariv;String hdp;String harv; String dat;
                 System.out.println(sql);
                 Connection con= data.getConnection();
                 ResultSet res;
                 ArrayList<volview> arr= new ArrayList<volview> ();
                 boolean t=true;
                try {
                res = data.ExecuteAct(sql);
                 while(t)
                 {
                     idv=res.getString(1);
                     dep=res.getString(2);
                     ariv=res.getString(3);
                     hdp=res.getString(4);
                     harv=res.getString(5);
                     dat=res.getString(6); 
                     arr.add(new volview(idv, dep, ariv, hdp, harv, dat));
                     t=res.next();
                 }
                  } catch (SQLException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ObservableList<volview> volv = FXCollections.observableArrayList(arr);
                        tablevol.setItems(volv);                      
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
