import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class My extends Application {
	
	 Stage st =new Stage();
	 HomepageController homepagec;
	 
	 @Override
	 public void start(Stage stage) throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("MyPhoto.fxml"));
		 Parent root =(Parent)loader.load();
		 Scene scene = new Scene(root); // attach scene graph to scene
		 MyController controller = (MyController)loader.getController();
		  //System.out.println(stage);
		 controller.setStage(stage,homepagec,this);
		 stage.setTitle("My Upload Image"); // displayed in window's title bar
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	 public void showWindow(HomepageController h)throws Exception{
		homepagec=h;
		start(st);
	}
}