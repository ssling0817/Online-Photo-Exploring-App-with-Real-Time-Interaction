
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import javafx.scene.image.WritableImage;


public class Photo extends Application{
	
	Stage st =new Stage();
	WritableImage writableImage;
	int editStatus=0;
	HomepageController homepagec;
	@Override
	public void start(Stage stage)throws Exception
	{
		
		//Parent root=FXMLLoader.load(getClass().getResource("Photo.fxml"));
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Photo.fxml"));
		Parent root = (Parent)loader.load();
		PhotoController controller = (PhotoController)loader.getController();
		//System.out.println(stage);
		if(editStatus==0)
			controller.setStage(stage);
		else if(editStatus==1)
			controller.setStage(stage,writableImage,1,homepagec);
		
		Scene scene=new Scene(root);
		stage.setTitle("Photo");
		stage.setScene(scene);
		stage.show();
	
		
		
	}
	public static void main(String[] args)
	{
		launch(args);
	}
	public void showWindow()throws Exception{
		start(st);
	}
	public void showWindow(WritableImage writableImage,HomepageController homepagec)throws Exception{
		this.writableImage=writableImage;
		this.homepagec=homepagec;
		editStatus=1;
		start(st);
	}

}
