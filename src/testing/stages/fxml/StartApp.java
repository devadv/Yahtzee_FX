package testing.stages.fxml;

import java.io.IOException;

import application.Main;
import application.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartApp extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	private Scene scene1;
	private FXMLLoader loader1;
	private AnchorPane rootLayout1;
	private Scene scene2;
	private AnchorPane rootLayout2;
	private FXMLLoader loader2;


	@Override
	public void start(Stage primaryStage) throws Exception {
		loader1 = new FXMLLoader();
		loader1.setLocation(Main.class.getResource("view/scene1.fxml"));
		rootLayout1 = (AnchorPane) loader1.load();
		loader2 = new FXMLLoader();
		loader2.setLocation(Main.class.getResource("view/scene2.fxml"));
		rootLayout2 = (AnchorPane) loader2.load();


		scene1 = new Scene(rootLayout1);
		Scene1Controller controller1 = loader1.getController();
		scene2 = new Scene(rootLayout2);
		Scene2Controller controller2 = loader2.getController();

		controller1.start(primaryStage, scene2);
		controller2.start(primaryStage, scene1);

		primaryStage.setScene(scene1);

		primaryStage.show();
	}

}
