
package addclient;
import addpersonnel.AddpersonnelController;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.database.databs;

public class AddclientController implements Initializable {
    databs data  ;
    @FXML
    private AnchorPane addanchr;
    @FXML
    private Button ajt;
    @FXML
    private Button exit;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private JFXDatePicker date;
    @FXML
    private TextField passport;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            data= new databs();
    }
    @FXML
      private void exitt(ActionEvent event) {
        Stage stag=(Stage) addanchr.getScene().getWindow();
        stag.close();
         }
    @FXML
    private void ajouterclient(ActionEvent event) throws SQLException {
        String nm=nom.getText();
        String prnm=prenom.getText();
        LocalDate dat=date.getValue();
        String pass=passport.getText();
        String sql="INSERT INTO `client` (`nom`, `prenom`, `datenaiss`, `passeport`) VALUES ('"+nm+"', '"+prnm+"', '"+dat+"', '"+pass+"');;";
        System.out.println(sql);
        Connection con= data.getConnection();
        if(IsEmpty(nm) || IsEmpty(prnm) || IsEmpty(pass) || !Verifclient(nm, prnm, pass) ) /**/
        {
        System.out.println("Missing informations !!"); 
        
            Alert alert=new Alert(Alert.AlertType.ERROR);
            
            alert.setTitle("Missing | conflict Information ");
            alert.setContentText("Please provide the missing information or Client may already exists ");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            int res=data.UpdateAct(sql);
        if (res > 0 )
        { 
            System.out.println("Query Executed !!"); 
            Alert alrt=new Alert(Alert.AlertType.INFORMATION);
            alrt.setTitle("Client added successfully  !!! ");
            alrt.setContentText("****Client Ajouter avéc success !!!******* ");
            alrt.setHeaderText(null);
            alrt.showAndWait();
        }
        else{
            System.out.println("Query cant be Executed !!"); 
            Alert alt=new Alert(Alert.AlertType.ERROR);
            alt.setTitle("************ Sorry Something went wrong ******");
            alt.setContentText("Try Again Client cant be added !!! ");
            alt.setHeaderText(null);
            alt.showAndWait();
            }
             }
        
    }

   
    public boolean IsEmpty(String s)
    {
        return s.equals("");
    }
     public boolean Verifclient(String nom, String prenom , String pass) /* Verify if a client already exists with the same idd */
    {
        int nb=0;
            String sql="SELECT client.nom,client.prenom,client.passeport from client where client.nom='"+nom+"' and client.prenom='"+prenom+"' and client.passeport='"+pass+"' ";
            Connection con= data.getConnection();
            ResultSet res;boolean test=true; 
        try {
            res=data.ExecuteAct(sql);
            while(test && nb==0)
            {
                if(!res.getString(1).equals(""))
                { nb++; }
                test=res.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddclientController.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nb==0;  

    }
    
}
