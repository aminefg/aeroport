/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tic
 */
public class menu extends Application {

    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent r = FXMLLoader.load(getClass().getResource("mainfxml.fxml"));
        Scene scene=new Scene(r);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String...args)
    {
        launch(args);
    }
    
}
