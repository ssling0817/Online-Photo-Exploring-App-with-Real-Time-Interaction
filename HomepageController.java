import java.util.Scanner;
import javax.swing.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Tab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import java.io.*;
import javafx.css.PseudoClass;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;


public class HomepageController {
	private Stage stage;
	Homepage homepage;
	
	//JdbcMySQL sqlcon = new JdbcMySQL();////////connection
	
	LogIn sqlcon1 = new LogIn();//////account and password
	
	MyPhoto sqlcon2 = new MyPhoto();//////My Photo
	
	Image profile;
	//private Image image;
	Image[]	image;
	
	File file;
	FileInputStream fileimage;
	
    @FXML
    private ImageView profilephoto;

    @FXML
    private Label accountname;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private ImageView imageView4;

    @FXML
    private ImageView imageView5;

    @FXML
    private ImageView imageView6;

    @FXML
    private ImageView imageView7;

    @FXML
    private ImageView imageView8;

    @FXML
    private ImageView imageView9;

    @FXML
    private ImageView imageView10;

    @FXML
    private ImageView imageView11;

    @FXML
    private ImageView imageView12;

    @FXML
    private ImageView imageView13;

    @FXML
    private ImageView imageView14;

    @FXML
    private ImageView imageView15;

    @FXML
    private ImageView imageView16;
	
	@FXML
    private TextField searchTextField;
	
	@FXML
    private Label followingLabel;

    @FXML
    private Label followersLabel;

	
	String account;
	
