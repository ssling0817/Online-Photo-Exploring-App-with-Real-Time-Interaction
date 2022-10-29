import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class First extends Application{
	
	Stage st=new Stage();
	
	@Override
	public void start(Stage stage)throws Exception
	{
		Parent root=FXMLLoader.load(getClass().getResource("First.fxml"));
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Phipster");
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
	
}
