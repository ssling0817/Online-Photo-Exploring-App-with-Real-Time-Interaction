import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.WritableImage;
import java.io.File;

public class FinishEdit extends Application {
	
	 Stage st =new Stage();
	 WritableImage writableImage;
	 int editStatus;
	 HomepageController homepagec;
	 File outputFile;
	 @Override
	 public void start(Stage stage) throws Exception {
		 
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("FinishEdit.fxml"));
		 Parent root =(Parent)loader.load();
		 FinishEditController controller = (FinishEditController)loader.getController();
		if(editStatus==0)
			controller.setStage(stage,writableImage,editStatus);
		else if(editStatus==1)
			controller.setStage(stage,writableImage,editStatus,homepagec,outputFile);
		 Scene scene = new Scene(root); // attach scene graph to scene
		 stage.setTitle("Finish Edit"); // displayed in window's title bar
		 
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	public void showWindow(WritableImage writableImage,int editStatus)throws Exception{
		this.writableImage=writableImage;
		this.editStatus=editStatus;
		start(st);
	}
	public void showWindow(WritableImage writableImage,int editStatus,HomepageController homepagec,File outputFile)throws Exception{
		this.writableImage=writableImage;
		this.editStatus=editStatus;
		this.homepagec=homepagec;
		this.outputFile=outputFile;
		start(st);
	}
}