	@FXML
    void archivePressed(ActionEvent event) throws Exception  {
		Archive archive=new Archive();
		archive.showWindow(this);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void followingPressed(ActionEvent event) throws Exception {
		Following following=new Following();
		following.showWindow(this);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	@FXML
    void logOutPressed(ActionEvent event)  throws Exception{
		First first=new First();
		first.showWindow();
		((Node)(event.getSource())).getScene().getWindow().hide();
		
    }

	public Homepage getHomepage()
	{
		return homepage;
	}
	public void setAccount(String a,Homepage homepage) throws Exception
	{
		account=a;
		accountname.setText(account);
		this.homepage=homepage;
		profile=sqlcon1.selectProfile(account);
		if(profile!=null)
		{
			profilephoto.setImage(profile);
		}
		followersLabel.setText(Integer.toString(sqlcon2.getFollowerCount(account)));
		followingLabel.setText(Integer.toString(sqlcon2.getFollowingCount(account)));
		//System.out.println("in setAccount");
		//Image im=new Image("00.png");
		//imageView1.setImage(im);
		
		//
		/*
		int num;
		int picNum=sqlcon2.getCount(account);
		if(picNum<16)
		{
			Image[]	image = new Image[picNum];
			image=sqlcon2.SelectTable(account);/////////take
			for(int i=0;i<picNum;i++)
			{
				num=i+1;
				switch(num)
				{
					case 1:
						imageView1.setImage(image[i]);
						imageView1.setPreserveRatio(true);
					break;
					case 2:
						imageView2.setImage(image[i]);
						imageView2.setPreserveRatio(true);
					break;
					case 3:
						imageView3.setImage(image[i]);
						imageView3.setPreserveRatio(true);
						break;
					case 4:
						imageView4.setImage(image[i]);
						imageView4.setPreserveRatio(true);
						break;
					case 5:
						imageView5.setImage(image[i]);
						imageView5.setPreserveRatio(true);
						break;
					case 6:
						imageView6.setImage(image[i]);
						imageView6.setPreserveRatio(true);
						break;
					case 7:
						imageView7.setImage(image[i]);
						imageView7.setPreserveRatio(true);
						break;
					case 8:
						imageView8.setImage(image[i]);
						imageView8.setPreserveRatio(true);
						break;
					case 9:
						imageView9.setImage(image[i]);
						imageView9.setPreserveRatio(true);
						break;
					case 10:
						imageView10.setImage(image[i]);
						imageView10.setPreserveRatio(true);
						break;
					case 11:
						imageView11.setImage(image[i]);
						imageView11.setPreserveRatio(true);
						break;
					case 12:
						imageView12.setImage(image[i]);
						imageView12.setPreserveRatio(true);
						break;
					case 13:
						imageView13.setImage(image[i]);
						imageView13.setPreserveRatio(true);
						break;
					case 14:
						imageView14.setImage(image[i]);
						imageView14.setPreserveRatio(true);
						break;
					case 15:
						imageView15.setImage(image[i]);
						imageView15.setPreserveRatio(true);
						break;
					case 16:
						imageView16.setImage(image[i]);
						imageView16.setPreserveRatio(true);
						break;
						
			}
			}
		}
		Image[]	image = new Image[16];
		image=sqlcon2.SelectTable(account);/////////take
		for(int i=0;i<16;i++)
		{
			num=i+1;
			switch(num)
			{
				case 1:
					imageView1.setImage(image[i]);
					imageView1.setPreserveRatio(true);
					break;
				case 2:
					imageView2.setImage(image[i]);
					imageView2.setPreserveRatio(true);
					break;
				case 3:
					imageView3.setImage(image[i]);
					imageView3.setPreserveRatio(true);
					break;
				case 4:
					imageView4.setImage(image[i]);
					imageView4.setPreserveRatio(true);
					break;
				case 5:
					imageView5.setImage(image[i]);
					imageView5.setPreserveRatio(true);
					break;
				case 6:
					imageView6.setImage(image[i]);
					imageView6.setPreserveRatio(true);
					break;
				case 7:
					imageView7.setImage(image[i]);
					imageView7.setPreserveRatio(true);
					break;
				case 8:
					imageView8.setImage(image[i]);
					imageView8.setPreserveRatio(true);
					break;
				case 9:
					imageView9.setImage(image[i]);
					imageView9.setPreserveRatio(true);
					break;
				case 10:
					imageView10.setImage(image[i]);
					imageView10.setPreserveRatio(true);
					break;
				case 11:
					imageView11.setImage(image[i]);
					imageView11.setPreserveRatio(true);
					break;
				case 12:
					imageView12.setImage(image[i]);
					imageView12.setPreserveRatio(true);
					break;
				case 13:
					imageView13.setImage(image[i]);
					imageView13.setPreserveRatio(true);
					break;
				case 14:
					imageView14.setImage(image[i]);
					imageView14.setPreserveRatio(true);
					break;
				case 15:
					imageView15.setImage(image[i]);
					imageView15.setPreserveRatio(true);
					break;
				case 16:
					imageView16.setImage(image[i]);
					imageView16.setPreserveRatio(true);
					break;
					
			}
		}*/
		
	}
	
	public void initialize() throws Exception
	{
		searchTextField.setOnMouseClicked(e -> {
            searchTextField.setText("");
        });
		
		
		profilephoto.setOnMouseClicked((MouseEvent e) -> {
			try{
				//System.out.println("Clicked!");
				profileClicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		
		profilephoto.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				profilephoto.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		profilephoto.setOnMouseExited((MouseEvent e) -> {
			try{
				profilephoto.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
	}
	
    @FXML
    void galleryselected(ActionEvent event) throws Exception {
		ViewImage viewimage=new ViewImage();
		viewimage.showWindow(this);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void myphotoselected(ActionEvent event) throws Exception {
		My my=new My();
		my.showWindow(this);
		((Node)(event.getSource())).getScene().getWindow().hide();
		/*
		int num;
		int picNum=sqlcon2.getCount(account);
		System.out.println("picnum:"+picNum);
		//Image[]	image;
		if(picNum>=16)
			image = new Image[16];
		else
			image = new Image[picNum];
		
		image=sqlcon2.SelectTable(account);/////////take

		if(picNum<16)
		{
			//Image[]	image = new Image[picNum];
			//image=sqlcon2.SelectTable(account);/////////take
			System.out.println("homepagec"+image[0]+" "+image[1]);

			for(int i=0;i<picNum;i++)
			{
				num=i+1;
				switch(num)
				{
					case 1:
						System.out.println("case1 set"+image[0]);
						imageView1.setImage(image[i]);
						imageView1.setPreserveRatio(true);
						System.out.println("case1 get"+imageView1.getImage());
						//Image im=new Image("00.png");
						//imageView1.setImage(im);
						//PauseTransition pause = new PauseTransition(Duration.seconds(1));

						//pause.play();
					break;
					case 2:
						System.out.println("case2 "+image[1]);
						imageView2.setImage(image[i]);
						imageView2.setPreserveRatio(true);
					break;
					case 3:
						System.out.println("case3 set"+image[2]);
						imageView3.setImage(image[i]);
						imageView3.setPreserveRatio(true);
						break;
					case 4:
						imageView4.setImage(image[i]);
						imageView4.setPreserveRatio(true);
						break;
					case 5:
						imageView5.setImage(image[i]);
						imageView5.setPreserveRatio(true);
						break;
					case 6:
						imageView6.setImage(image[i]);
						imageView6.setPreserveRatio(true);
						break;
					case 7:
						imageView7.setImage(image[i]);
						imageView7.setPreserveRatio(true);
						break;
					case 8:
						imageView8.setImage(image[i]);
						imageView8.setPreserveRatio(true);
						break;
					case 9:
						imageView9.setImage(image[i]);
						imageView9.setPreserveRatio(true);
						break;
					case 10:
						imageView10.setImage(image[i]);
						imageView10.setPreserveRatio(true);
						break;
					case 11:
						imageView11.setImage(image[i]);
						imageView11.setPreserveRatio(true);
						break;
					case 12:
						imageView12.setImage(image[i]);
						imageView12.setPreserveRatio(true);
						break;
					case 13:
						imageView13.setImage(image[i]);
						imageView13.setPreserveRatio(true);
						break;
					case 14:
						imageView14.setImage(image[i]);
						imageView14.setPreserveRatio(true);
						break;
					case 15:
						imageView15.setImage(image[i]);
						imageView15.setPreserveRatio(true);
						break;
					case 16:
						imageView16.setImage(image[i]);
						imageView16.setPreserveRatio(true);
						break;
						
				}
			}
		}
		//Image[]	image = new Image[16];
		//image=sqlcon2.SelectTable(account);/////////take
		else
		{
			System.out.println("else");
			for(int i=0;i<16;i++)
			{
				num=i+1;
				switch(num)
				{
					case 1:
						imageView1.setImage(image[i]);
						imageView1.setPreserveRatio(true);
						break;
					case 2:
						imageView2.setImage(image[i]);
						imageView2.setPreserveRatio(true);
						break;
					case 3:
						imageView3.setImage(image[i]);
						imageView3.setPreserveRatio(true);
						break;
					case 4:
						imageView4.setImage(image[i]);
						imageView4.setPreserveRatio(true);
						break;
					case 5:
						imageView5.setImage(image[i]);
						imageView5.setPreserveRatio(true);
						break;
					case 6:
						imageView6.setImage(image[i]);
						imageView6.setPreserveRatio(true);
						break;
					case 7:
						imageView7.setImage(image[i]);
						imageView7.setPreserveRatio(true);
						break;
					case 8:
						imageView8.setImage(image[i]);
						imageView8.setPreserveRatio(true);
						break;
					case 9:
						imageView9.setImage(image[i]);
						imageView9.setPreserveRatio(true);
						break;
					case 10:
						imageView10.setImage(image[i]);
						imageView10.setPreserveRatio(true);
						break;
					case 11:
						imageView11.setImage(image[i]);
						imageView11.setPreserveRatio(true);
						break;
					case 12:
						imageView12.setImage(image[i]);
						imageView12.setPreserveRatio(true);
						break;
					case 13:
						imageView13.setImage(image[i]);
						imageView13.setPreserveRatio(true);
						break;
					case 14:
						imageView14.setImage(image[i]);
						imageView14.setPreserveRatio(true);
						break;
					case 15:
						imageView15.setImage(image[i]);
						imageView15.setPreserveRatio(true);
						break;
					case 16:
						imageView16.setImage(image[i]);
						imageView16.setPreserveRatio(true);
						break;
						
				}
			}
		}*/
    }
	
	void profileClicked(MouseEvent event) throws Exception{ 
		try {
  			FileChooser chooser=new FileChooser();
  		    chooser.setInitialDirectory(new File("C:\\"));   
  		    chooser.setTitle("Open Image");              
  		    chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
  		                                         new FileChooser.ExtensionFilter("PNG", "*.png"));

  		    file=chooser.showOpenDialog(stage);
			fileimage=new FileInputStream(file);
			Image photo = new Image(fileimage);
			sqlcon1.updateTable(account,file);
			profilephoto.setImage(photo);
		   /*  
  		    if (file != null) {
				
				sqlcon.insertTable(file,fileimage);
  		        } else {
  		            System.out.println("Image file selection cancelled.");
  		        }
*/
  	    }
		catch (Exception e) {
  		    System.out.println("Error: " + e);
  		}
    }
	
	public String getAccount()
	{
		return account;
	}
    @FXML
    void uploadselected(ActionEvent event) throws Exception {
		
		Upload upload=new Upload();
		upload.showWindow(this);
		((Node)(event.getSource())).getScene().getWindow().hide();
		/*
		try {
  			 FileChooser chooser=new FileChooser();
  		     chooser.setInitialDirectory(new File("C:\\"));   
  		   
  		     chooser.setTitle("Open Image");              
  		     chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
  		                                             new FileChooser.ExtensionFilter("PNG", "*.png"));
  		     //System.out.println(stage);
  		     File selectedFile= chooser.showOpenDialog(stage);
  		     
  		        if (selectedFile != null) {
					// load
  		         
   
					
  		        } else {
  		            System.out.println("Image file selection cancelled.");
  		        }

  	        }
  		
  		
  		     catch (Exception e) {
  		    	 System.out.println("Error: " + e);
  		     }
		*/

    }
	@FXML
    void goPressed(ActionEvent event)throws Exception  {
		String search=searchTextField.getText();
		if((search.charAt(0)=='@') && sqlcon1.reuseAccount(search.substring(1))==true)
		{
			TargetHome targetHome=new TargetHome();
			targetHome.showWindow(this,account,search.substring(1));
			((Node)(event.getSource())).getScene().getWindow().hide();
			//TargetHome targetwindow=new TargetHome();
			//targetwindow.showWindow(search);
			//System.out.println("account search");
		}
		else
		{
			Search searchWindow=new Search();
			searchWindow.showWindow(search,this);
		}
		((Node)(event.getSource())).getScene().getWindow().hide();
		
    }


}