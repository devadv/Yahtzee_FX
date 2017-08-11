package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class mainController {
	@FXML
	private Button button_1, button_2, button_3, button_4, button_5;
	private Button roll_Dices;
	private ImageView dice_1_imageV, dice_2_imageV, dice_3_imageV, dice_4_imageV, dice_5_imageV;
	private Main main;
	private Dice[] dices = new Dice[6];
	private Game game = new Game(0, 0);
	private boolean diceIsCreated = false;
	private Stage primaryStage;

	@FXML
	private void dice_click(ActionEvent event){

		if(event.getSource() == button_1)
		{
			System.out.println("btn 1 click");
		}

		if(event.getSource() == button_2)
		{
			System.out.println("btn 2 click");
		}

		if(event.getSource() == button_3)
		{
			System.out.println("btn 3 click");
		}

		if(event.getSource() == button_4)
		{
			System.out.println("btn 4 click");
		}

		if(event.getSource() == button_5)
		{
			System.out.println("btn 5 click");
		}

	}

	@FXML
	private void rollDices_click(){
		createDices();
		diceIsCreated = true;
		for(int i = 0; i < dices.length; i++) {
			dices[i] = new Dice();
			dices[i].roll();
		}

		setDiceImages();
	}

	@FXML
	private void setDiceImages() {
		int dice_1_Value = dices[0].getValue();
		System.out.println(dice_1_Value);

		Image[] image = new Image[6];
		image[0] = new Image("file:resources/images/dice_1.png");
		image[1] = new Image("file:resources/images/dice_2.png");
		image[2] = new Image("file:resources/images/dice_3.png");
		image[3] = new Image("file:resources/images/dice_4.png");
		image[4] = new Image("file:resources/images/dice_5.png");
		image[5] = new Image("file:resources/images/dice_6.png");

		ImageView[] imageV = new ImageView[5];
		imageV[0] = new ImageView();
		dice_1_imageV = new ImageView();
		imageV[0] = dice_1_imageV;
		imageV[0].setImage(image[0]);
		imageV[0].setVisible(true);
		
	}

	@FXML
	private void createDices(){
		if(!diceIsCreated){
			for (int i = 0; i < 5; i++) {
				dices[i] = new Dice();
				dices[i].roll();
			}
		}

	}

	public void setMainApp(Main main) {
		this.main = main;

	}


}
