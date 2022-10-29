import java.awt.image.BufferedImage;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Transform;
public class ModifyController {

	int bright=0,contrast=0,hue=0,saturation=0;
	Image image;
	Stage stage;
	ColorAdjust coloradjust=new ColorAdjust();
	PhotoController photoC;
	double realWidth,realHeight;
	
	@FXML
	ImageView Img1;
    
	@FXML
    private Slider brightSlider;

    @FXML
    private Slider saturationSlider;

    @FXML
    private Slider contrastSlider;

    @FXML
    private Slider hueSlider;
    /*
    @FXML
    private TextField brightTextField;

    @FXML
    private TextField contrastTextField;

    @FXML
    private TextField hueTextField;

    @FXML
    private TextField saturationTextField;*/
	
	@FXML
    private Label brightLabel;

    @FXML
    private Label contrastLabel;

    @FXML
    private Label hueLabel;

    @FXML
    private Label saturationLabel;

    
    public void initialize()
	{
		/*
    	brightTextField.setText("0");
    	contrastTextField.setText("0");
    	hueTextField.setText("0");
    	saturationTextField.setText("0");*/
    	
		brightSlider.valueProperty().addListener(
		new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<?extends Number> ov,Number oldValue,Number newValue)
			{
				bright =(newValue.intValue() );
				
				brightLabel.setText(String.valueOf(bright));
				coloradjust.setBrightness(bright/200.0);
				Img1.setEffect(coloradjust);
			}
		});
		
		contrastSlider.valueProperty().addListener(
		new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<?extends Number> ov,Number oldValue,Number newValue)
			{
				contrast =(newValue.intValue() );
				contrastLabel.setText(String.valueOf(contrast));
				coloradjust.setContrast(contrast/100.0);
				Img1.setEffect(coloradjust);
			}
		});
		
		hueSlider.valueProperty().addListener(
		new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<?extends Number> ov,Number oldValue,Number newValue)
			{
				hue =(newValue.intValue() );
				hueLabel.setText(String.valueOf(hue));
				coloradjust.setHue(hue/100.0);
				Img1.setEffect(coloradjust);
			}
		});
		
		saturationSlider.valueProperty().addListener(
		new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<?extends Number> ov,Number oldValue,Number newValue)
			{
				saturation =(newValue.intValue() );
				saturationLabel.setText(String.valueOf(saturation));
				coloradjust.setSaturation(saturation/200.0);
				Img1.setEffect(coloradjust);
			}
		});
	}
     public void setPhoto(PhotoController photoC)
    {
    	this.photoC=photoC;
    }
    public void setStage(Stage stage)
    {
    	this.stage=stage;
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
    @FXML
    void savePressed(ActionEvent event) {
    	SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(10,10));
		Image shot=Img1.snapshot(sp, null);
		PixelReader reader = shot.getPixelReader();
		WritableImage newImage = new WritableImage(reader,0,0,(int)image.getWidth()-1,(int)image.getHeight()-1);
    	photoC.setNewImage(newImage);
		stage.hide();
    }

}
