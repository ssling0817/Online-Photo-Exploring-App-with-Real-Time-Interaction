import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.WritableImage;
import javafx.event.ActionEvent;
import java.io.File;

public class FinishEditController {
    @FXML
    private StackPane stackPane;

    @FXML
    private ImageView imageView;
	
	WritableImage writableImage;
	Image image;
	double realHeight,realWidth;
	Stage stage;
	int editStatus;
	HomepageController homepagec;
	File outputFile;
	
	public void setStage(Stage stage,WritableImage writableImage,int editStatus)throws Exception
	{
		this.stage=stage;	
		this.writableImage=writableImage;
		this.editStatus=editStatus;
		image=writableImage;
		double aspectRatio = image.getWidth() / image.getHeight();
  		realWidth = Math.min(imageView.getFitWidth(), imageView.getFitHeight() * aspectRatio);
  		realHeight = Math.min(imageView.getFitHeight(), imageView.getFitWidth() / aspectRatio);
  		imageView.setFitWidth(realWidth);
  		imageView.setFitHeight(realHeight);
		imageView.setImage(image);
		
	}
	public void setStage(Stage stage,WritableImage writableImage,int editStatus,HomepageController homepagec,File outputFile)throws Exception
	{
		this.stage=stage;	
		this.writableImage=writableImage;
		this.editStatus=editStatus;
		this.homepagec=homepagec;
		this.outputFile=outputFile;
		image=writableImage;
		double aspectRatio = image.getWidth() / image.getHeight();
  		realWidth = Math.min(imageView.getFitWidth(), imageView.getFitHeight() * aspectRatio);
  		realHeight = Math.min(imageView.getFitHeight(), imageView.getFitWidth() / aspectRatio);
  		imageView.setFitWidth(realWidth);
  		imageView.setFitHeight(realHeight);
		imageView.setImage(image);
		
	}
	
    @FXML
    void closePressed(ActionEvent event) {
		System.out.println("close");
		 stage.close();
    }

    @FXML
    void uploadPressed(ActionEvent event)throws Exception {
		if(editStatus==0)
		{
			Log log=new Log();
			log.showWindow();
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		else
		{
			Upload upload=new Upload();
			upload.showWindow(homepagec,image,outputFile);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		
    }

}
