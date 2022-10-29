import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class PhotoInfo extends Application {
	
	 Stage st =new Stage();
	 HomepageController homepagec;
	 int num;
	 ViewImageController viewc;
	 MyController myc;
	 int topicStatus=0;
	 int pagePhotoStatus;
	 int photoIndex;
	 @Override
	 public void start(Stage stage) throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("PhotoInfo.fxml"));
		 Parent root =(Parent)loader.load();
		 PhotoInfoController controller = (PhotoInfoController)loader.getController();
		 controller.setStage(stage,homepagec,num,viewc,myc,topicStatus,pagePhotoStatus,photoIndex,this);
		 Scene scene = new Scene(root,500,550); // attach scene graph to scene
		 stage.setTitle("Photo"); // displayed in window's title bar
		 
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	 public void showWindow(HomepageController homepagec,int num,ViewImageController viewc,int topicStatus,int pagePhotoStatus,int photoIndex)throws Exception{
		this.homepagec=homepagec;
		this.viewc=viewc;
		this.num=num;
		this.topicStatus=topicStatus;
		this.pagePhotoStatus=pagePhotoStatus;
		this.photoIndex=photoIndex;
		start(st);
	}
	/*
	 public void showWindow(HomepageController homepagec,int num,ViewImageController viewc,int topicStatus)throws Exception{
		this.homepagec=homepagec;
		this.viewc=viewc;
		this.num=num;
		this.topicStatus=topicStatus;
		this.pagePhotoStatus=viewc.getpagePhotoStatus();
		this.photoIndex=viewc.getphotoIndex();
		start(st);
	}*/
	
	 public void showWindow(HomepageController homepagec,int num,MyController myc)throws Exception{
		this.homepagec=homepagec;
		this.myc=myc;
		this.num=num;
		start(st);
	}
}