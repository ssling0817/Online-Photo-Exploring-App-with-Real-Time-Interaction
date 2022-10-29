import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class TargetHome extends Application {
	
	 Stage st =new Stage();
	 HomepageController homepagec;
	 PhotoInfoController pc;
	 ArchiveInfoController ac;
	 SearchInfoController sc;
	 FollowingPhotoController fc;
	 String myAccount,following;
	 ViewImageController viewc;
	 int pagePhotoStatus,photoIndex;
	 String search;
	 int num;
	 int topicStatus;
	 
	 @Override
	 public void start(Stage stage) throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("TargetHome.fxml"));
		 Parent root =(Parent)loader.load();
		 Scene scene = new Scene(root); // attach scene graph to scene
		 TargetHomeController controller = (TargetHomeController)loader.getController();
		 controller.setStage(stage,homepagec,this,pc,myAccount,following,ac,sc,fc,viewc,search,num,pagePhotoStatus,photoIndex,topicStatus);
		 stage.setTitle("Home"); // displayed in window's title bar
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	 public void showWindow(HomepageController h,PhotoInfoController pc,String myAccount,String following,ViewImageController viewc,int pagePhotoStatus,int photoIndex,int num,int topicStatus)throws Exception{
		homepagec=h;
		this.viewc=viewc;
		this.pc=pc;
		this.myAccount=myAccount;
		this.following=following;
		this.pagePhotoStatus=pagePhotoStatus;
		this.photoIndex=photoIndex;
		this.num=num;
		this.topicStatus=topicStatus;

		start(st);
	}
	public void showWindow(HomepageController h,ArchiveInfoController ac,String myAccount,String following,int num,int pagePhotoStatus,int photoIndex)throws Exception{
		homepagec=h;
		this.ac=ac;
		this.myAccount=myAccount;
		this.following=following;
		this.num=num;
		
		start(st);
	}
	public void showWindow(HomepageController h,SearchInfoController sc,String myAccount,String following,String search,int num,int pagePhotoStatus,int photoIndex)throws Exception{
		homepagec=h;
		this.sc=sc;
		this.myAccount=myAccount;
		this.following=following;
		this.search=search;
		this.num=num;
		start(st);
	}
	public void showWindow(HomepageController h,FollowingPhotoController fc,String myAccount,String following,int num,int pagePhotoStatus,int photoIndex)throws Exception{
		homepagec=h;
		this.fc=fc;
		this.myAccount=myAccount;
		this.following=following;
		this.num=num;
		start(st);
	}
	public void showWindow(HomepageController h,PhotoInfoController pc)throws Exception{
		homepagec=h;
		this.pc=pc;
		start(st);
	}
	public void showWindow(HomepageController h,String myAccount,String following)throws Exception{
		homepagec=h;
		this.myAccount=myAccount;
		this.following=following;
		start(st);
	}
}