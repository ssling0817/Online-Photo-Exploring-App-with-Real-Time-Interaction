//reset 還沒修好
import javafx.scene.input.MouseEvent;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.Scanner;
import javax.swing.*;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Scale;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.beans.InvalidationListener;
import javafx.scene.input.ScrollEvent;
import java.io.IOException;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.beans.Observable;
import javafx.scene.input.ScrollEvent;
import javafx.beans.property.DoubleProperty; 
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler; 
import javafx.scene.effect.ColorAdjust;
import javafx.scene.Node;
import javafx.scene.CacheHint; 
import javafx.scene.SnapshotParameters;
import javafx.scene.shape.Line;
import javafx.scene.layout.StackPane;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.application.Platform;
import java.util.concurrent.CompletableFuture;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Bounds;


public class PhotoController {
	private int status=0;
	private Stage stage;
	private double aspectRatio,realWidth,realHeight ;
	private static double realW,realH;
	final DoubleProperty zoomProperty = new SimpleDoubleProperty(200); 
	String imageFile,originalImageFile;
	private Image image,originalImage;
	
	//cut parameter
	int clickStatus=0,cutstatus=0;
	double clickX1=0,clickY1=0,clickX2=0,clickY2=0;  // orignal
	Line line1,line2,line3,line4;
	double cutXMin,cutXMax,cutYMin,cutYMax;
	double bCutX1,bCutY1,bCutX2,bCutY2;
	
    @FXML private Button Input;
    @FXML private Button SaveButton;
    @FXML private ImageView Img1;
    @FXML private BorderPane BPane;
    
	double ratio; //image vs imageView 
    int scaledImageSize;
	boolean rotateFirst=true;
	boolean phototType=true;
	
	//original press
	Image tempImage;
	double oriWidth,oriHeight,tempWidth,tempHeight;
	double tempRotate;
	
	
	
	@FXML
    private Button returnButton;

    @FXML
    private Button rotateButton;

    @FXML
    private Button colorButton;

    @FXML
    private Button filterButton;

    @FXML
    private Button modifyButton;

    @FXML
    private Button drawButton;

    @FXML
    private Button wordButton;

    @FXML
    private Button mergeButton;
	
	@FXML
    private Button cutButton;
	
	int editStatus=0;
	HomepageController homepagec;
	
    public void initialize()
    {
		/*
		if(image==null)
		{
			//statusbar.getChildren().add(confirm);
			try {
				 FileChooser chooser=new FileChooser();
				 chooser.setInitialDirectory(new File("C:\\"));   //設定初始路徑，預設為我的電腦
			   
				 chooser.setTitle("Open Image");                //設定視窗標題，預設為“開啟”
				 chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
														 new FileChooser.ExtensionFilter("PNG", "*.png"));
				 //System.out.println(stage);
				 File selectedFile= chooser.showOpenDialog(stage);
				 
				if (selectedFile != null) {
					// load
					imageFile = selectedFile.toURI().toURL().toString();
					originalImageFile = new String(imageFile);
					System.out.println(originalImageFile);
					image = new Image(imageFile);
						
					//adjust Img1 ratio
					double aspectRatio = image.getWidth() / image.getHeight();
					realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
					realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
					Img1.setFitWidth(realWidth);
					Img1.setFitHeight(realHeight);
					if(realWidth>realHeight)
					{
						phototType=true;
					}
					else
					{
						phototType=false;
					}
					Img1.setImage(image);
					System.out.println(image.getWidth()+" "+image.getHeight());
					System.out.println(Img1.getFitWidth()+"  "+Img1.getFitHeight()+"  "+realWidth+"  "+realHeight);
						
					//set scale ratio
					ratio = image.getWidth()/Img1.getFitWidth();
						
					//high resolution
					Img1.setCache(true);
					Img1.setCacheHint(CacheHint.SPEED);
						
					// TA
						
					//Bounds b = Img1.getBoundsInParent();
					//System.out.println("TA:"+b.getMinX()+" "+b.getMinY()+" "+b.	getMaxX()+" "+b.getMaxY());
						
				} else {
					System.out.println("Image file selection cancelled.");
				}

			}
			
			
			catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}*/
			/*
    	zoomProperty.addListener(new InvalidationListener()
    	{
    		@Override
    		public void invalidated(Observable arg0)
    		{
    			if(status==1)
    			{
    				Img1.setFitWidth(zoomProperty.get()*2);
    				Img1.setFitHeight(zoomProperty.get()*3);
    		
    			}
    		}
    	});
    	BPane.addEventFilter(ScrollEvent.ANY,new EventHandler<ScrollEvent>()
    	{
    		@Override
    		public void handle(ScrollEvent event)
    		{
    			if(status==1)
    			{
    				if(event.getDeltaY()>0)
    				{
    					zoomProperty.set(zoomProperty.get()*1.2);
    				}
    				else if(event.getDeltaY()<0)
    				{
    					zoomProperty.set(zoomProperty.get()/1.1);
    				}
    			}
    		}
    	});*/
		
    }
    
   
    
