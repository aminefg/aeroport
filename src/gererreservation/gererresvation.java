/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gererreservation;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tic
 */
public class gererresvation extends Application {
     @Override
    public void start(Stage stage) throws Exception {
        Parent rt = FXMLLoader.load(getClass().getResource("gererreservation.fxml"));
        Scene scene=new Scene(rt);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String...args)
    {
        launch(args);
    }
}
