import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Register extends Application {
	
	 Stage st =new Stage();
	 
	 @Override
	 public void start(Stage stage) throws Exception {
		 Parent root =
		 FXMLLoader.load(getClass().getResource("Register.fxml"));
		 Scene scene = new Scene(root); // attach scene graph to scene
		 stage.setTitle("Register"); // displayed in window's title bar
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	 public void showWindow()throws Exception{
		start(st);
	}
}