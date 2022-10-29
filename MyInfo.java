import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class MyInfo extends Application {
	
	 Stage st =new Stage();
	 HomepageController homepagec;
	 int num;
	 ViewImageController viewc;
	 MyController myc;
	 int pagePhotoStatus,photoIndex;
	 @Override
	 public void start(Stage stage) throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("MyInfo.fxml"));
		 Parent root =(Parent)loader.load();
		 MyInfoController controller = (MyInfoController)loader.getController();
		 controller.setStage(stage,homepagec,num,viewc,myc,pagePhotoStatus,photoIndex);
		 Scene scene = new Scene(root); // attach scene graph to scene
		 stage.setTitle("My Upload Image"); // displayed in window's title bar
		 
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	 public void showWindow(HomepageController homepagec,int num,ViewImageController viewc)throws Exception{
		this.homepagec=homepagec;
		this.viewc=viewc;
		this.num=num;
		start(st);
	}
	 public void showWindow(HomepageController homepagec,int num,MyController myc,int pagePhotoStatus,int photoIndex)throws Exception{
		this.homepagec=homepagec;
		this.myc=myc;
		this.num=num;
		this.pagePhotoStatus=pagePhotoStatus;
		this.photoIndex=photoIndex;
		start(st);
	}
}