package testing.stages.fxml;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Scene2Controller {

	@FXML
	private Button btn;
	private Stage primaryStage;
	private FXMLLoader loader;
	private AnchorPane rootLayout;

	private Scene scene1;

	@FXML
	public void switchToScene1(){
		System.out.println("switched to scene1");
		primaryStage.setScene(scene1);
		
	}

	public void start(Stage primaryStage, Scene scene1){
		this.primaryStage = primaryStage;
		this.scene1 = scene1;
	}


}
