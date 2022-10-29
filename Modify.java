import java.awt.image.BufferedImage;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Modify extends Application{
	BufferedImage bImage;
	Stage st =new Stage();
	PhotoController photoC;
	double realWidth,realHeight;
	public Modify(double realW,double realH)
	{
		realWidth=realW;
		realHeight=realH;
	}
	@Override
	public void start(Stage stage)throws Exception
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Modify.fxml"));
		Parent root = (Parent)loader.load();
		ModifyController controller = (ModifyController)loader.getController();
		controller.setStage(stage);
		controller.setImage(bImage,realWidth,realHeight);
		controller.setPhoto(photoC);
		Scene scene=new Scene(root);
		stage.setTitle("Modify");
		stage.setScene(scene);
		stage.show();
	
		
	}
	public static void main(String[] args)
	{
		launch(args);
	}
	public void showWindow(BufferedImage bImage,PhotoController photoC)throws Exception{
		this.photoC=photoC;
		this.bImage=bImage;
		start(st);
	}
}
