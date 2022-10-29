import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.image.Image;
public class Upload extends Application {
	
	 Stage st =new Stage();
	 HomepageController homepagec;
	  Image image;
	 File outputFile;
	 
	 @Override
	 public void start(Stage stage) throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Upload.fxml"));
		 Parent root =(Parent)loader.load();
		 UploadController controller = (UploadController)loader.getController();
		  if(image==null)
			controller.setStage(stage,homepagec);
		else
			controller.setStage(stage,homepagec,image,outputFile);
		 Scene scene = new Scene(root); // attach scene graph to scene
		 stage.setTitle("Upload"); // displayed in window's title bar
		 
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	 public void showWindow(HomepageController homepagec)throws Exception{
		 this.homepagec=homepagec;
		start(st);
	}
	
	public void showWindow(HomepageController homepagec,Image image,File outputFile)throws Exception{
		 this.homepagec=homepagec;
		 this.image=image;
		 this.outputFile=outputFile;
		 start(st);
	}
}