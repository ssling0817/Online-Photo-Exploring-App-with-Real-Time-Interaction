import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.scene.control.TextField;
import java.io.*;

public class RegisterController {

	LogIn sqlcon = new LogIn();////////connection
	
	String account,password;
	@FXML
    private TextField accountTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void returnClicked(ActionEvent event) throws Exception{
		Log log=new Log();
		log.showWindow();
		((Node)(event.getSource())).getScene().getWindow().hide();

    }
	public void initialize() throws Exception
    {
		accountTextField.setOnMouseClicked(e -> {
            accountTextField.setText("");
        });
		
	}
	/*
	@FXML
    void logInPressed(ActionEvent event) throws Exception {
		account=accountTextField.getText();
		password=passwordTextField.getText();
		System.out.println(account+" "+password);
		String realpassword=sqlcon.SelectTable(account);
		//System.out.println(realpassword);
		
		if(realpassword.equals(password)){
			System.out.println("success");
			
			ViewImage viewimage=new ViewImage();
			
			viewimage.showWindow();
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		else
		{
			System.out.println("fail");
		}
    }
	*/
	@FXML
    void registerPressed(ActionEvent event)throws Exception {
		account=accountTextField.getText();
		password=passwordTextField.getText();
		
		
		/*
		for(int i=0;i<account.length()&&blank;i++)
		{
			if(account.charAt(i)==' ')
				blank=false;
		}*/
		//account= account.replaceAll("\\s","");
		boolean blank=account.contains(" ");
		
		if(((account.charAt(0)>'a')&&(account.charAt(0)<'z'))||((account.charAt(0)>'A')&&(account.charAt(0)<'Z'))&&!blank)
		{
			if(sqlcon.reuseAccount(account)==true)
			{
				accountTextField.setText("Already used");
			}
			else
			{
			sqlcon.insertTable(account,password);
			Log log=new Log();
			log.showWindow();
			((Node)(event.getSource())).getScene().getWindow().hide();
			}
		}
		else{
			accountTextField.setText("Unavailable");
			passwordTextField.setText("");
		}
		
		
		
    }

}