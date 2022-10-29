import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class FollowingPhoto extends Application {
	
	 Stage st =new Stage();
	 HomepageController homepagec;
	 FollowingController fc;
	 int num;
	 int pagePhotoStatus;
	 int photoIndex;
	 @Override
	 public void start(Stage stage) throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("FollowingPhoto.fxml"));
		 Parent root =(Parent)loader.load();
		 Scene scene = new Scene(root,500,550); // attach scene graph to scene
		 FollowingPhotoController controller = (FollowingPhotoController)loader.getController();
		  //System.out.println(stage);
		 controller.setStage(stage,homepagec,num,fc,pagePhotoStatus,photoIndex,this);
		 stage.setTitle("My following users' Upload Image"); // displayed in window's title bar
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
	 public void showWindow(HomepageController homepagec,int num,FollowingController myc,int pagePhotoStatus,int photoIndex)throws Exception{
		this.homepagec=homepagec;
		this.fc=myc;
		this.num=num;
		this.pagePhotoStatus=pagePhotoStatus;
		this.photoIndex=photoIndex;
		start(st);
	}
	public void showWindow(HomepageController homepagec,int num,FollowingController myc)throws Exception{
		this.homepagec=homepagec;
		this.fc=myc;
		this.num=num;
		this.pagePhotoStatus=myc.getpagePhotoStatus();
		this.photoIndex=myc.getphotoIndex();
		start(st);
	}
}