package application;

import java.io.IOException;

import application.controller.Controller;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	private AnchorPane rootLayout;
	private Stage primaryStage;
	private FXMLLoader loader;

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			/*BorderPane root = new BorderPane();
			Scene scene = new Scene(group,1100,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);*/
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
			Text text = new Text();
			text.setFont(Font.font("verdana" , FontWeight.BOLD , FontPosture.REGULAR , 50));
			text.setX(200);
			text.setY(550);
			text.setFill(Color.BROWN);
			text.setStrokeWidth(2);
			text.setStroke(Color.BLUE);
			text.setText("YATHZEE GAME");

			Text text1 = new Text();
			text1.setFont(Font.font("verdana" , FontWeight.BOLD , FontPosture.REGULAR , 20));
			text1.setX(350);
			text1.setY(580);
			text1.setFill(Color.BLUE);
			text1.setStrokeWidth(1);
			text1.setStroke(Color.BROWN);
			text1.setText("made by Herman & Ben");

			Rectangle rectangle = new Rectangle();
			rectangle.setX(200);
			rectangle.setY(550);
			rectangle.setWidth(500f);
			rectangle.setHeight(50f);
			rectangle.setFill(Color.YELLOWGREEN);
			rectangle.setArcWidth(30.0);
			rectangle.setArcHeight(20.0);
			//list with nodes
			ObservableList<Node> list= rootLayout.getChildren();
			list.add(rectangle);
			list.add(text);
			list.add(text1);
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			Controller controller = loader.getController();
			//controller.setMainApp(this);
			controller.start();

			primaryStage.show();
		} catch (IOException e) {
				e.printStackTrace();
			}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
