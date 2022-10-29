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
import javafx.event.ActionEvent;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.SnapshotParameters;
import javafx.scene.transform.Transform;

public class ColorRemoveController {
	Image image;
	Stage stage;
	int TOLERANCE_THRESHOLD = 0xFF;
	String  colorString;
	boolean isBackgroundColor;
	int Cx=0,Cy=0;
	int tolerate=5;
	PhotoController photoC;
	double realWidth,realHeight;

	@FXML
    private ImageView Img1;
	public void initialize()
	{
		
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
		 realWidth=realW;
		 realHeight=realH;
		  
		 image = SwingFXUtils.toFXImage(bImage, null);
		 Img1.setImage(image);
		 Img1.setFitWidth(realWidth);
  		 Img1.setFitHeight(realHeight);
		 double aspectRatio = image.getWidth() / image.getHeight();
		 realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
  		 realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
	}
    @FXML
    void mouseClicked(MouseEvent event) {
    	double aspectRatio = image.getWidth() / image.getHeight();
    	double realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
    	double realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
    	Cx=(int)(event.getX()*image.getWidth()/realWidth);
    	Cy=(int)(event.getY()*image.getHeight()/realHeight);
    	
    	makeTransparent(image);
    }

    private void makeTransparent(Image inputImage) 
    {
        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(W, H);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        //
        int FLAG = reader.getArgb(Cx, Cy);
        
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);
                
				isBackgroundColor=(((argb>(FLAG-tolerate))&&((argb<(FLAG)+tolerate)))||(((argb>>16)>((FLAG>>16)-tolerate))&&((argb>>16)<((FLAG>>16)+tolerate))||(((argb>>8)>((FLAG>>8)-tolerate))&&((argb>>8)<((FLAG>>8)+tolerate)))));
                if(isBackgroundColor)
				{	
					int red = ((argb >> 16) & 0xff);
	            	int green = ((argb >> 8) & 0xff);
	            	int blue = (argb & 0xff);
					int grayLevel = (int) (0.2162 * (double)red + 0.7152 * (double)green + 0.0722 * (double)blue) / 3;
					grayLevel = 255 - grayLevel; // Inverted the grayLevel value here.
					int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

					writer.setArgb(x, y, -gray);
					
				}
				else
				{
					writer.setArgb(x, y, argb);
				}
                
            }
        } 
       
        image=outputImage;
        Img1.setImage(outputImage);
    }
	 @FXML
    void savePressed(ActionEvent event) {
		SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(10,10));
		Image shot=Img1.snapshot(sp, null);
		PixelReader reader = shot.getPixelReader();
		System.out.println((int)image.getWidth()+" "+(int)image.getHeight());
		WritableImage newImage = new WritableImage(reader,0,0,(int)image.getWidth()-1,(int)image.getHeight()-1);
    	photoC.setNewImage(newImage);
		stage.hide();
    }
}
