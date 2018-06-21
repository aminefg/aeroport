
package deletevol;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.database.databs;
public class DeletevolController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchrdltv;
    @FXML
    private TextField vid;
    @FXML
    private TextField depart;
    @FXML
    private TextField arrivee;
    @FXML
    private Button dltvol;
    databs data; 
    @FXML
    private JFXButton exit1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            data= new databs(); 
    }    
        @FXML
        private void exitt(ActionEvent event) {
        Stage stag=(Stage) anchrdltv.getScene().getWindow();
        stag.close();
         }
        @FXML
        private void deletevol(ActionEvent event) throws SQLException {
        String volid=vid.getText();
        String dep=depart.getText();
        String arv=arrivee.getText();
        String sql="DELETE FROM `vol` where id_vol ='"+volid+"' and vol.nom_aero_dep ='"+dep+"' and vol.nom_aero_ariv='"+arv+"';";
        if(verif(sql) )
        {
        System.out.println("Vol deleted Successfully !!"); 
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("vol DELETED");
            alert.setContentText("*****************Vol is deleted !!!!********* ");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            System.out.println("Query cant be Executed !!"); 
            Alert alt=new Alert(Alert.AlertType.ERROR);
            alt.setTitle("************ Sorry Something went wrong ******");
            alt.setContentText("Try Again vol cant be deleted !!! ");
            alt.setHeaderText(null);
            alt.showAndWait();
             }
         }
        public boolean verif(String sql) throws SQLException
    {
        System.out.println(sql);
        Connection con= data.getConnection();
        int res=data.UpdateAct(sql);
        return res>0;

    }
    
}
