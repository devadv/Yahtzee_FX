package testing.stages;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartApp extends Application {

	private Stage primaryStage;
	private Scene scene1 , scene2;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Switching Scenes");
		BorderPane borderPane = new BorderPane();
		Button btn1 = new Button();
		TextField  txt = new TextField();
		txt.setText("Change the text and switch scene");
		btn1.setText("Switch to Second Stage");
		btn1.setOnAction(e -> primaryStage.setScene(scene2));
		StackPane pane1 = new StackPane();
		borderPane.setTop(txt);
		pane1.getChildren().add(borderPane);
		pane1.getChildren().add(btn1);
		scene1 = new Scene(pane1 ,300,300);

		Button btn2 = new Button();

		btn2.setText("Switch to First Stage");
		btn2.setOnAction(e -> primaryStage.setScene(scene1));
		StackPane pane2 = new StackPane();
		pane2.getChildren().add(btn2);
		scene2 = new Scene(pane2 ,300,400);


		primaryStage.setScene(scene1);
		primaryStage.show();


	}
public static void main(String[] args) {
	launch(args);
}
}
