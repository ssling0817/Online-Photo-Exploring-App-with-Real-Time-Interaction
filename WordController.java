import javafx.scene.control.TextField;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.event.EventHandler;
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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import javafx.scene.Parent;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Transform;


public class WordController {

	int outputX;
	int outputY;
	Image image;
	boolean movable=false;
	TextField text=new TextField();
	double textWidth,textHeight;
	double realWidth,realHeight;
	Point2D point=new Point2D(0,0);
	PhotoController photoC;
	Parent root;
	Stage stage;
    @FXML
    private StackPane stackPane;

    @FXML
    private ImageView Img1;
    
    @FXML
    private ColorPicker colorPicker;
    
    @FXML
    private Slider fontSlider;
    @FXML
    private Slider thickSilder;
    @FXML
    private Label fontLabel;
    @FXML
    private Label thickLabel;


    String colorString;
    double fontValue=20;
    int thickValue=0;
	String f;
	
    public void initialize()
    {
    	colorPicker.valueProperty().addListener((t,oldVal,newVal)->{
    		colorString=FxUtils.toRGBCode( newVal );
    		text.setStyle("-fx-text-fill: " + colorString + ";");
    	});
    	fontSlider.valueProperty().addListener((t,oldVal,newVal)->{
    		fontValue=(double)newVal;
    		fontLabel.setText(String.valueOf((int)fontValue));
    		text.setFont(Font.font(f, FontWeight.BOLD, fontValue));
    		textWidth=text.getWidth();
        	textHeight=text.getHeight();
    	});
    	thickSilder.valueProperty().addListener((t,oldVal,newVal)->{
    		
    		double tempValue=(double)newVal;
    		thickValue=(int)tempValue;
    		thickLabel.setText(String.valueOf((int)thickValue));
    		
    		if(thickValue==10)
			{
				f="Segoe Print";
    			text.setFont(Font.font("Segoe Print", FontWeight.BOLD, fontValue));
			}
			else if(thickValue==9)
			{
				f="Serif";
    			text.setFont(Font.font("Serif", FontWeight.BOLD, fontValue));
			}
    		else if(thickValue==8)
			{
				f="MT Extra";
    			text.setFont(Font.font("MT Extra", FontWeight.BOLD, fontValue));
			}
    		else if(thickValue==7)
			{
				f="Lucida Console";
    			text.setFont(Font.font("Lucida Console", FontWeight.BOLD, fontValue));
			}
    		else if(thickValue==6)
			{
				f="MS Gothic";
    			text.setFont(Font.font("MS Gothic", FontWeight.BOLD, fontValue));
			}
    		else if(thickValue==5)
			{
				f="MV Boli";
    			text.setFont(Font.font("MV Boli", FontWeight.BOLD, fontValue));
			}
    		else if(thickValue==4)
			{
				f="Century Gothic";
    			text.setFont(Font.font("Century Gothic", FontWeight.BOLD, fontValue));
			}
    		else if(thickValue==3)
			{
				f="Charline";
    			text.setFont(Font.font("Charline", FontWeight.BOLD, fontValue));
			}
    		else if(thickValue==2)
			{
				f="Corbel Light";
    			text.setFont(Font.font("Corbel Light", FontWeight.BOLD, fontValue));
			}
    		else if(thickValue==1)
			{
				f="DFKai-SB";
    			text.setFont(Font.font("DFKai-SB", FontWeight.BOLD, fontValue));
			}
    		else
			{
				f="SimSun";
    			text.setFont(Font.font("SimSun", FontWeight.BOLD, fontValue));
			}
    	});
    	
    	text.setLayoutX(0);
    	text.setLayoutY(0);
    }
    
