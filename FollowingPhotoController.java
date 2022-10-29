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
import javafx.scene.input.MouseEvent;

public class FollowingPhotoController {

	JdbcMySQL sqlcon1 = new JdbcMySQL();
	MyPhoto sqlcon2 = new MyPhoto();//need to chang to MyPhoto()
	LogIn sqlcon3 = new LogIn();

	private Stage stage;
	
	@FXML
    private ImageView profilephoto;
	
    @FXML
    private ImageView imageView;
	
	@FXML
    private Label userLabel;
	
    @FXML
    private Label photoLabel;
	
	@FXML
    private StackPane stackPane;
	
	@FXML
    private Label dateLabel;

	String account,following;
	
	HomepageController homepagec;
	ViewImageController viewc;
	MyController myc;
	FollowingController fc;
	FollowingPhoto f;
	int num;
	Image[]	images;
	Image image;
	double realWidth,realHeight;
	String[] photosearch={"","","","","","","","","","","","","","","",""};
	String[] accountsearch={"","","","","","","","","","","","","","","",""};
	String[] date={"","","","","","","","","","","","","","","",""};
	private static double realW,realH;
	String[] followings;
	int pagePhotoStatus;
	int photoIndex;
	
	
	FollowingController getFollowingController()
	{
		return fc;
	}
	@FXML
    void returnClicked(ActionEvent event) throws Exception{
		
		fc.getFollow().showWindow(fc.getHomepagec());
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
		
		sqlcon2.insertArchiveTable(photosearch[num],inputStream,accountsearch[num],date[num]);
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
		/*double aspectRatio = image.getWidth() / image.getHeight();
 	  	realWidth = Math.min(imageView.getFitWidth(), imageView.getFitHeight() * aspectRatio);
 	  	realHeight = Math.min(imageView.getFitHeight(), imageView.getFitWidth() / aspectRatio);*/
    	
        WritableImage writableImage = new WritableImage((int)Math.rint(pixelScale*realW), (int)Math.rint(pixelScale*realH));
        SnapshotParameters spa = new SnapshotParameters();
        //spa.setTransform(Transform.scale(10, 10));
        spa.setTransform(Transform.scale(pixelScale, pixelScale));
        return imageView.snapshot(spa, writableImage);     
    }
    
	public void setStage(Stage stage, HomepageController home,int num,FollowingController fc,int pagePhotoStatus,int photoIndex,FollowingPhoto f)throws Exception
	{
		this.homepagec=home;
		this.stage=stage;
		this.num=num;
		//this.viewc=viewc;
		//this.myc=myc;
		this.fc=fc;
		this.pagePhotoStatus=pagePhotoStatus;
		this.photoIndex=photoIndex;
		this.f=f;
		account=homepagec.getAccount();
		System.out.println("acc:"+account);
		followings=fc.getFollowingArray();
		
		images=sqlcon1.SelectFollowingTable(account,followings,pagePhotoStatus,photoIndex);
		photosearch=sqlcon1.SelectFollowingSearch(account,followings,pagePhotoStatus,photoIndex);
		accountsearch=sqlcon1.SelectFollowingAccount(account,followings,pagePhotoStatus,photoIndex);
		following=accountsearch[num];
		date=sqlcon1.SelectDateTable(account,followings,pagePhotoStatus,photoIndex);
		image=images[num];
		
		if(sqlcon3.selectProfile(accountsearch[num])!=null)
			profilephoto.setImage(sqlcon3.selectProfile(accountsearch[num]));
		
		double aspectRatio = image.getWidth() / image.getHeight();
	//	System.out.println("fpc:"+imageView.getFitWidth()+" "+imageView.getFitHeight());
  		realWidth = Math.min(imageView.getFitWidth(), imageView.getFitHeight() * aspectRatio);
  		realHeight = Math.min(imageView.getFitHeight(), imageView.getFitWidth() / aspectRatio);
		imageView.setImage(images[num]);
	//	System.out.println(image.getWidth()+" "+image.getHeight());
	//	System.out.println(realWidth+" "+realHeight);
		
		//stackPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(realWidth,realHeight,false,false,true,true))));
		
		
  		imageView.setFitWidth(realWidth);
  		imageView.setFitHeight(realHeight);
		imageView.setPreserveRatio(true);
		
		System.out.println(photosearch[num]+" "+num);
		photoLabel.setText(photosearch[num]);
		userLabel.setText(accountsearch[num]);
		dateLabel.setText(date[num]);
		
	}
	FollowingPhoto getFollowingPhoto()
	{
		return f;
	}
	public void initialize() throws Exception
    {
		//BPane.setCenter(imageView);
	}

}
