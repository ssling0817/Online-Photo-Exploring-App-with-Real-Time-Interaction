import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class FirstController {
	
	public String s="";
	private Stage stage;
	private Image image;
	JdbcMySQL sqlcon = new JdbcMySQL();
	
	@FXML
    void editPressed(ActionEvent event)throws Exception {
		Photo photo=new Photo();
		photo.showWindow();
		((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void uploadPressed(ActionEvent event) throws Exception {
		/*
		try {
  			 FileChooser chooser=new FileChooser();
  		     chooser.setInitialDirectory(new File("C:\\"));   
  		   
  		     chooser.setTitle("Open Image");              
  		     chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
  		                                          new FileChooser.ExtensionFilter("PNG", "*.png"));
  		   
  		    File file=chooser.showOpenDialog(null);
			FileInputStream fileimage=new FileInputStream(file);
			
		    
  		    if (file != null) {
				sqlcon.insertTable(file,fileimage);
  		        } else {
  		            System.out.println("Image file selection cancelled.");
  		        }

  	        }
		catch (Exception e) {
  		    	 System.out.println("Error: " + e);
  		    }
			*/
			Log log=new Log();
			log.showWindow();
			((Node)(event.getSource())).getScene().getWindow().hide();
			/*
			ViewImage viewimage=new ViewImage();
			viewimage.showWindow();
			((Node)(event.getSource())).getScene().getWindow().hide();
		*/

    }
}
