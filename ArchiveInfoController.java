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
import javafx.scene.input.MouseEvent;


public class ArchiveInfoController {

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
	ViewImageController viewc;
	MyController myc;
	ArchiveInfo a;
	int topicStatus;
	
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
	ArchiveController ac;
	
	String account;
	int pagePhotoStatus;
	 int photoIndex;
	/*
	@FXML
    void followPressed(ActionEvent event) throws Exception{
		
		//already followed
		if(sqlcon2.alreadyfollowing(myAccount,following))
		{
			System.out.println("in1");
			sqlcon2.cancelfollowing(myAccount,following);
			followButton.setText("follow");
		}
		
		//haven't follow
		else
		{
			System.out.println(myAccount+" "+following);
			if(myAccount.compareTo(following)!=0)
			{
				System.out.println("in2");
				sqlcon2.insertFollowing(myAccount,following);
				followButton.setText("following");
			}
		}
		
		
    }*/
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
    void userLabelClicked(MouseEvent event)throws Exception {
		if(account.compareTo(following)!=0)
		{
			TargetHome targetHome=new TargetHome();
			targetHome.showWindow(homepagec,this,account,following,num,pagePhotoStatus,photoIndex);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
    }
	@FXML
    void returnClicked(ActionEvent event) throws Exception{
		ac.getArchive().showWindow(homepagec);
		((Node)(event.getSource())).getScene().getWindow().hide();

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
	ArchiveInfo getArchiveInfo()
	{
		return a;
	}
	ArchiveController getArchiveController()
	{
		return ac;
	}
	
	public static WritableImage pixelScaleAwareCanvasSnapshot(ImageView imageView, double pixelScale) {
		/*double aspectRatio = image.getWidth() / image.getHeight();
 	  	realWidth = Math.min(imageView.getFitWidth(), imageView.getFitHeight() * aspectRatio);
 	  	realHeight = Math.min(imageView.getFitHeight(), imageView.getFitWidth() / aspectRatio);*/
    	
        WritableImage writableImage = new WritableImage((int)Math.rint(pixelScale*realW), (int)Math.rint(pixelScale*realH));
        SnapshotParameters spa = new SnapshotParameters();
        //spa.setTransform(Transform.scale(10, 10));
        spa.setTransform(Transform.scale(pixelScale, pixelScale));
        return imageView.snapshot(spa, writableImage);     
    }
    
	public void setStage(Stage stage, HomepageController home,int num,ViewImageController viewc,ArchiveController myc,int pagePhotoStatus,int photoIndex,ArchiveInfo a)throws Exception
	{
		this.homepagec=home;
		this.stage=stage;
		this.num=num;
		this.viewc=viewc;
		this.pagePhotoStatus=pagePhotoStatus;
		this.photoIndex=photoIndex;
		this.a=a;
		File defaultphotofile = new File("defaultphoto.jpg");
        Image imageFromFolder = new Image(defaultphotofile.toURI().toString());
		ac=myc;
		account=homepagec.getAccount();
		
		//getImage
		images=sqlcon2.SelectArchiveTable(account,pagePhotoStatus,photoIndex);//
		photosearch=sqlcon2.SelectArchiveSearchTable(account,pagePhotoStatus,photoIndex);
		accountsearch=sqlcon2.SelectArchiveAccountTable(account,pagePhotoStatus,photoIndex);
		following=accountsearch[num];
		date=sqlcon2.SelectArchiveDateTable(account,pagePhotoStatus,photoIndex);
		image=images[num];
		
		
		//show profile
		if(sqlcon3.selectProfile(accountsearch[num])!=null)
			profilephoto.setImage(sqlcon3.selectProfile(accountsearch[num]));
		else		
			profilephoto.setImage(imageFromFolder);
		
		
		double aspectRatio = image.getWidth() / image.getHeight();
  		realWidth = Math.min(imageView.getFitWidth(), imageView.getFitHeight() * aspectRatio);
  		realHeight = Math.min(imageView.getFitHeight(), imageView.getFitWidth() / aspectRatio);
		imageView.setImage(images[num]);
		//System.out.println(image.getWidth()+" "+image.getHeight());
		//System.out.println(realWidth+" "+realHeight);
		
		
  		imageView.setFitWidth(realWidth);
  		imageView.setFitHeight(realHeight);
		imageView.setPreserveRatio(true);
		
		//System.out.println("date"+date[num]);
		photoLabel.setText(photosearch[num]);
		userLabel.setText(accountsearch[num]);
		dateLabel.setText(date[num]);
			
		
		
	}
	public void initialize() throws Exception
    {
	}

}
