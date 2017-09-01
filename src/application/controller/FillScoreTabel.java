package application.controller;

import application.model.Dice;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class FillScoreTabel {
	int dice_1 = 0;
	int dice_2 = 0;
	int dice_3 = 0;
	int dice_4 = 0;
	int dice_5 = 0;

	@FXML
	private TextArea onesTxArea, twosTxArea, threesTxArea, foursTxArea, fivesTxArea, sixesTxArea;

	public void calculateDiceCombination(Dice[] dices) {

		dice_1 = dices[0].getValue();
		dice_2 = dices[1].getValue();
		dice_3 = dices[2].getValue();
		dice_4 = dices[3].getValue();
		dice_5 = dices[4].getValue();



	}// end method calculateDiceCombination


	public int getOnesTxArea(){
		return 0;
	}

}// end class FillScoreTabel