    public void setStage(Stage stage)
    {
    	this.stage=stage;
		if(image==null)
		{
			//statusbar.getChildren().add(confirm);
			try {
				 FileChooser chooser=new FileChooser();
				 chooser.setInitialDirectory(new File("C:\\"));   //設定初始路徑，預設為我的電腦
			   
				 chooser.setTitle("Open Image");                //設定視窗標題，預設為“開啟”
				 chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
														 new FileChooser.ExtensionFilter("PNG", "*.png"));
				 //System.out.println(stage);
				 File selectedFile= chooser.showOpenDialog(stage);
				 
				if (selectedFile != null) {
					// load
					imageFile = selectedFile.toURI().toURL().toString();
					originalImageFile = new String(imageFile);
					//System.out.println(originalImageFile);
					image = new Image(imageFile);
					originalImage=image;
					//adjust Img1 ratio
					double aspectRatio = image.getWidth() / image.getHeight();
					realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
					realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
					oriWidth=realWidth;
					oriHeight=realHeight;
					Img1.setFitWidth(realWidth);
					Img1.setFitHeight(realHeight);
					if(realWidth>realHeight)
					{
						phototType=true;
					}
					else
					{
						phototType=false;
					}
					Img1.setImage(image);
					System.out.println(image.getWidth()+" "+image.getHeight());
					System.out.println("Img1: "+Img1.getFitWidth()+"  "+Img1.getFitHeight()+"  "+realWidth+"  "+realHeight);
						
					//set scale ratio
					ratio = image.getWidth()/Img1.getFitWidth();
						
					//high resolution
					Img1.setCache(true);
					Img1.setCacheHint(CacheHint.SPEED);
						
					// TA
						
					//Bounds b = Img1.getBoundsInParent();
					//System.out.println("TA:"+b.getMinX()+" "+b.getMinY()+" "+b.	getMaxX()+" "+b.getMaxY());
						
				} else {
					System.out.println("Image file selection cancelled.");
				}

			}
			
			
			catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
    }
	public void setStage(Stage stage,WritableImage writableImage,int editStatus,HomepageController homepagec)
    {
    	this.stage=stage;
		this.homepagec=homepagec;
		this.editStatus=editStatus;
		image=writableImage;
		originalImage=image;
		double aspectRatio = image.getWidth() / image.getHeight();
  		realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
  		realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
		oriWidth=realWidth;
		oriHeight=realHeight;
		//System.out.println("new size"+realWidth+" "+realHeight);
  		Img1.setFitWidth(realWidth);
  		Img1.setFitHeight(realHeight);
		if(realWidth>realHeight)
		{
			phototType=true;
		}
		else
		{
			phototType=false;
		}
		Img1.setImage(image);
		originalImage=image;
		//set scale ratio
		ratio = image.getWidth()/Img1.getFitWidth();
  		            
		//high resolution
  		Img1.setCache(true);
  		Img1.setCacheHint(CacheHint.SPEED);
    }
/*
    @FXML
    void confirmPressed(ActionEvent event) {
    	if(status==2)
    	{
			
    		
			BPane.setTop(null);
			line1=null;
			line2=null;
			line3=null;
			line4=null;
			clickX1=0;
			clickX2=0;
			clickY1=0;
			clickY2=0;
			clickStatus=0;

    	}
    	status=0;
    	Img1.setFitWidth(realWidth);
  	    Img1.setFitHeight(realHeight);
  	    System.out.println(Img1.getFitWidth()+"  "+Img1.getFitHeight()+"  "+realWidth+"  "+realHeight);
    }
*/
    @FXML
    void enlargePressed(ActionEvent event) {
    	status=1;
    }
    
