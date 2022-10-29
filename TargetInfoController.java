import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import javafx.scene.SnapshotParameters;
import javafx.scene.transform.Transform;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
public class TargetInfoController {

	JdbcMySQL sqlcon = new JdbcMySQL();
	MyPhoto sqlcon2 = new MyPhoto();
	LogIn sqlcon3 = new LogIn();

	private Stage stage;
	
	@FXML
    private ImageView profilephoto;
	
    @FXML
    private ImageView imageView;

    @FXML
    private Label photoLabel;
	
	@FXML
    private StackPane stackPane;
	
	@FXML
    private Label userLabel;
	
	@FXML
    private Label dateLabel;
	
	@FXML
    private Button followButton;

	//int stackPaneWidth=300;
	//int stackPaneHeight=200;

	
	HomepageController homepagec;
	//ViewImageController viewc;
	//MyController myc;
	//int topicStatus;
	TargetInfo ti;
	TargetHomeController thc;
	
	int num;
	Image[]	images;
	Image image;
	//double realWidth,realHeight;
	String[] photosearch={""};
	String[] accountsearch={""};
	String[] date={""};
	String following,myAccount;
	double realWidth,realHeight;
	private static double realW,realH;
	int pagePhotoStatus;
	int photoIndex;
	PhotoInfo pc;
	
	
	@FXML
    void followPressed(ActionEvent event) throws Exception{
		
		//already followed
		if(sqlcon2.alreadyfollowing(myAccount,following))
		{
			//System.out.println("in1");
			sqlcon2.cancelfollowing(myAccount,following);
			followButton.setText("follow");
			sqlcon2.cancelfollower(following,myAccount);
		}
		
		//haven't follow
		else
		{
			//System.out.println(myAccount+" "+following);
			if(myAccount.compareTo(following)!=0)
			{
				//System.out.println("in2");
				sqlcon2.insertFollowing(myAccount,following);
				sqlcon2.insertFollower(following,myAccount);
				followButton.setText("following");
			}
		}
		
		
    }
	
	@FXML
    void editPressed(ActionEvent event) throws Exception{
		BufferedImage bImage = SwingFXUtils.fromFXImage(imageView.snapshot(null, null), null);
		WritableImage writableImage;
        
        realW=realWidth;
        realH=realHeight;
        writableImage=pixelScaleAwareCanvasSnapshot(imageView,10.0);
        
		Photo edit=new Photo();
		edit.showWindow(writableImage,homepagec);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	@FXML
    void archivePressed(ActionEvent event)throws Exception {
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		InputStream inputStream ;
	
		ImageIO.write(bImage, "png", outputStream);
		byte[] res  = outputStream.toByteArray();
		inputStream = new ByteArrayInputStream(res);
		
		sqlcon2.insertArchiveTable(photosearch[num],inputStream,accountsearch[num],date[num],myAccount);
    }
	@FXML
    void returnClicked(ActionEvent event) throws Exception{
		/*if(viewc!=null)
		{
			viewc.getView().showWindow(viewc.getHomepagec());
		}
		else if(myc!=null)
		{
			myc.getMy().showWindow(myc.getHomepagec());
		}*/
		thc.getTargetHome().showWindow(thc.getHomepagec(),thc.getPhotoInfoController());
		((Node)(event.getSource())).getScene().getWindow().hide();

    }
	PhotoInfo getPhotoInfo()
	{
		return pc;
	}
	@FXML
    void SaveClicked(ActionEvent event) {
		FileChooser chooser=new FileChooser();
        chooser.setInitialDirectory(new File("C:\\"));   
        chooser.setTitle("save"); 
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                                             new FileChooser.ExtensionFilter("PNG", "*.png"));
        
        File outputFile = chooser.showSaveDialog(stage);
        BufferedImage bImage = SwingFXUtils.fromFXImage(imageView.snapshot(null, null), null);
		WritableImage writableImage;
        try {
        	realW=realWidth;
        	realH=realHeight;
        	writableImage=pixelScaleAwareCanvasSnapshot(imageView,10.0);
        	ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null),"png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	public static WritableImage pixelScaleAwareCanvasSnapshot(ImageView imageView, double pixelScale) {
        WritableImage writableImage = new WritableImage((int)Math.rint(pixelScale*realW), (int)Math.rint(pixelScale*realH));
        SnapshotParameters spa = new SnapshotParameters();
        spa.setTransform(Transform.scale(pixelScale, pixelScale));
        return imageView.snapshot(spa, writableImage);     
    }
    
	public void setStage(Stage stage, HomepageController home,int num,TargetHomeController thc,int pagePhotoStatus,int photoIndex,TargetInfo ti)throws Exception
	{
		this.homepagec=home;
		this.stage=stage;
		this.num=num;
		this.thc=thc;
		this.pagePhotoStatus=pagePhotoStatus;
		this.photoIndex=photoIndex;
		this.ti=ti;
		myAccount=thc.getAccount();
		following=thc.getFollowingAccount();
		userLabel.setText(following);
		profilephoto.setImage(sqlcon3.selectProfile(following));
		
		images=sqlcon2.SelectTable(pagePhotoStatus,photoIndex,following);
		image=images[num];
		double aspectRatio = image.getWidth() / image.getHeight();
  		realWidth = Math.min(imageView.getFitWidth(), imageView.getFitHeight() * aspectRatio);
  		realHeight = Math.min(imageView.getFitHeight(), imageView.getFitWidth() / aspectRatio);
		imageView.setImage(images[num]);
		System.out.println(image.getWidth()+" "+image.getHeight());
		System.out.println(realWidth+" "+realHeight);
		
		//stackPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(realWidth,realHeight,false,false,true,true))));
		
		
  		imageView.setFitWidth(realWidth);
  		imageView.setFitHeight(realHeight);
		imageView.setPreserveRatio(true);
		photosearch=sqlcon2.SelectSearchTable(following,pagePhotoStatus,photoIndex); //2
		accountsearch=sqlcon2.SelectAccountTable(following,pagePhotoStatus,photoIndex); //3
		date=sqlcon2.SelectDateTable(following,pagePhotoStatus,photoIndex); //4
		
		if(sqlcon3.selectProfile(accountsearch[num])!=null)
			profilephoto.setImage(sqlcon3.selectProfile(accountsearch[num]));
		
		//System.out.println(accountsearch[num]+" "+num);
		photoLabel.setText(photosearch[num]);
		userLabel.setText(accountsearch[num]);
		dateLabel.setText(date[num]);
		following=accountsearch[num];
		myAccount=thc.getHomepagec().getAccount();
		
		if(myAccount.compareTo(following)==0)
		{
			followButton.setVisible(false);
		}
		if(sqlcon2.alreadyfollowing(myAccount,following))
		{
			followButton.setText("following");
		}
		
		
	}
	public void initialize() throws Exception
    {
		//BPane.setCenter(imageView);
		
	}

}