    @FXML
    void addTextpressed(ActionEvent event) {
    	
    	/*text.setOnAction(event -> {
            text.setFill(colorPicker.getValue());
            event.consume();
        });*/
    	//text.setStyle("-fx-text-fill: " + colorString + ";");
    	//text.setFont(Font.font("Verdana", FontWeight.BOLD, fontValue));
    	text.setAlignment(Pos.CENTER);
    	text.setBackground(Background.EMPTY);
    	text.setBorder(Border.EMPTY);
    	text.setPadding(Insets.EMPTY);
    	text.setPrefColumnCount(10);
    	stackPane.getChildren().add(text);
    	stackPane.setAlignment(Pos.CENTER);
    	point = text.localToScene(0.0, 0.0);
    	textWidth=text.getWidth();
    	textHeight=text.getHeight();
    	System.out.println(point.getX()+point.getY()+textWidth+textHeight);
    }
    
    public void setStage(Stage stage)
    {
    	this.stage=stage;
    }
	public void setPhoto(PhotoController photoC,Parent root)
    {
    	this.photoC=photoC;
		this.root=root;

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
		// System.out.println("Img1 "+Img1.getLayoutX()+" "+Img1.getLayoutY());
		 final int outputX=(int)(Img1.getLayoutX()*5);
		 final int outputY=(int)(Img1.getLayoutY()*5);
		 this.outputX=outputX;
		 this.outputY=outputY;
	 }
    @FXML
    void mouseClicked(MouseEvent event) {
    	System.out.println("enter clicked...");
    	/*
    	if((event.getX()<(point.getX()+textWidth+10))&&(event.getX()>(point.getX()-10)))
    	{
    		if((event.getY()<(point.getY()+textHeight+10))&&(event.getY()>(point.getY()-10)))
    		{
    			
    			System.out.println("GOTITTTTTTTTTTTTTTTT");
    			System.out.println("getx and y "+event.getX()+event.getY());
    			movable=true;
    		}
    	}*/
    	TranslateTransition transition = new TranslateTransition(Duration.millis(400), text);
    	//transition.setToX(event.getX()-300);
    	//transition.setToY(event.getY()-200);
		transition.setToX(event.getSceneX()-Img1.getLayoutX()-Img1.getFitWidth()/2);
    	transition.setToY(event.getSceneY()-Img1.getLayoutY()-Img1.getFitHeight()/2);
    	System.out.println("getx and y "+(event.getSceneX()-Img1.getLayoutX())+" "+(event.getSceneY()-Img1.getLayoutY()));
    	transition.play();
    }

    @FXML
    void mouseDragged(MouseEvent event) {
    	if(movable)
    	{
    		System.out.println("moving...");
    		text.setLayoutX(event.getX());
    		text.setLayoutY(event.getY());
    	}
    }

    @FXML
    void mouseReleased(MouseEvent event) {
    	movable=false;
    }
    @FXML
    void savePressed(ActionEvent event) {
		
		SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(5,5));
		Image shot=root.snapshot(sp, null);
		PixelReader reader = shot.getPixelReader();
		System.out.println("img1 "+(int)(Img1.getLayoutX()*5)+" "+(int)(Img1.getLayoutY()*5));
		System.out.println("image "+(int)image.getWidth()+" "+(int)image.getHeight());
		WritableImage newImage = new WritableImage(reader,(int)(Img1.getLayoutX()*5),(int)(Img1.getLayoutY()*5),(int)image.getWidth(),(int)image.getHeight());
    	photoC.setNewImage(newImage);
		stage.hide();
		/*
		//0620
		SnapshotParameters sp =  new SnapshotParameters();
	
	//	PixelReader reader = image.getPixelReader();
		//WritableImage newImage = new 
		//WritableImage(reader,100,89,(int)image.getWidth(),(int)image.getHeight());
    	sp.setTransform(Transform.scale(5,5));
		Image shot=root.snapshot(sp, null);
		PixelReader reader = shot.getPixelReader();
		//System.out.println((int)image.getWidth()+" "+(int)image.getHeight());
		//System.out.println(Img1.getLayoutX()+" "+Img1.getLayoutY());
		System.out.println("output "+outputX+" "+outputY);
		WritableImage newImage = new WritableImage(reader,outputX,outputY,(int)image.getWidth(),(int)image.getHeight());
    	//BufferedImage bImage = SwingFXUtils.fromFXImage(shot, null);
    	photoC.setNewImage(newImage);
		stage.hide();*/
    }
}

class FxUtils
{
    public static String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ) );
    }
}