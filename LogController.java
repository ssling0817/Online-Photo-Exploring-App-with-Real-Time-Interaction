import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TextField;
import java.io.*;
//cannot delete after typing
public class LogController {

	LogIn sqlcon = new LogIn();////////connection
	
	MyPhoto sqlcon2 = new MyPhoto();
	
	String account,password="";
	int pre=0;
	
	@FXML
    private TextField accountTextField;

    @FXML
    private TextField passwordTextField;
	boolean change=true;

    @FXML
    void returnClicked(ActionEvent event) throws Exception{
		First first=new First();
		first.showWindow();
		((Node)(event.getSource())).getScene().getWindow().hide();

    }
	@FXML
    void keyPressed(KeyEvent e) {
		if (e.getCode() == KeyCode.BACK_SPACE || e.getCode() == KeyCode.DELETE) {
			passwordTextField.setText("");
			password="";
		}
    }

    @FXML
    void keyTyped(KeyEvent event) {
		String t=passwordTextField.getText();
			
			if(t.length()<1)
			{
					
			}
			else
			{
				
				pre=t.length();
				password+=passwordTextField.getText().charAt(0);
			
			}
			
			
			String s="";
			for(int i=0;i<t.length();i++)
			{
				s+="*";
			}
			passwordTextField.setText(s);
    
    }

	public void initialize() throws Exception
    {
		accountTextField.setOnMouseClicked(e -> {
            accountTextField.setText("");
        });
		passwordTextField.setOnMouseClicked(e -> {
            passwordTextField.setText("");
			password="";
        });
		
		/*passwordTextField.textProperty().addListener((obs, oldText, newText) -> {
			String t=passwordTextField.getText();
			
			if(change)
			{
				if(t.length()<1)
				{
					
				}
				else
				{
					//if(t.length()<pre)
					//{
					//	 passwordTextField.setText("");
					//	 pre=t.length();
					//}
					//else
					//{
						pre=t.length();
						System.out.println("char"+t.charAt(t.length()-1));
						password+=passwordTextField.getText().charAt(t.length()-1);
						change=false;
					//}
				}
			}
			else
			{
				change=true;
			}
			String s="";
			for(int i=0;i<t.length();i++)
			{
				s+="*";
			}
			passwordTextField.setText(s);
    
		});*/
		
		/*
		passwordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			String t=passwordTextField.getText();
			String s=null;
			for(int i=0;i<t.length();i++)
			{
				s+="*";
			}
			passwordTextField.setText(s);
		});*/
		
	}
	@FXML
    void logInPressed(ActionEvent event) throws Exception {
		account=accountTextField.getText();
		//password=passwordTextField.getText();
		System.out.println(account+" "+password);
		String realpassword=sqlcon.SelectTable(account);
		
		//System.out.println(realpassword);
		
		boolean available=false;
		
		if(realpassword==null)
		{
			accountTextField.setText("wrong");
			//0622
			password="";
			passwordTextField.setText("");
		}
		else if(((account.charAt(0)>'a')&&(account.charAt(0)<'z'))||((account.charAt(0)>'A')&&(account.charAt(0)<'Z')))
		{			
			if(realpassword.equals(password)){
				
				sqlcon2.createTable(account);
				sqlcon2.createFollowTable(account);
				sqlcon2.createArchiveTable(account);
				sqlcon2.createFollowerTable(account);
				
				Homepage homepage=new Homepage();
				homepage.showWindow(account);
				((Node)(event.getSource())).getScene().getWindow().hide();
			}
			else
			{
				System.out.println("fail");
				//0622
				accountTextField.setText("fail");
				passwordTextField.setText("");
				password="";
			}
		}
		else{
			accountTextField.setText("Unavailable");
			passwordTextField.setText("");
		}
    }
	@FXML
    void registerPressed(ActionEvent event) throws Exception{
		Register r=new Register();
		r.showWindow();
		((Node)(event.getSource())).getScene().getWindow().hide();
    }

}