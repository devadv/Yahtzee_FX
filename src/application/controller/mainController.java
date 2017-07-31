package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class mainController {
	@FXML
	private Button button_1, button_2, button_3, button_4, button_5;
	private Button roll_Dices;
	private ImageView dice_1_imageV, dice_2_imageV, dice_3_imageV, dice_4_imageV, dice_5_imageV;
	private Main main;
	private Dice[] dices;
	private boolean diceIsCreated = false;

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
		roll();

	}

	private void setDiceImages() {
		int dice_1 = dices[0].getValue();

	}

	private void createDices(){
		for (int i = 0; i < dices.length; i++) {
			dices[i] = new Dice();
		}
	}

	public void roll() {
		if(!diceIsCreated){
			for (int i = 0; i < dices.length; i++) {
				dices[i].roll();
			}
		}
	}

	public void setMainApp(Main main) {
		this.main = main;

	}


}
