/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deleteclient;

/**
 *
 * @author tic
 */
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class deleteclient extends Application {
        /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent rooot = FXMLLoader.load(getClass().getResource("deleteclient.fxml"));
        Scene scene=new Scene(rooot);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String...args)
    {
        launch(args);
    }
    
}
