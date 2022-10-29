
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.control.ColorPicker;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.scene.control.Button;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Transform;
import javafx.geometry.Pos;

public class Merge extends Application  {
	private static final int TOLERANCE_THRESHOLD = 0xCF;
	StackPane layout;
	ImageView resampledImageView;
	private Button save=new Button("Save");;
	Stage st=new Stage();
	Image image,backgroundImage;
	PhotoController photoC;
	double realWidth,realHeight;
	public Merge(double realW,double realH)
	{
		realWidth=realW;
		realHeight=realH;
		save.getStylesheets().add("smallFont.css");
	}
	private Image makeTransparent(Image inputImage) {
        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(W, H);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);

                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                if (r >= TOLERANCE_THRESHOLD 
                        && g >= TOLERANCE_THRESHOLD 
                        && b >= TOLERANCE_THRESHOLD) {
                    argb &= 0x00FFFFFF;
                }

                writer.setArgb(x, y, argb);
            }
        }

        return outputImage;
    }

    @Override
    public void start(final Stage stage) throws Exception {
    	ImageView backgroundImageView;
		try {
 			 FileChooser chooser=new FileChooser();
 		     chooser.setInitialDirectory(new File("C:\\"));   //設定初始路徑，預設為我的電腦
 		   
 		     chooser.setTitle("Open Image");                //設定視窗標題，預設為“開啟”
 		     chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
 		                                             new FileChooser.ExtensionFilter("PNG", "*.png"));
 		   
 		     File selectedFile= chooser.showOpenDialog(stage);
 		     
 		        if (selectedFile != null) {
 		            String imageFile = selectedFile.toURI().toURL().toString();
 		            Image image = new Image(imageFile);
 		            backgroundImage=image;
 		        } else {
 		            System.out.println("Image file selection cancelled.");
 		        }
 	        }
 		     catch (Exception e) {
 		    	 System.out.println("Error: " + e);
 		     }
			 
		
		
		 
		
        //final Image backgroundImage = new Image(BACKGROUND_IMAGE_LOC);
        backgroundImageView = new ImageView(backgroundImage);
        //backgroundImageView.setFitWidth(image.getWidth()*0.5);
        //backgroundImageView.setFitHeight(image.getHeight()*0.5);
		backgroundImageView.setFitWidth(realWidth);
  		backgroundImageView.setFitHeight(realHeight);
        //final Image originalImage = new Image(ORIGINAL_IMAGE_LOC);
        final ImageView originalImageView = new ImageView(image);
        originalImageView.setFitWidth(image.getWidth()*0.5);
        originalImageView.setFitHeight(image.getHeight()*0.5);
        final Image resampledImage = makeTransparent(image);
        resampledImageView = new ImageView(resampledImage);
		/*
        resampledImageView.setFitWidth(image.getWidth()*0.5);
        resampledImageView.setFitHeight(image.getHeight()*0.5);*/
		resampledImageView.setFitWidth(realWidth);
  		resampledImageView.setFitHeight(realHeight);
		double aspectRatio = image.getWidth() / image.getHeight();
		realWidth = Math.min(resampledImageView.getFitWidth(), resampledImageView.getFitHeight() * aspectRatio);
  		realHeight = Math.min(resampledImageView.getFitHeight(), resampledImageView.getFitWidth() / aspectRatio);
		
        //final HBox images = new HBox(originalImageView, resampledImageView);
        final HBox images = new HBox(resampledImageView);
		
        stage.getIcons().add(image);
		//save
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
			@Override
 		    public void handle(ActionEvent e) 
 		    { 
			System.out.println(backgroundImageView.getLayoutX()+" "+backgroundImageView.getLayoutY());
			System.out.println(image.getWidth()+image.getHeight());
				SnapshotParameters sp =  new SnapshotParameters();
				sp.setTransform(Transform.scale(5,5));
				Image shot=layout.snapshot(sp, null);
				PixelReader reader = shot.getPixelReader();
				WritableImage newImage = new WritableImage(reader,0,0,(int)image.getWidth()-1,(int)image.getHeight()-1);
				photoC.setNewImage(newImage);
				stage.hide();
			}
		};
		save.setOnAction(event);
        layout = new StackPane(backgroundImageView, images);
		final VBox vbox = new VBox(layout, save);
		save.setAlignment(Pos.BOTTOM_RIGHT);
        stage.setScene(new Scene(vbox));
        stage.show();
    }
    public void showWindow(BufferedImage bImage,PhotoController photoC)throws Exception{
		//this.bImage=bImage;
		this.photoC=photoC;
		image = SwingFXUtils.toFXImage(bImage, null);
		start(st);
	}
    public static void main(String[] args) {
        launch(args);
    }
}
