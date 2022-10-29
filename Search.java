import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Search extends Application {
	
	 Stage st =new Stage();
	 String search;
	 SearchController controller;
	 HomepageController homepagec;
	 @Override
	 public void start(Stage stage) throws Exception {
		 //System.out.println("start"+search);
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Searching.fxml"));
		 Parent root =(Parent)loader.load();
		 Scene scene = new Scene(root); // attach scene graph to scene
		 controller = (SearchController)loader.getController();
		  //System.out.println(stage);
		 controller.setStage(stage,homepagec,this,search);
		// controller.setSearch();
		 stage.setTitle("Search Image"); // displayed in window's title bar
		 stage.setScene(scene); // attach scene to stage
		 stage.show(); // display the stage
	 }
	 public static void main(String[] args) {
		 
		 launch(args);
	 }
	 public void showWindow(String s,HomepageController homepagec)throws Exception{
		search=s;
		this.homepagec=homepagec;
		start(st);

	}
	public void showWindow(HomepageController homepagec)throws Exception{
		
		this.homepagec=homepagec;
		start(st);

	}
}