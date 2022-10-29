import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Homepage extends Application{
	
	Stage st=new Stage();
	String account;
	
	@Override
	public void start(Stage stage)throws Exception
	{
		System.out.println("in Homepage start");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
		Parent root =(Parent)loader.load();
		Scene scene = new Scene(root); // attach scene graph to scene
		HomepageController controller = (HomepageController)loader.getController();
		controller.setAccount(account,this);
		//Parent root=FXMLLoader.load(getClass().getResource("Homepage.fxml"));
		//Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Homepage");
		stage.show();
	}
	
	public static void main(String []args)
	{
		launch(args);
	}
	
	public void showWindow() throws Exception
	{
		start(st);
	}
	
	public void showWindow(String account) throws Exception
	{
		this.account=account;
		System.out.println("in account showWindow,account="+this.account);
		start(st);
	}
	
}
