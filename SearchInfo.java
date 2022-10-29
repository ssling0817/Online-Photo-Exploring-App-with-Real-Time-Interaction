import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class SearchInfo extends Application {
	
	 Stage st =new Stage();
	 HomepageController homepagec;
	 int num;
	 int topicStatus=0;
	 int pagePhotoStatus;
	 int photoIndex;
	 SearchController s;
	 String search;
	 @Override
	 public void start(Stage stage) throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchInfo.fxml"));
		 Parent root =(Parent)loader.load();
		 SearchInfoController controller = (SearchInfoController)loader.getController();
		 controller.setStage(stage,homepagec,num,s,pagePhotoStatus,photoIndex,search,this);
		 Scene scene = new Scene(root,500,550); // attach scene graph to scene
		 stage.setTitle("SearchInfo"); // displayed in window's title bar
		 
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	 /*
	 public void showWindow(HomepageController homepagec,int num)throws Exception{
		this.homepagec=homepagec;
		this.viewc=viewc;
		this.num=num;
		this.topicStatus=topicStatus;
		start(st);
	}*/
	 public void showWindow(HomepageController homepagec,int num,SearchController s,int pagePhotoStatus,int photoIndex,String search)throws Exception{
		this.homepagec=homepagec;
		this.s=s;
		this.num=num;
		this.pagePhotoStatus=pagePhotoStatus;
		this.photoIndex=photoIndex;
		this.search=search;
		start(st);
	}
	 public void showWindow(HomepageController homepagec,int num,SearchController s,String search)throws Exception{
		this.homepagec=homepagec;
		this.s=s;
		this.num=num;
		this.pagePhotoStatus=s.getPagePhotoStatus();
		this.photoIndex=s.getPhotoIndex();
		this.search=search;
		start(st);
	}
}