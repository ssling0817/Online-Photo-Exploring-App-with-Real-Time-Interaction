import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ComboBox;
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
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.shape.Line;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ColorPicker;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.security.SecureRandom;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Transform;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.Parent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.Node;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;


//畫筆粗細 存的圖
public class DrwController {
	
	double initX;
    double initY;
	int outputX,outputY;
    int status=0;
	Image image,addPicture;
	Line line;
	int penStatus=0;
	int totalcount=0;
	PhotoController photoC;
	SecureRandom random=new SecureRandom();
	Stage stage;
	static Scene scene;
	double realWidth,realHeight;
	Parent root;
	private static final double SCALE_FACTOR =1.0/5 ;
	@FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView Img1;
    
    @FXML
    private ColorPicker colorPicker;
    
    @FXML
    private ComboBox<String> penComboCox;
	
	@FXML
    private Button undoButton;

    @FXML
    private Button clearButton;
	
	@FXML
    private Button saveButton;

    
    ImageView scaledImageView=new ImageView();
    Label label;
	boolean first=true;
	boolean addingOrNot=true;

   // ObservableList <String> penComboCoxList=FXCollections.observableArrayList("ball-point pen","crayon");
    
	double maxX,minX ;
    double maxY,minY;
    
	public void initialize()
	{
		colorPicker.setValue(Color.WHITE);
		scaledImageView.setVisible(false);
		//Img1.add(scaledImageView);
		penComboCox.getItems().addAll("ball-point pen","spray","Mike pen","Line","solid");
		penComboCox.getSelectionModel().select("ball-point pen");
		
		// Create action event 
        EventHandler<ActionEvent> event = 
                  new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	if(penComboCox.getValue()=="ball-point pen")
                {
            		penStatus=0;
                }
            	else if(penComboCox.getValue()=="spray")
                {
            		penStatus=1;
                }
            	else if(penComboCox.getValue()=="Line")
                {
            		penStatus=2;
                }
            	else if(penComboCox.getValue()=="Mike pen")
                {
            		penStatus=3;
                }
            	else if(penComboCox.getValue()=="solid")
                {
            		penStatus=4;
                }

            } 
        }; 
  
        // Set on action 
        penComboCox.setOnAction(event); 
	}
	@FXML
    void mouseDragged(MouseEvent event) {
	if(status==0)
	{
		if(penStatus==0)
		{
			//System.out.println(event.getSceneX()+" "+event.getSceneY());
			if (event.getSceneX() < maxX && event.getSceneY() < maxY) {
				line = new Line(initX, initY, event.getSceneX(), event.getSceneY());
				line.setFill(null);
				line.setStroke(colorPicker.getValue());
				line.setStrokeWidth(2);
				anchorPane.getChildren().add(line);
			}

			initX = event.getSceneX() > maxX ? maxX : event.getSceneX();
			initY = event.getSceneY() > maxY ? maxY : event.getSceneY();
		}
		else if(penStatus==1)
		{
			if (event.getSceneX() < maxX && event.getSceneY() < maxY) {
				for(int i=0;i<360;i++)
				{
					int a=random.nextInt(100);
					if((a<50)&&(a>=0))
					{
						double radius=0.5;
						Circle circle=new Circle(radius,colorPicker.getValue());
						circle.relocate(event.getSceneX()+1*Math.cos(i),event.getSceneY()+1*Math.sin(i));
						anchorPane.getChildren().addAll(circle);
					}
					else if((a<75)&&(a>=50))
					{
						double radius=0.5;
						Circle circle=new Circle(radius,colorPicker.getValue());
						circle.relocate(event.getSceneX()+2*Math.cos(i),event.getSceneY()+2*Math.sin(i));
						anchorPane.getChildren().addAll(circle);
					}
					else if((a<90)&&(a>=75))
					{
						double radius=0.5;
						Circle circle=new Circle(radius,colorPicker.getValue());
						circle.relocate(event.getSceneX()+3*Math.cos(i),event.getSceneY()+3*Math.sin(i));
						anchorPane.getChildren().addAll(circle);
					}
					else if((a<97)&&(a>=90))
					{
						double radius=0.5;
						Circle circle=new Circle(radius,colorPicker.getValue());
						circle.relocate(event.getSceneX()+4*Math.cos(i),event.getSceneY()+4*Math.sin(i));
						anchorPane.getChildren().addAll(circle);
					}
					else if((a<100)&&(a>=97))
					{
						double radius=0.5;
						Circle circle=new Circle(radius,colorPicker.getValue());
						circle.relocate(event.getSceneX()+5*Math.cos(i),event.getSceneY()+5*Math.sin(i));
						anchorPane.getChildren().addAll(circle);
					}
				}
			}
			initX = event.getSceneX() > maxX ? maxX : event.getSceneX();
			initY = event.getSceneY() > maxY ? maxY : event.getSceneY();
		}
		else if(penStatus==2)
		{
			if (event.getSceneX() < maxX && event.getSceneY() < maxY) {
				Rectangle r1 = new Rectangle(event.getSceneX(),event.getSceneY(),50,1);
				if(colorPicker.getValue()==Color.WHITE)
				{
					//r1.setStroke(Color.BLACK);
					r1.setFill(Color.rgb(255, 255, 255, 0.5));
					//System.out.println(colorPicker.getValue().getRed());
					r1.setStrokeWidth(3);
				}
				else
				{
				//r1.setStroke(Color.BLACK);
				//System.out.println(colorPicker.getValue().getRed());
				r1.setFill(Color.rgb((int)(colorPicker.getValue().getRed()*255), (int)(colorPicker.getValue().getGreen()*255),(int)(colorPicker.getValue().getBlue()*255), 0.7));

				//r1.setFill(Color.rgb(Integer.parseInt(toHexString(colorPicker.getValue()).substring(4,5),16), Integer.parseInt(toHexString(colorPicker.getValue()).substring(2,3),16), Integer.parseInt(toHexString(colorPicker.getValue()).substring(0,1),16), 0.5));
				
				r1.setStrokeWidth(3);
				}
				anchorPane.getChildren().addAll(r1);
			}
			initX = event.getSceneX() > maxX ? maxX : event.getSceneX();
			initY = event.getSceneY() > maxY ? maxY : event.getSceneY();
		}
		else if(penStatus==3)
		{
			if (event.getSceneX() < maxX && event.getSceneY() < maxY) {
				Rectangle r1 = new Rectangle(event.getSceneX(),event.getSceneY(),20,5);
				if(colorPicker.getValue()==Color.WHITE)
				{
					//r1.setStroke(Color.BLACK);
					r1.setFill(Color.rgb(255, 255, 255, 0.5));
					//System.out.println(colorPicker.getValue().hashCode());
					r1.setStrokeWidth(3);
				}
				else
				{
				//r1.setStroke(Color.BLACK);
					
					r1.setFill(Color.rgb((int)(colorPicker.getValue().getRed()*255), (int)(colorPicker.getValue().getGreen()*255),(int)(colorPicker.getValue().getBlue()*255), 0.3));
					r1.setStrokeWidth(3);
				}
				anchorPane.getChildren().addAll(r1);
			}
			initX = event.getSceneX() > maxX ? maxX : event.getSceneX();
			initY = event.getSceneY() > maxY ? maxY : event.getSceneY();
		}else if(penStatus==4)
		{
			if (event.getSceneX() < maxX && event.getSceneY() < maxY) {
				Rectangle r1 = new Rectangle(event.getSceneX(),event.getSceneY(),20,5);
				if(colorPicker.getValue()==Color.WHITE)
				{
					r1.setStroke(Color.WHITE);
					r1.setFill(Color.WHITE);
					//System.out.println(colorPicker.getValue().hashCode());
					r1.setStrokeWidth(3);
				}
				else
				{
				    r1.setStroke(Color.rgb((int)(colorPicker.getValue().getRed()*255), (int)(colorPicker.getValue().getGreen()*255),(int)(colorPicker.getValue().getBlue()*255)));
				    r1.setFill(Color.WHITE);
					//r1.setFill(Color.rgb((int)(colorPicker.getValue().getRed()*255), (int)(colorPicker.getValue().getGreen()*255),(int)(colorPicker.getValue().getBlue()*255), 0.3));

				
				
				r1.setStrokeWidth(3);
				}
				anchorPane.getChildren().addAll(r1);
			}
			initX = event.getSceneX() > maxX ? maxX : event.getSceneX();
			initY = event.getSceneY() > maxY ? maxY : event.getSceneY();
		}
	}
    }
	private String format(double val) {
	    String in = Integer.toHexString((int) Math.round(val * 255));
	    return in.length() == 1 ? "0" + in : in;
	}
	//delete # version
	public String toHexString(Color value) {
	    return  (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
	            .toUpperCase();
	}
    @FXML
    void mousePressed(MouseEvent event) {
    	
    	initX = event.getSceneX();
        initY = event.getSceneY();
        event.consume();
        
    }
    @FXML
    void colorPickerPressed(ActionEvent event) {
    }
	public void setStage(Stage stage,Parent root)
    {
    	this.stage=stage;
    	this.root=root;
		
    }
    public void setImage(BufferedImage bImage,double realW,double realH)
	 {
		 image = SwingFXUtils.toFXImage(bImage, null);
		 //System.out.println("set"+(int)image.getWidth()+" "+(int)image.getHeight());
		 Img1.setImage(image);
		 realWidth=realW;
		 realHeight=realH;
		 
		 Img1.setFitWidth(realWidth);
  		 Img1.setFitHeight(realHeight);
		 //System.out.println("real:"+realWidth+" "+realHeight);
		 /*
		 maxX = Img1.getImage().getWidth()+350;
		 maxY = Img1.getImage().getHeight()+230;*/
		 double aspectRatio = image.getWidth() / image.getHeight();
		 realWidth = Math.min(Img1.getFitWidth(), Img1.getFitHeight() * aspectRatio);
  		 realHeight = Math.min(Img1.getFitHeight(), Img1.getFitWidth() / aspectRatio);
		 System.out.println("reall"+realWidth+" "+realHeight);
		 
		 minX=Img1.getLayoutX();
		 minY=Img1.getLayoutY();
		 maxX=realWidth+minX;
		 maxY=realHeight+minY;
		 System.out.println("layout "+Img1.getLayoutX()+" "+Img1.getLayoutY());
		 final int outputX=(int)(Img1.getLayoutX()*5);
		 final int outputY=(int)(Img1.getLayoutY()*5);
		 this.outputX=outputX;
		 this.outputY=outputY;
		 System.out.println(outputX+" "+outputY);
	 }
    @FXML
    void clearPressed(ActionEvent event) {
    	int count = anchorPane.getChildren().size();
    	while(count>8)
    	{
    		 anchorPane.getChildren().remove(count - 1);
    		 count = anchorPane.getChildren().size();
    	}
    }
    @FXML
    void undoPressed(ActionEvent event) {
    	
    	if(penStatus==0)
    	{
    		int count = anchorPane.getChildren().size();
    		if (count > 8) {
    			anchorPane.getChildren().remove(count - 1);
    		}
    	}
    	else if(penStatus==1)
    	{
    		for(int i=0;i<360;i++)
        	{
        		int count = anchorPane.getChildren().size();
        		if (count > 8) {
        			anchorPane.getChildren().remove(count - 1);
        		}
        	}
    	}
    	
    }
    public void setPhoto(PhotoController photoC)
    {
    	this.photoC=photoC;
    }
    @FXML
    void savePressed(ActionEvent event) {
    	System.out.println(outputX+" "+outputY);
    	SnapshotParameters sp =  new SnapshotParameters();
    	sp.setTransform(Transform.scale(5,5));
		Image shot=root.snapshot(sp, null);
		PixelReader reader = shot.getPixelReader();
		WritableImage newImage = new WritableImage(reader,outputX,outputY,(int)image.getWidth(),(int)image.getHeight());
    	photoC.setNewImage(newImage);
		stage.hide();
    	
    }
	/*
    @FXML
    void ConfirmPressed(ActionEvent event) {
    	status=0;
    }*/
    @FXML
    void AddPicPressed(ActionEvent event) {
		if(addingOrNot)
		{
			status=1;
			label=new Label();
			try {
				 FileChooser chooser=new FileChooser();
				 chooser.setInitialDirectory(new File("C:\\"));   //設定初始路徑，預設為我的電腦
			   
				 chooser.setTitle("Add Image");                //設定視窗標題，預設為“開啟”
				 chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
														 new FileChooser.ExtensionFilter("PNG", "*.png"));
			   
				 File selectedFile= chooser.showOpenDialog(stage);
				 
					if (selectedFile != null) {

						String imageFile = selectedFile.toURI().toURL().toString();
						
						addPicture = new Image(imageFile);
						
					} else {
						System.out.println("Image file selection cancelled.");
					}
				}
			catch (Exception e) {
					 System.out.println("Error: " + e);
			}
			scaledImageView.setVisible(true);
			 scaledImageView.setImage(addPicture);
			scaledImageView.setScaleX(SCALE_FACTOR);
			scaledImageView.setScaleY(SCALE_FACTOR);
			
			label.setGraphic(scaledImageView);
			realWidth*=SCALE_FACTOR;
			realHeight*=SCALE_FACTOR;
			if(first)
			{
				anchorPane.getChildren().add(scaledImageView);
				first=false;
			}
			MouseGestures mg = new MouseGestures();
			mg.makeDraggable( label);
			/*
			scaledImageView.setOnMouseDragOver((MouseDragEvent e) -> {
				System.out.println("mouseDragOver");
			});
			 */
			 //anchorPane.setTopAnchor(label,(double)outputX);  
			 System.out.println(outputX+" "+outputY);
			anchorPane.getChildren().add(label);
			//0625
			addingOrNot=false;
			saveButton.setVisible(false);
			undoButton.setVisible(false);
			clearButton.setVisible(false);
			colorPicker.setVisible(false);
			penComboCox.setVisible(false);
		}
		else
		{
			addingOrNot=true;
			saveButton.setVisible(true);
			undoButton.setVisible(true);
			clearButton.setVisible(true);
			colorPicker.setVisible(true);
			penComboCox.setVisible(true);
			status=0;
		}
	}
    private class MouseGestures {

        class DragContext {
            double x;
            double y;
        }

        DragContext dragContext = new DragContext();

        public void makeDraggable( Node node) {
            node.setOnMousePressed( onMousePressedEventHandler);
            node.setOnMouseDragged( onMouseDraggedEventHandler);
        }

        EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
        	if(status==1)
        	{
                Node node = ((Node) (event.getSource()));

                dragContext.x = node.getTranslateX() - event.getSceneX();
                dragContext.y = node.getTranslateY() - event.getSceneY();
        	}
        };

        EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
        	if(status==1)	
        	{
                Node node = ((Node) (event.getSource()));

                node.setTranslateX( dragContext.x + event.getSceneX());
                node.setTranslateY( dragContext.y + event.getSceneY()); // uncomment this if you want x/y dragging
        	}
        };
    }
}