    @FXML
    void mouseClicked(MouseEvent event) {
    	if(status==1)
    	{
    		double x=event.getX();
    		double y=event.getY();
    		
    		Scale newScale = new Scale(); 
    	    newScale.setPivotX(event.getX()); 
    	    newScale.setPivotY(event.getY()); 
    	    newScale.setX(1.05);
    	    newScale.setY(1.05);
    	    Img1.getTransforms().add(newScale);
    	}
		//cutting
    	else if(status==2)
    	{
    			//BPane.setTop(statusbar);
			if(clickStatus==0)//左上
			{
				Point2D sceneCoords1 = new Point2D(event.getSceneX(), event.getSceneY());
				clickX1=sceneCoords1.getX();
				clickY1=sceneCoords1.getY();
				//0619
				Point2D BPaneCoords1 = Img1.sceneToLocal(sceneCoords1);
				bCutX1=BPaneCoords1.getX();
				bCutY1=BPaneCoords1.getY();
				
				clickStatus=1;
			}
			else if(clickStatus==1)//右下
			{
				Point2D sceneCoords2 = new Point2D(event.getSceneX(), event.getSceneY());  
				clickX2=sceneCoords2.getX();
				clickY2=sceneCoords2.getY();
 		        
				//0619
				Point2D BPaneCoords2 = Img1.sceneToLocal(sceneCoords2);
				bCutX2=BPaneCoords2.getX();
				bCutY2=BPaneCoords2.getY();
				
				System.out.println("click y: "+clickY1+" "+clickY2);
				//System.out.println("click 0619y: "+bCutY1+" "+bCutY2);
				cutXMax=Math.max(clickX1,clickX2);
				cutXMin=Math.min(clickX1,clickX2);
				cutYMax=Math.max(clickY1,clickY2);
				cutYMin=Math.min(clickY1,clickY2);
				
					
				line1.setStartX(cutXMin);
				line1.setStartY(cutYMin);
				line1.setEndX(cutXMax);
				line1.setEndY(cutYMin);
					//
				line2.setStartX(cutXMin);
				line2.setStartY(cutYMin);
				line2.setEndX(cutXMin);
				line2.setEndY(cutYMax);
					//
				line3.setStartX(cutXMin);
				line3.setStartY(cutYMax);
				line3.setEndX(cutXMax);
				line3.setEndY(cutYMax);
					//
				line4.setStartX(cutXMax);
				line4.setStartY(cutYMin);
				line4.setEndX(cutXMax);
				line4.setEndY(cutYMax);
				
				clickStatus=2;	
			}
			else if(clickStatus==2)
			{
				Point2D sceneCoords1 = new Point2D(event.getSceneX(), event.getSceneY());
				clickX1=sceneCoords1.getX();
				clickY1=sceneCoords1.getY();
				
				//0619
				Point2D BPaneCoords1 = Img1.sceneToLocal(sceneCoords1);
				bCutX1=BPaneCoords1.getX();
				bCutY1=BPaneCoords1.getY();
				
				System.out.println("click y: "+clickY1+" "+clickY2);
				cutXMax=Math.max(clickX1,clickX2);
				cutXMin=Math.min(clickX1,clickX2);
				cutYMax=Math.max(clickY1,clickY2);
				cutYMin=Math.min(clickY1,clickY2);
					
				line1.setStartX(cutXMin);
				line1.setStartY(cutYMin);
				line1.setEndX(cutXMax);
				line1.setEndY(cutYMin);
					//
				line2.setStartX(cutXMin);
				line2.setStartY(cutYMin);
				line2.setEndX(cutXMin);
				line2.setEndY(cutYMax);
					//
				line3.setStartX(cutXMin);
				line3.setStartY(cutYMax);
				line3.setEndX(cutXMax);
				line3.setEndY(cutYMax);
					//
				line4.setStartX(cutXMax);
				line4.setStartY(cutYMin);
				line4.setEndX(cutXMax);
				line4.setEndY(cutYMax);
				
				clickStatus=1;
			}
				/*
    			EventHandler<ActionEvent> eventt = new EventHandler<ActionEvent>() { 
 		            public void handle(ActionEvent e) 
 		            { 
 		            	//System.out.println("in:");
 		            	Point2D BPaneCoords1 = Img1.sceneToLocal(sceneCoords1);
 		            	Point2D BPaneCoords2 = Img1.sceneToLocal(sceneCoords2);
 		            	System.out.println("image size:");
 		            	System.out.println(image.getWidth()+"  "+image.getHeight()+"  "+realWidth+"  "+realHeight);
 		            	System.out.println(sceneCoords1.getX()+" "+sceneCoords1.getY()+" "+sceneCoords2.getX()+" "+sceneCoords2.getY());
 		            	System.out.println(BPaneCoords1.getX()+" "+BPaneCoords1.getY()+" "+BPaneCoords2.getX()+" "+BPaneCoords2.getY());
 		            	//rec=new Rectangle2D(BPaneCoords1.getX()*image.getWidth()/realWidth,BPaneCoords1.getY()*image.getHeight()/realHeight,(BPaneCoords2.getX()-BPaneCoords1.getX())*image.getWidth()/realWidth,(BPaneCoords2.getY()-BPaneCoords1.getY())*image.getHeight()/realHeight);
 		            	
 		            	//rec=new //Rectangle2D(BPaneCoords1.getX(),BPaneCoords1.getY(),Math.abs(BPaneCoords2.getX()-BPaneCoords1.getX()),Math.abs(BPaneCoords2.getY()-BPaneCoords1.getY()));
 		            	//realWidth=rec.getWidth();
 		            	//realHeight=rec.getHeight();
 		            	//Img1.setViewport(rec);
 		            	
						
 		            	PixelReader reader = image.getPixelReader();
 		            	int hereWidth=(int)(Math.abs(BPaneCoords2.getX()-BPaneCoords1.getX()));
 		            	int hereHeight=(int)(Math.abs(BPaneCoords2.getY()-BPaneCoords1.getY()));
 		            	System.out.println("here    "+hereWidth+" "+hereHeight);
						
						// TA
						double x = (clickX1-450)*ratio+image.getWidth()/2;
						double y = (clickY1-300)*ratio+image.getHeight()/2;
 		            	int changeWidth=(int)((clickX2-clickX1)*ratio);
 		            	int changeHeight=(int)((clickY2-clickY1)*ratio);
 		            	
						System.out.println("changed "+changeWidth+" "+changeHeight);
 		            	//這裡xy座標都對的怪怪的
 		            	WritableImage newImage = new WritableImage(reader, (int)x, (int)y, changeWidth, changeHeight);
 		            	//BPane.getChildren().remove(Img1);
 		            	//System.out.println("checkedxy "+newImage.getX()+" "+newImage.getY());
 		            	image=newImage;
						ratio = image.getWidth()/Img1.getFitWidth();
 		            	
 		            	double aspectRatio = newImage.getWidth() / newImage.getHeight();
 	  		    	    realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
 	  		    	    realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
 	  		    	   
 		            	//Img1=new ImageView(newImage);
 	  		    	    Img1.setImage(newImage);
 		            	Img1.setFitWidth(realWidth);
 		         	    Img1.setFitHeight(realHeight);
 		            	//BPane.setCenter(Img1);
 		            	//realWidth=hereWidth;
 		            	//realHeight=hereHeight;
 		            	BPane.getChildren().remove(line1);
 		            	BPane.getChildren().remove(line2);
 		            	BPane.getChildren().remove(line3);
 		            	BPane.getChildren().remove(line4);
 		            	//BPane.getChildren().remove(confirm);
 		            	
 		            	clickStatus=0;
 		       
 		            	
 		            } 
 		        }; 
				*/
				
 		        //confirm.setOnAction(eventt); 
 		        /*
 		       EventHandler<ActionEvent> eventsave = new EventHandler<ActionEvent>() { 
		            public void handle(ActionEvent e) 
		            { 
		            	
		            	SnapshotParameters sp =  new SnapshotParameters();
		            	//這個scale會影響最後大小而且button移不掉
		           	    sp.setTransform(Transform.scale(3, 3));
		            	BufferedImage buImage = SwingFXUtils.fromFXImage(Img1.snapshot(sp, null), null);
		           	    //BufferedImage buImage=SwingFXUtils.fromFXImage(exportPng(Img1), null);
		            	Image imagenew = SwingFXUtils.toFXImage(buImage, null);
		           	    image=imagenew;
		           	   
		      		    double aspectRatio = image.getWidth() / image.getHeight();
		      	        realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
		      	        realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
		      	        
		      	        rec=new Rectangle2D(0,0,image.getWidth(),image.getHeight());
		      	        Img1.setImage(image);
		      	        BPane.getChildren().remove(save);
		      	        System.out.println(image.getWidth()+"  "+image.getHeight()+"  "+realWidth+"  "+realHeight);
		      	        BPane.getChildren().remove(confirm);
		      	        BPane.getChildren().remove(save);
		      	        status=0;
		            } 
		        }; 
		        save.setOnAction(eventsave); 
    		        */
    		    
				//clickStatus=2;
    		//}
			/*
    		else if(clickStatus==1)
    		{
    			sceneCoords1 = new Point2D(event.getSceneX(), event.getSceneY());
    			clickX1=event.getSceneX();
     		    clickY1=event.getSceneY();
     		    //
        		line1.setStartX(clickX1);
        		line1.setStartY(clickY1);
        		line1.setEndX(clickX2);
        		line1.setEndY(clickY1);
        		//
        		line2.setStartX(clickX1);
        		line2.setStartY(clickY1);
        		line2.setEndX(clickX1);
        		line2.setEndY(clickY2);
        		//
        		line3.setStartX(clickX1);
        		line3.setStartY(clickY2);
        		line3.setEndX(clickX2);
        		line3.setEndY(clickY2);
        		//
        		line4.setStartX(clickX2);
        		line4.setStartY(clickY1);
        		line4.setEndX(clickX2);
        		line4.setEndY(clickY2);
        		
    			clickStatus=2;
    		}
    		else if(clickStatus==2)
    		{
    			sceneCoords2 = new Point2D(event.getSceneX(), event.getSceneY());
    			clickX2=event.getSceneX();
     		    clickY2=event.getSceneY();
     		   //
				line1.setStartX(clickX1);
				line1.setStartY(clickY1);
				line1.setEndX(clickX2);
				line1.setEndY(clickY1);
				//
				line2.setStartX(clickX1);
				line2.setStartY(clickY1);
				line2.setEndX(clickX1);
				line2.setEndY(clickY2);
				//
				line3.setStartX(clickX1);
				line3.setStartY(clickY2);
				line3.setEndX(clickX2);
				line3.setEndY(clickY2);
				//
				line4.setStartX(clickX2);
				line4.setStartY(clickY1);
				line4.setEndX(clickX2);
				line4.setEndY(clickY2);
        		clickStatus=1;
    		}*/
    		
    		
    	}
    }
    private WritableImage exportPng(final ImageView node) {

        //final int w = (int) node.getLayoutBounds().getWidth();
        //final int h = (int) node.getLayoutBounds().getHeight();
    	final int w = (int) node.getFitWidth();
        final int h = (int) node.getFitHeight();
        final WritableImage full = new WritableImage(w, h);

        // defines the number of tiles to export (use higher value for bigger resolution)
        final int size = 2; 
        final int tileWidth = w / size;
        final int tileHeight = h / size;

        System.out.println("Exporting node (building " + (size * size) + " tiles)");

        try {
            for (int col = 0; col < size; ++col) {
                for (int row = 0; row < size; ++row) {

                    final int x = row * tileWidth;
                    final int y = col * tileHeight;
                    final SnapshotParameters params = new SnapshotParameters();
                    params.setViewport(new Rectangle2D(x, y, tileWidth, tileHeight));   

                    final CompletableFuture<Image> future = new CompletableFuture<>();

                    // keeps fx application thread unblocked
                    Platform.runLater(() -> future.complete(node.snapshot(params, null)));
                    full.getPixelWriter().setPixels(x, y, tileWidth, tileHeight, future.get().getPixelReader(), 0, 0);
                }
            }

            //System.out.println("Exporting node (saving to file)");
            
            //ImageIO.write(SwingFXUtils.fromFXImage(full, null), "png", new File(filePath));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return full;
    }
    @FXML
    void cutPressed(ActionEvent event) {
    	status=2;
    	//System.out.println("adding!!!!");
		if(cutstatus==0)
		{
			line1=new Line(clickX1,clickY1,clickX2,clickY1);//上
			line2=new Line(clickX1,clickY1,clickX1,clickY2);//左
			line3=new Line(clickX1,clickY2,clickX2,clickY2);//下
			line4=new Line(clickX2,clickY1,clickX2,clickY2);//右
			BPane.getChildren().add(line1);
			BPane.getChildren().add(line2);
			BPane.getChildren().add(line3);
			BPane.getChildren().add(line4);
			cutstatus=1;
			returnButton.setVisible(false);
			rotateButton.setVisible(false);
			colorButton.setVisible(false);
			filterButton.setVisible(false);
			modifyButton.setVisible(false);
			drawButton.setVisible(false);
			wordButton.setVisible(false);
			mergeButton.setVisible(false);
			SaveButton.setVisible(false);
			cutButton.setText("Confirm");
		}
		else
		{
			returnButton.setVisible(true);
			rotateButton.setVisible(true);
			colorButton.setVisible(true);
			filterButton.setVisible(true);
			modifyButton.setVisible(true);
			drawButton.setVisible(true);
			wordButton.setVisible(true);
			mergeButton.setVisible(true);
			SaveButton.setVisible(true);
			cutButton.setText("Cut");
			//Point2D BPaneCoords1 = Img1.sceneToLocal(sceneCoords1);
 		    //Point2D BPaneCoords2 = Img1.sceneToLocal(sceneCoords2);
 		    System.out.println("image size:");
 		    System.out.println(image.getWidth()+"  "+image.getHeight()+"  "+realWidth+"  "+realHeight);
 		    //System.out.println(sceneCoords1.getX()+" "+sceneCoords1.getY()+" "+sceneCoords2.getX()+" "+sceneCoords2.getY());
 		    //System.out.println(BPaneCoords1.getX()+" "+BPaneCoords1.getY()+" "+BPaneCoords2.getX()+" "+BPaneCoords2.getY());
			
			
			//0619
			//System.out.println("0619 image width"+image.getWidth()+" image height"+image.getHeight());
			SnapshotParameters sp =  new SnapshotParameters();
			sp.setTransform(Transform.scale(ratio, ratio));
			BufferedImage bImage = SwingFXUtils.fromFXImage(Img1.snapshot(sp, null), null);
			image = SwingFXUtils.toFXImage(bImage, null);
			//System.out.println("0619 image width"+image.getWidth()+" image height"+image.getHeight());
			
 		    PixelReader reader = image.getPixelReader();
			
 		    //int hereWidth=(int)(Math.abs(BPaneCoords2.getX()-BPaneCoords1.getX()));
 		    //int hereHeight=(int)(Math.abs(BPaneCoords2.getY()-BPaneCoords1.getY()));
 		    //System.out.println("here    "+hereWidth+" "+hereHeight);
						
			
			
			//0619
			// TA
			
			double x = (Math.min(clickX1, clickX2)-450)*ratio+image.getWidth()/2;
			double y = (Math.min(clickY1, clickY2)-300)*ratio+image.getHeight()/2;
			
			//System.out.println("bx "+bCutX1+" by "+bCutY1+"bx "+bCutX2+" by "+bCutY2);
			//double x = (Math.min(bCutX1, bCutX2)-450)*ratio+image.getWidth()/2;
			//double y = (Math.min(bCutY1, bCutY2)-300)*ratio+image.getHeight()/2;
			
			
 		    int changeWidth=(int)(Math.abs(clickX2-clickX1)*ratio);
 		    int changeHeight=(int)(Math.abs(clickY2-clickY1)*ratio);
			
			//System.out.println("click y: "+clickY1+" "+clickY2);
 		   // System.out.println("changed "+x+" "+y+" "+changeWidth+" "+changeHeight);
 		            	//這裡xy座標都對的怪怪的
						
			WritableImage newImage = new WritableImage(reader, (int)x, (int)y, changeWidth, changeHeight);
			image=newImage;
			ratio = image.getWidth()/Img1.getFitWidth();
 		    double aspectRatio = newImage.getWidth() / newImage.getHeight();
 	  		realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
 	  		realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
 	  		    	   
 		    Img1.setImage(newImage);
 		    Img1.setFitWidth(realWidth);
 		    Img1.setFitHeight(realHeight);
 		     
 		    BPane.getChildren().remove(line1);
 		    BPane.getChildren().remove(line2);
 		    BPane.getChildren().remove(line3);
 		    BPane.getChildren().remove(line4);
 		       
			   
			BPane.setTop(null);
			line1=null;
			line2=null;
			line3=null;
			line4=null;
			clickX1=0;
			clickX2=0;
			clickY1=0;
			clickY2=0;
			clickStatus=0;
			
			status=0;
 		    cutstatus=0;
		}
    	
		
    }
    @FXML
    void resetPressed(ActionEvent event) {
		//load		
    	//image = new Image(originalImageFile);
		image=originalImage;
		//reset imageView
		Img1.setRotate(0); 
    	Img1.setFitWidth(700);
    	Img1.setFitHeight(450);
		
		//adjust Img1 ratio
    	double aspectRatio = image.getWidth() / image.getHeight();
    	realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
    	realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
    	Img1.setImage(image);
		Img1.setFitWidth(realWidth);
  	    Img1.setFitHeight(realHeight);
		ratio = image.getWidth()/Img1.getFitWidth();
		
    	//rotate
		if(realWidth>realHeight)
		{
			phototType=true;
		}
		else
		{
			phototType=false;
		}
		rotateFirst=true;
		
		
		
		//set cut parameter
		clickStatus=0;
		cutstatus=0;
		status=0;
		clickX1=0;
		clickY1=0;
		clickX2=0;
		clickY2=0;  // orignal
		
		
    }
    @FXML
    void ColorPressed(ActionEvent event)  throws Exception{
    	ColorRemove colorremove=new ColorRemove(realWidth,realHeight);
		SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(10, 10));
    	BufferedImage bImage = SwingFXUtils.fromFXImage(Img1.snapshot(sp, null), null);
    	colorremove.showWindow(bImage,this);
    }
    @FXML
    void savePressed(ActionEvent event) throws Exception{
    	FileChooser chooser=new FileChooser();
        chooser.setInitialDirectory(new File("C:\\"));   //設定初始路徑，預設為我的電腦
        chooser.setTitle("儲存圖片");                //設定視窗標題，預設為“開啟”
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                                             new FileChooser.ExtensionFilter("PNG", "*.png"));
        
