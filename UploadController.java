import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.*;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.event.Event;
import javafx.event.EventHandler;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

public class UploadController {

	JdbcMySQL sqlcon = new JdbcMySQL();////////connection
	MyPhoto sqlcon1 = new MyPhoto();
	Stage stage;
	File file;
	FileInputStream fileimage;
	Image image;
	File outputFile;
	
	@FXML
    private ListView<String> listView;

	ObservableList<String> s1=FXCollections.observableArrayList("fashion", "scenery", "boy", "girl","makeup","OOTD","Humor","food");
	
	@FXML
    private Button select;
	@FXML
    private TextField wordTextField;
	
	String choice;
	HomepageController homepagec;
	Date date;
	
	public void initialize() throws Exception
    {
		
		listView.setItems(s1);
		/*listView.getItems().add("fashion");
		listView.getItems().add("scenery");
		listView.getItems().add("boy");
		listView.getItems().add("girl");
		listView.getItems().add("makeup");
		listView.getItems().add("design");*/
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		/*listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            public void changed(ObservableValue<? extends String> ov,final String oldvalue, final String newvalue) 
            {
                choiceChanged(ov, oldvalue, newvalue);
        }});*/
		listView.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
			//System.out.println("new" + nv);
			choice="";
			 ObservableList<String> selectedItems =  listView.getSelectionModel().getSelectedItems();
			for(String s : selectedItems){
              
			   choice+=s+" ";
            }
			 System.out.println("selected item " + choice);
            //selected.setItems(listView.getSelectionModel().getSelectedItems());
        });
		/*
		listView.setOnMouseClicked(new EventHandler<Event>()
		{
			@Override
            public void handle(Event event) 
			{
                ObservableList<String> selectedItems =  listView.getSelectionModel().getSelectedItems();
				for(String s : selectedItems){
                    System.out.println("selected item " + s);
               }

            }

        });*/

	}
	 public void choiceChanged(ObservableValue<? extends String> observable,String oldValue,String newValue) 
    {
        String oldText = oldValue == null ? "null" : oldValue.toString();
        String newText = newValue == null ? "null" : newValue.toString();
        System.out.println("newtext:"+newText);
        //logging.appendText("Season changed: old = " + oldText + ", new = " + newText + "\n");
    }
	public void setStage(Stage stage,HomepageController homepagec)
	{
		this.homepagec=homepagec;
		this.stage=stage;
	}
	public void setStage(Stage stage,HomepageController homepagec,Image image,File outputFile)
	{
		this.homepagec=homepagec;
		this.stage=stage;
		this.image=image;
		file=outputFile;
		select.setText(outputFile.getName());
	}
	@FXML
    void selectPressed(ActionEvent event) {
		try {
  			FileChooser chooser=new FileChooser();
  		    chooser.setInitialDirectory(new File("C:\\"));   
  		    chooser.setTitle("Open Image");              
  		    chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
  		                                         new FileChooser.ExtensionFilter("PNG", "*.png"));

  		    file=chooser.showOpenDialog(stage);
			fileimage=new FileInputStream(file);
			select.setText(file.getName());
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
	@FXML
    void uploadPressed(ActionEvent event) throws Exception{
		String word=wordTextField.getText();
		
		date=new Date();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = formatter.format(date);
		
		if (file != null) {
			sqlcon.insertTable(word,file,homepagec.getAccount(),choice,s);
		//	System.out.println("uploadc accountname:"+homepagec.getAccount());
			sqlcon1.insertTable(word,file,homepagec.getAccount(),choice,s);
  		    } else {
  		    System.out.println("Image file selection cancelled.");
  		}
		//Homepage homepage=new Homepage();
		homepagec.getHomepage().showWindow();
		((Node)(event.getSource())).getScene().getWindow().hide();
		date=new Date();
    }

}