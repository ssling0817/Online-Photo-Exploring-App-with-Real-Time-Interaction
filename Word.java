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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
public class Word extends Application{
	BufferedImage bImage;
	Stage st =new Stage();
	PhotoController photoC;
	double realWidth,realHeight;
	public Word(double realW,double realH)
	{
		realWidth=realW;
		realHeight=realH;
	}
	@Override
	public void start(Stage stage)throws Exception
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Word.fxml"));
		Parent root = (Parent)loader.load();
		WordController controller = (WordController)loader.getController();
		controller.setStage(stage);
		controller.setImage(bImage,realWidth,realHeight);
		controller.setPhoto(photoC,root);
		
		Scene scene=new Scene(root,900,600);
		stage.setTitle("AddText");
		stage.setScene(scene);
		stage.show();
		
		
	}
	public static void main(String[] args)
	{
		launch(args);
	}
	public void showWindow(BufferedImage bImage,PhotoController photoC)throws Exception{
		this.bImage=bImage;
		this.photoC=photoC;
		start(st);
	}
}

