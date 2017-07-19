package application;

import java.io.IOException;

import application.controller.mainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private AnchorPane rootLayout;
	private Stage primaryStage;
	private FXMLLoader loader;

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1200,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Yahtzee");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

		initRootLayout();
	}

	/*
	 *Initialize the root layout.
	 */

	private void initRootLayout() {
		try{
			// Load root layout from fxml file.
			loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/view.fxml"));
			rootLayout = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			mainController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
	} catch (IOException e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
