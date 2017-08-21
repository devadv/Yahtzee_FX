package application.controller;

import application.Main;
import application.model.Dice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class mainController {
	@FXML
	private Button button_1, button_2, button_3, button_4, button_5;
	@FXML
	private Button roll_Dices;
	@FXML
	private ImageView dice_1_imageV, dice_2_imageV, dice_3_imageV, dice_4_imageV, dice_5_imageV;
	@FXML
	private TextArea onesTxArea, t;

	private Main main;
	private Game game;
	private Dice[] dices = new Dice[5];
	private boolean diceIsCreated = false;
	private Stage primaryStage;

	@FXML
	public void mainController() {
		game = new Game(1, 13);

		for (int i = 0; i < dices.length; i++) {
			this.dices[i] = game.getDice(i);
		}

	}

	@FXML
	private void saveValue(MouseEvent event){
		System.out.println(event.getSource());

	}

	@FXML
	private void dice_click(ActionEvent event){
		ColorAdjust colorOnHold = new ColorAdjust();
		ColorAdjust colorNotHold = new ColorAdjust();
		colorOnHold.setBrightness(-0.5);
		colorNotHold.setBrightness(0.0);

		if(game.getNumberOfThrows() > 1){

			if(event.getSource() == button_1)
			{
				dices[0].setHold();
				if(dices[0].isHold()){
					dice_1_imageV.setEffect(colorOnHold);
				}
				else{
					dice_1_imageV.setEffect(colorNotHold);
				}
			}

			if(event.getSource() == button_2)
			{
				dices[1].setHold();
				if(dices[1].isHold()){
					dice_2_imageV.setEffect(colorOnHold);
				}
				else{
					dice_2_imageV.setEffect(colorNotHold);
				}
			}

			if(event.getSource() == button_3)
			{
				dices[2].setHold();
				if(dices[2].isHold()){
					dice_3_imageV.setEffect(colorOnHold);
				}
				else{
					dice_3_imageV.setEffect(colorNotHold);
				}
			}

			if(event.getSource() == button_4)
			{
				dices[3].setHold();
				if(dices[3].isHold()){
					dice_4_imageV.setEffect(colorOnHold);
				}
				else{
					dice_4_imageV.setEffect(colorNotHold);
				}
			}

			if(event.getSource() == button_5)
			{
				dices[4].setHold();
				if(dices[4].isHold()){
					dice_5_imageV.setEffect(colorOnHold);
				}
				else{
					dice_5_imageV.setEffect(colorNotHold);
				}
			}
		}

	}

	@FXML
	private void rollDices_click(){
		game.roll();
		setDiceImages();
	}

	@FXML
	private void setDiceImages() {
		for (int i = 0; i < dices.length; i++) {
			System.out.println("dice " + (i + 1) + " = " + dices[i].getValue());
		}
		System.out.println();

		Image[] image = new Image[6];
		image[0] = new Image("file:resources/images/dice_1.png");
		image[1] = new Image("file:resources/images/dice_2.png");
		image[2] = new Image("file:resources/images/dice_3.png");
		image[3] = new Image("file:resources/images/dice_4.png");
		image[4] = new Image("file:resources/images/dice_5.png");
		image[5] = new Image("file:resources/images/dice_6.png");

		ImageView[] imageView = new ImageView[5];
		imageView[0] = dice_1_imageV;
		imageView[1] = dice_2_imageV;
		imageView[2] = dice_3_imageV;
		imageView[3] = dice_4_imageV;
		imageView[4] = dice_5_imageV;

		for (int i = 0; i < imageView.length; i++) {
			if(dices[i].isHold() == false){
				imageView[i].setImage(image[dices[i].getValue() - 1]);
			}
		}


	}// end method setDiceImages

	public void setMainApp(Main main) {
		this.main = main;

	}

}
