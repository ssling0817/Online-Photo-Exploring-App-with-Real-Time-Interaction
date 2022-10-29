import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.css.PseudoClass;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class FollowingController {

	JdbcMySQL sqlcon1 = new JdbcMySQL();////////connection
	MyPhoto sqlcon2 = new MyPhoto();
	
	Image[]	image;
	private Stage stage;
    @FXML private Button returnButton;
	@FXML private ImageView imageView1;
	@FXML private ImageView imageView2;
    @FXML private ImageView imageView3;
    @FXML private ImageView imageView4;
    @FXML private ImageView imageView5;
    @FXML private ImageView imageView6;
    @FXML private ImageView imageView7;
    @FXML private ImageView imageView8;
    @FXML private ImageView imageView9;
    @FXML private ImageView imageView10;
    @FXML private ImageView imageView11;
    @FXML private ImageView imageView12;
    @FXML private ImageView imageView13;
    @FXML private ImageView imageView14;
    @FXML private ImageView imageView15;
    @FXML private ImageView imageView16;
	@FXML
    private Label currentPageLabel;
    @FXML
    private Label totalPageLabel;

	String account;
	HomepageController homepagec;
	ViewImage view;
	Following followClass;
	String[] followings=new String[1000];
	int pagePhotoStatus;//first index
	int photoIndex;
	int photoNum;//total
	int pagenum;
	int totalpagenum;
	
	int getpagePhotoStatus()
	{
		return pagePhotoStatus;
	}
		
	
	int getphotoIndex()
	{
		return photoIndex;
	}
		
	@FXML
    void nextPressed(ActionEvent event) throws Exception {
		if((pagePhotoStatus+1+16*(totalpagenum-pagenum)<=photoNum)&&pagenum!=totalpagenum)
		{
			pagenum++;
			pagePhotoStatus+=16;
				//whether last page
				if(photoNum-pagePhotoStatus==photoNum%16)
					photoIndex=photoNum%16;
				else
					photoIndex=16;
			currentPageLabel.setText(String.valueOf(pagenum));
			totalPageLabel.setText(String.valueOf(totalpagenum));
			for(int k=0;k<16;k++)
			{
				image[k]=null;
			}		

				image=sqlcon1.SelectFollowingTable(account,followings,pagePhotoStatus,photoIndex);
				int num;
				for(int i=0;i<16;i++)
				{
					num=i+1;
					//System.out.println("showImage"+image[i]);
					switch(num)
					{
						case 1:
							//System.out.println("view case1 set"+image[0]);
							imageView1.setImage(image[i]);
							imageView1.setPreserveRatio(true);
							//System.out.println("view case1 get"+imageView1.getImage());
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
		
    }
	@FXML
    void previousPressed(ActionEvent event) throws Exception {
		if(pagePhotoStatus>0)
		{
			System.out.println("in pre");
			pagePhotoStatus-=16;
			photoIndex=16;
			pagenum--;
			currentPageLabel.setText(String.valueOf(pagenum));
			totalPageLabel.setText(String.valueOf(totalpagenum));
			for(int k=0;k<16;k++)
			{
				image[k]=null;
			}	
				image=sqlcon1.SelectFollowingTable(account,followings,pagePhotoStatus,photoIndex);
				int num;
				for(int i=0;i<16;i++)
				{
					num=i+1;
					//System.out.println("showImage"+image[i]);
					switch(num)
					{
						case 1:
							imageView1.setImage(image[i]);
							imageView1.setPreserveRatio(true);
							//System.out.println("view case1 get"+imageView1.getImage());
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
		
    }
	public HomepageController getHomepagec()
	{
		return homepagec;
	}
	
    void imageView1Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,0,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView2Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,1,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView3Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,2,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView4Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,3,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView5Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,4,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView6Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,5,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView7Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,6,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView8Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,7,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView9Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,8,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView10Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,9,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView11Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,10,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView12Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,11,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView13Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,12,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView14Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,13,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView15Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,14,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }
	void imageView16Clicked(MouseEvent event) throws Exception{ 
		FollowingPhoto photoInfo=new FollowingPhoto();
		photoInfo.showWindow(homepagec,15,this,pagePhotoStatus,photoIndex);
		((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void returnClicked(ActionEvent event) throws Exception{
		
		homepagec.getHomepage().showWindow();
		((Node)(event.getSource())).getScene().getWindow().hide();

    }
	public String[] getFollowingArray()
	{
		return followings;
	}
	public void setStage(Stage stage, HomepageController home,Following f) throws Exception
	{
		this.homepagec=home;
		this.stage=stage;
		this.followClass=f;
		account=homepagec.getAccount();
		followings=sqlcon2.followingTable(account);
		//System.out.println("fc: "+following[0]);
		
		int num;
		int picNum=sqlcon1.getFollowingPhotoCount(account,followings);
		photoNum=picNum;	//photo number
		photoIndex=photoNum%16;
		pagePhotoStatus=photoNum-(photoNum%16);//first index
		
		if(photoNum%16==0)
		{	
			totalpagenum=photoNum/16;
			pagenum=totalpagenum;
		}
		else
		{	
			totalpagenum=(photoNum+1)/16+1;
			pagenum=totalpagenum;
		}
		currentPageLabel.setText(String.valueOf(pagenum));
		totalPageLabel.setText(String.valueOf(totalpagenum));
		image = new Image[16];
		for(int k=0;k<16;k++)
		{
			image[k]=null;
		}	
		
		if(photoIndex==0)
		{
			pagePhotoStatus=photoNum-16;
			photoIndex=16;
		}
		
		image=sqlcon1.SelectFollowingTable(account,followings,pagePhotoStatus,photoIndex);
		
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
		}
		
	
	public Following getFollow()
	{
		return followClass;
	}
	public void initialize() throws Exception
    {
		imageView1.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView1Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView2.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView2Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView3.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView3Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView4.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked4!");
				imageView4Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView5.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView5Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView6.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView6Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView7.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView7Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView8.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView8Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView9.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView9Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView10.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView10Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView11.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView11Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView12.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView12Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView13.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView13Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView14.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView14Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView15.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView15Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView16.setOnMouseClicked((MouseEvent e) -> {
			try{
				System.out.println("Clicked!");
				imageView16Clicked(e);
			} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		
		//
		imageView1.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView1.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView1.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView1.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView2.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView2.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView2.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView2.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView3.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView3.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView3.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView3.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView4.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView4.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView4.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView4.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView5.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView5.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView5.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView5.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView6.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView6.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView6.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView6.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView7.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView7.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView7.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView7.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView8.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView8.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView8.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView8.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView9.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView9.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView9.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView9.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView10.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView10.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView10.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView10.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView11.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView11.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView11.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView11.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView12.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView12.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView12.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView12.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView13.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView13.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView13.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView13.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView14.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView14.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView14.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView14.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView15.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView15.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView15.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView15.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView16.setOnMouseEntered((MouseEvent e) -> {
			try{
				//System.out.println("Enter!");
				imageView16.setEffect(new DropShadow(20, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		imageView16.setOnMouseExited((MouseEvent e) -> {
			try{
				imageView16.setEffect(new DropShadow(0, Color.BLACK));
				} 
			catch (Exception event){
				throw new IllegalStateException("something went wrong", event);
			}
           
        });
		//set 1000 if too many explode
		/*for(int k=0;k<1000;k++)
			following[k]=new String("");*/
		
	}
}