        File outputFile = chooser.showSaveDialog(stage);
        BufferedImage bImage = SwingFXUtils.fromFXImage(Img1.snapshot(null, null), null);
		WritableImage writableImage;
        try {
			
             //ImageIO.write(bImage, "png", outputFile);
        	 //ImageIO.write(SwingFXUtils.fromFXImage(Img1.getImage(),null), "png", outputFile);
			 
        	realW=realWidth;
        	realH=realHeight;
			
        	writableImage=pixelScaleAwareCanvasSnapshot(Img1,2.0);
        	ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null),"png", outputFile);
        
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
		
		FinishEdit finish=new FinishEdit();
		if(editStatus==0)
			finish.showWindow(writableImage,editStatus);
		else if(editStatus==1)
			finish.showWindow(writableImage,editStatus,homepagec,outputFile);
		
		((Node)(event.getSource())).getScene().getWindow().hide();

        
 
    }
    //public void setNewImage(BufferedImage bimage)
	public void setNewImage(WritableImage image)
    {
		
		double aspectRatio = image.getWidth() / image.getHeight();
 	  	realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
 	  	realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
		this.image=image;
 	  	Img1.setImage(image);
 		Img1.setFitWidth(realWidth);
 		Img1.setFitHeight(realHeight);
		Img1.setImage(image);
    	
    }
	
    //截整個畫面
    public static WritableImage pixelScaleAwareCanvasSnapshot(ImageView imageView, double pixelScale) {
    	
        WritableImage writableImage = new WritableImage((int)Math.rint(pixelScale*realW), (int)Math.rint(pixelScale*realH));
        SnapshotParameters spa = new SnapshotParameters();
        //spa.setTransform(Transform.scale(10, 10));
        spa.setTransform(Transform.scale(pixelScale, pixelScale));
        return imageView.snapshot(spa, writableImage);     
    }
    
    
    @FXML
    void mergePressed(ActionEvent event)throws Exception {
    	Merge merge=new Merge(realWidth,realHeight);
		SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(5, 5));
    	BufferedImage bImage = SwingFXUtils.fromFXImage(Img1.snapshot(sp,null), null);
    	merge.showWindow(bImage,this);
    }
    @FXML
    void rotatePressed(ActionEvent event) {
		/*
    	double temp=realWidth;
    	realWidth=realHeight;
    	realHeight=temp;
		*/
		//System.out.println("width:"+Img1.getFitWidth()+"height:"+Img1.getFitHeight());
		if(phototType)
		{
			if(rotateFirst)
			{
				//System.out.println("a1");
				
				double aspectRatio = image.getHeight()/image.getWidth();
				realWidth = Math.min(Img1.getFitHeight(),Img1.getFitWidth() * aspectRatio);
				realHeight = Math.min(Img1.getFitWidth(),Img1.getFitHeight() / aspectRatio);
				Img1.setFitWidth(realWidth);
				Img1.setFitHeight(realHeight);
				rotateFirst=false;
				Img1.setRotate(Img1.getRotate() + 90); 
			}
			else
			{
				//System.out.println("a2");
			//	System.out.println(Img1.getFitWidth()+" "+Img1.getFitHeight());
				Img1.setRotate(Img1.getRotate() + 90); 
				Img1.setFitWidth(realHeight);
				Img1.setFitHeight(realWidth);
			//	System.out.println(Img1.getFitWidth()+" "+Img1.getFitHeight());
				double aspectRatio = image.getWidth() / image.getHeight();
				realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
				realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
				/*double aspectRatio = image.getHeight()/image.getWidth();
				realWidth = Math.min(Img1.getFitHeight(),Img1.getFitWidth() * aspectRatio);
				realHeight = Math.min(Img1.getFitWidth(),Img1.getFitHeight() / aspectRatio);*/
				Img1.setFitWidth(realWidth);
				Img1.setFitHeight(realHeight);
				rotateFirst=true;
			}
		}
		else
		{
			if(rotateFirst)
			{
				//System.out.println("b1");
				//Img1.setFitWidth(realHeight);
				//Img1.setFitHeight(realWidth);
				double aspectRatio = image.getHeight()/image.getWidth();	

				realWidth =Img1.getFitHeight();
				realHeight = realWidth/aspectRatio;
				
				
				Img1.setFitWidth(realWidth);
				Img1.setFitHeight(realHeight);
				rotateFirst=false;
				
			}
			else
			{
				//System.out.println("b2");
			
				Img1.setFitWidth(realHeight);
				Img1.setFitHeight(realWidth);
			//	System.out.println(Img1.getFitWidth()+" "+Img1.getFitHeight());
				double aspectRatio = image.getWidth() / image.getHeight();
				realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
				realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
				Img1.setFitWidth(realWidth);
				Img1.setFitHeight(realHeight);
				rotateFirst=true;
			}
			Img1.setRotate(Img1.getRotate() + 90); 
		}			
    }
    @FXML
    void filterPressed(ActionEvent event) throws Exception{
    	Filter filter=new Filter(realWidth,realHeight);
    	SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(10, 10));
    	BufferedImage bmImage = SwingFXUtils.fromFXImage(Img1.snapshot(sp, null), null);
    	filter.showWindow(bmImage,this);
    }
    @FXML
    void drawPressed(ActionEvent event) throws Exception{
    	//imageWidthBeforeDraw=image.getWidth();
    	//imageHeightBeforeDraw=image.getHeight();
    	//System.out.println(imageWidthBeforeDraw+" "+imageHeightBeforeDraw);
    	Drw draw=new Drw(realWidth,realHeight);
    	SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(5, 5));
    	BufferedImage bmImage = SwingFXUtils.fromFXImage(Img1.snapshot(sp, null), null);
    	draw.showWindow(bmImage,this);
    }

    @FXML
    void wordPressed(ActionEvent event)throws Exception {
    	Word word=new Word(realWidth,realHeight);
    	SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(5, 5));
    	BufferedImage bmImage = SwingFXUtils.fromFXImage(Img1.snapshot(sp, null), null);
    	word.showWindow(bmImage,this);
    }
    @FXML
    void modifyPressed(ActionEvent event) throws Exception {
    	Modify modify=new Modify(realWidth,realHeight);
    	SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(10, 10));
    	BufferedImage bmImage = SwingFXUtils.fromFXImage(Img1.snapshot(sp, null), null);
    	modify.showWindow(bmImage,this);
    }
	@FXML
    void returnClicked(ActionEvent event) throws Exception {
		if(editStatus==0)
		{
			First first=new First();
			first.showWindow();
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		else{
			homepagec.getHomepage().showWindow();
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		

    }
	@FXML
    void originalPressed(MouseEvent event) {
		tempImage=image;
		tempWidth=realWidth;
		tempHeight=realHeight;
		tempRotate=Img1.getRotate();
    	Img1.setImage(originalImage);
		Img1.setFitWidth(oriWidth);
  	    Img1.setFitHeight(oriHeight);
		Img1.setRotate(0);
    }
	@FXML
    void originalReleased(MouseEvent event) {
		Img1.setImage(tempImage);
		Img1.setFitWidth(tempWidth);
  	    Img1.setFitHeight(tempHeight);
		Img1.setRotate(0);
    }


}
