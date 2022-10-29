import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class ArchiveInfo extends Application {
	
	 Stage st =new Stage();
	 HomepageController homepagec;
	 int num;
	 ViewImageController viewc;
	 ArchiveController ac;
	 int topicStatus=0;
	 int pagePhotoStatus;
	 int photoIndex;
	 @Override
	 public void start(Stage stage) throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("ArchiveInfo.fxml"));
		 Parent root =(Parent)loader.load();
		 ArchiveInfoController controller = (ArchiveInfoController)loader.getController();
		 controller.setStage(stage,homepagec,num,viewc,ac,pagePhotoStatus,photoIndex,this);
		 Scene scene = new Scene(root,500,550); // attach scene graph to scene
		 stage.setTitle("Archive"); // displayed in window's title bar
		 
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	 public void showWindow(HomepageController homepagec,int num,ViewImageController viewc,int topicStatus)throws Exception{
		this.homepagec=homepagec;
		this.viewc=viewc;
		this.num=num;
		this.topicStatus=topicStatus;
		start(st);
	}
	
	 public void showWindow(HomepageController homepagec,int num,ArchiveController myc,int pagePhotoStatus,int photoIndex)throws Exception{
		this.homepagec=homepagec;
		this.ac=myc;
		this.num=num;
		this.pagePhotoStatus=pagePhotoStatus;
		this.photoIndex=photoIndex;
		start(st);
	}
	public void showWindow(HomepageController homepagec,int num,ArchiveController myc)throws Exception{
		this.homepagec=homepagec;
		this.ac=myc;
		this.num=num;
		this.pagePhotoStatus=myc.getpagePhotoStatus();
		this.photoIndex=myc.getPhotoIndex();
		start(st);
	}
}