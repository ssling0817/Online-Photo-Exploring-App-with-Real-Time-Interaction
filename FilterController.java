import java.awt.image.BufferedImage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.control.ComboBox;
import javafx.event.EventHandler;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Transform;

public class FilterController {
	
	Stage stage;
	Image image;
	PhotoController photoC;
	double realWidth,realHeight;
	
	//filter
	ColorAdjust monochrome = new ColorAdjust();
	ColorAdjust saturation = new ColorAdjust();
	
	ColorAdjust A = new ColorAdjust();
	ColorAdjust B = new ColorAdjust();
	ColorAdjust C = new ColorAdjust();
	ColorAdjust D = new ColorAdjust();
	ColorAdjust E = new ColorAdjust();
	ColorAdjust F = new ColorAdjust();
	ColorAdjust G = new ColorAdjust();
	
	//int filterStatus=0;
	
	@FXML
    private ImageView Img1;
	 @FXML
	private ComboBox<String> filterComboBox;


	public void initialize()
	{
		//filter
    	monochrome.setSaturation(-1);
    	
    	saturation.setContrast(0.1);
    	saturation.setHue(-0.05);
    	saturation.setBrightness(0.1);
    	saturation.setSaturation(0.2);
    	
    	//A
    	A.setContrast(-0.15);
    	A.setHue(0);
    	A.setBrightness(0.41);
    	A.setSaturation(0);
    	//B
    	B.setContrast(-0.22);
    	B.setHue(-0.07);
    	B.setBrightness(0.21);
    	B.setSaturation(-0.25);
    	//C
    	C.setContrast(-0.15);
    	C.setHue(0.03);
    	C.setBrightness(0.15);
    	C.setSaturation(0);
    	//D
    	D.setContrast(-0.01);
    	D.setHue(-0.06);
    	D.setBrightness(-0.32);
    	D.setSaturation(-0.32);
		//E
    	E.setContrast(-0.07);
    	E.setHue(-0.04);
    	E.setBrightness(0.29);
    	E.setSaturation(-0.15);
    	//F
    	F.setContrast(-0.24);
    	F.setHue(-0.03);
    	F.setBrightness(0.1);
    	F.setSaturation(-0.12);
		//G
    	G.setContrast(0.03);
    	G.setHue(0.03);
    	G.setBrightness(0.28);
    	G.setSaturation(0.09);
    	
    	filterComboBox.getItems().addAll("BRIGHT","COLD","FRESH","DARK","FOGGY","GRAY","FRUIT");
    	filterComboBox.getSelectionModel().select("BRIGHT");
		
		// Create action event 
        EventHandler<ActionEvent> event = 
                  new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	if(filterComboBox.getValue()=="BRIGHT")
                {
            		Img1.setEffect(A);
                }
            	else if(filterComboBox.getValue()=="COLD")
                {
            		Img1.setEffect(B);
                }
            	if(filterComboBox.getValue()=="FRESH")
                {
            		Img1.setEffect(C);
                }
            	else if(filterComboBox.getValue()=="DARK")
                {
            		Img1.setEffect(D);
                }
				else if(filterComboBox.getValue()=="FOGGY")
                {
            		Img1.setEffect(E);
                }
				else if(filterComboBox.getValue()=="GRAY")
                {
            		Img1.setEffect(F);
                }
				else if(filterComboBox.getValue()=="FRUIT")
                {
            		Img1.setEffect(G);
                }
            } 
        }; 
        // Set on action 
        filterComboBox.setOnAction(event); 

	}
	 public void setImage(BufferedImage bImage,double realW,double realH)
	 { 
		 image = SwingFXUtils.toFXImage(bImage, null);
		 Img1.setImage(image);
		 realWidth=realW;
		 realHeight=realH;
		 Img1.setFitWidth(realWidth);
  		 Img1.setFitHeight(realHeight);
		 double aspectRatio = image.getWidth() / image.getHeight();
		 realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
  		 realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
		
	 }
	 public void setStage(Stage stage)
	 {
	 	this.stage=stage;
	 }
	 public void setPhoto(PhotoController photoC)
    {
    	this.photoC=photoC;
    }
	  @FXML
    void savePressed(ActionEvent event) {
		SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(10,10));
		Image shot=Img1.snapshot(sp, null);
		PixelReader reader = shot.getPixelReader();
		//System.out.println((int)image.getWidth()+" "+(int)image.getHeight());
		WritableImage newImage = new WritableImage(reader,0,0,(int)image.getWidth()-1,(int)image.getHeight()-1);
    	photoC.setNewImage(newImage);
		stage.hide();
    }
}
