/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import deleteclient.DeleteclientController;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.database.databs;

public class MainfxmlController implements Initializable {

    @FXML
    private AnchorPane anchrpan;
    @FXML
    private Button exit;
    @FXML
    private Button ajt;
    @FXML
    private Button supclt;
    @FXML
    private Button gererv;
    @FXML
    private Button gererp;
    @FXML
    private Button gererrsv;
    @FXML
    private Button actualiser;
    private ChoiceBox cbox;
    @FXML
    private TableView<clientview> tableclient;
    @FXML
    private TableColumn<clientview, Integer> num;
    @FXML
    private TableColumn<clientview, String> nom;
    @FXML
    private TableColumn<clientview, String> prenom;
    @FXML
    private TableColumn<clientview, String> volid;
    @FXML
    private TableColumn<clientview, String> datenaiss;
    @FXML
    private TableColumn<clientview, String> passport;
    databs data;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox box;
    @FXML
    private FontAwesomeIconView user13;
    @FXML
    private FontAwesomeIconView user12;
    @FXML
    private FontAwesomeIconView user11;
    @FXML
    private FontAwesomeIconView user1;
    @FXML
    private JFXButton dl;
    @FXML
    private FontAwesomeIconView user121;
    @FXML
    private JFXButton avn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = new databs();
        num.setCellValueFactory(new PropertyValueFactory<clientview, Integer>("num"));
        nom.setCellValueFactory(new PropertyValueFactory<clientview, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<clientview, String>("prenom"));
        datenaiss.setCellValueFactory(new PropertyValueFactory<clientview, String>("datenaiss"));
        passport.setCellValueFactory(new PropertyValueFactory<clientview, String>("passport"));
        volid.setCellValueFactory(new PropertyValueFactory<clientview, String>("volid"));
        afficherclient();
           
        drawer.setSidePane(box);
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });

       
    }

    @FXML
    private void exitt(ActionEvent event) {
        Stage stag = (Stage) anchrpan.getScene().getWindow();
        stag.close();
    }

    @FXML
    private void ajouteclt(ActionEvent event) {
        String path = "/addclient/addclient.fxml";
        loadwindow(path);
    }

    @FXML
    private void refresh(ActionEvent event) {
        afficherclient();
    }

    @FXML
    private void deleteclt(ActionEvent event) {
     String path = "/deleteclient/deleteclient.fxml";
     loadwindow(path);
    }

    @FXML
    private void gerervols(ActionEvent event) {
        String path = "/gerervols/gerervols.fxml";
        loadwindow(path);
    }

    @FXML
    private void gererperso(ActionEvent event) {
        String path = "/gererpersonnels/gererperso.fxml";
        loadwindow(path);
    }

    @FXML
    private void gererres(ActionEvent event) {
        String path="/gererreservation/gererreservation.fxml";
        loadwindow(path);
    }

    private void afficherclient() {
        String sql = "select * from client;";
        Integer numc;
        String idv;
        String nomc;
        String prnom;
        String datn;
        String passp;
        System.out.println(sql);
        Connection con = data.getConnection();
        ResultSet res;
        ArrayList<clientview> arr = new ArrayList<clientview>();
        boolean t = true;
        try {
            res = data.ExecuteAct(sql);
            while (t) {
                numc = res.getInt(1);
                nomc = res.getString(2);
                prnom = res.getString(3);
                datn = res.getString(4);
                passp = res.getString(5);
                arr.add(new clientview(numc, nomc, prnom, datn, passp));
                t = res.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<clientview> clientv = FXCollections.observableArrayList(arr);
        tableclient.setItems(clientv);
    }

    private void loadwindow(String path) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  

    public TableView<clientview> getTableclient() {
        return tableclient;
    }

    @FXML
    private void delselect(ActionEvent event) {
                int selectedid;
     ObservableList<clientview> selectedclient, allClients;
     allClients = tableclient.getItems();
     selectedclient=tableclient.getSelectionModel().getSelectedItems();
     selectedid = tableclient.getSelectionModel().getSelectedItem().getNum();
     selectedclient.forEach(allClients::remove);
        deleteselected(selectedid);
    }
    
       public boolean verif(String sql) throws SQLException
    {
        System.out.println(sql);
        Connection con= data.getConnection();
        int res=data.UpdateAct(sql);
        return res>0;

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
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }


    @FXML
    private void gereravion(ActionEvent event) {
       String path = "/gereravions/gereravions.fxml";
        loadwindow(path);
    }


    
    


}
