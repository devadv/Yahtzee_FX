package testing.stages.fxml;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Scene1Controller {

	@FXML
	private Button btn;
	private FXMLLoader loader;
	private AnchorPane rootLayout;
	private Scene scene1;
	private Scene scene2;
	private Stage primaryStage;

	@FXML
	private TextField txtField;

	@FXML
	public void switchToScene2() {
		System.out.println("switched to scene2");
		System.out.println(txtField.getText());
		primaryStage.setScene(scene2);
		primaryStage.show();

	}

	public void start(Stage primaryStage, Scene scene2) {
		this.primaryStage = primaryStage;
		this.scene2 = scene2;

	}

}
