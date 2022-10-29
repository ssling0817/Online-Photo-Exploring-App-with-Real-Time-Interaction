import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class TargetInfo extends Application {
	
	 Stage st =new Stage();
	 HomepageController homepagec;
	 int num;
	 ViewImageController viewc;
	 MyController myc;
	 int topicStatus=0;
	 int pagePhotoStatus;
	 int photoIndex;
	 TargetHomeController t;
	 @Override
	 public void start(Stage stage) throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("TargetInfo.fxml"));
		 Parent root =(Parent)loader.load();
		 TargetInfoController controller = (TargetInfoController)loader.getController();
		 controller.setStage(stage,homepagec,num,t,pagePhotoStatus,photoIndex,this);
		 Scene scene = new Scene(root,500,550); // attach scene graph to scene
		 stage.setTitle("Photo"); // displayed in window's title bar
		 
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	 public void showWindow(HomepageController homepagec,int num,TargetHomeController t,int pagePhotoStatus,int photoIndex)throws Exception{
		this.homepagec=homepagec;
		this.t=t;
		this.num=num;
		this.topicStatus=topicStatus;
		this.pagePhotoStatus=pagePhotoStatus;
		this.photoIndex=photoIndex;
		start(st);
	}
	 public void showWindow(HomepageController homepagec,int num,MyController myc)throws Exception{
		this.homepagec=homepagec;
		this.myc=myc;
		this.num=num;
		start(st);
	}
}