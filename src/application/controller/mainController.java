package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class mainController {
	@FXML
	private Button button_1, button_2, button_3, button_4, button_5;
	private Button roll_Dices;
	private Main main;

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


	}

	public void setMainApp(Main main) {
		this.main = main;

	}


}
