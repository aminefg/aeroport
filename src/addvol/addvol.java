
package addvol;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class addvol extends Application {
        /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent pt = FXMLLoader.load(getClass().getResource("addvol.fxml"));
        Scene scene=new Scene(pt);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String...args)
    {
        launch(args);
    }
    
}
