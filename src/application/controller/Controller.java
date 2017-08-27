package application.controller;

import application.Main;
import application.model.Dice;
import application.model.Yahtzee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {
	@FXML
	private Button button_1, button_2, button_3, button_4, button_5;
	@FXML
	private Button roll_Dices;
	@FXML
	private ImageView dice_1_imageV, dice_2_imageV, dice_3_imageV, dice_4_imageV, dice_5_imageV;
	@FXML
	private TextArea onesTxArea, twosTxArea, threesTxArea, foursTxArea, fivesTxArea, sixesTxArea;
	@FXML
	private TextArea threeOfAKindTxArea, fourOfAKindTxArea, fullHouseTxArea, smStraightTxArea;
	@FXML
	private TextArea lgStraightTxArea, yahtzeeTxArea, chanceTxArea, yahtzeeBonusTxArea;
	@FXML
	private TextArea totalScoreTxArea, bonusTxArea , totalFacesTxArea, totalDiceCombTxArea, grandTotalTxArea;
	@FXML
	private GridPane gridcolumn1;
	@FXML
	private Label numberofthrowsLabel;
	@FXML
	private Label numberofroundsLabel;
	@FXML
	private Label numberofplayerLabel;

	private int numberofrounds = 13;
	private int numberofthrows = 0;
	private int player = 1;
	private int totalFaces;
	private int totalDiceComb;
	private Main main;
	private Dice[] dices = new Dice[5];

	public Controller() {

	}

	public void start() {

		numberofroundsLabel.setText("" + numberofrounds);
		numberofthrowsLabel.setText("" + numberofthrows);
		numberofplayerLabel.setText("" + player);

		for (int i = 0; i < dices.length; i++) {
			dices[i] = new Dice();
		}
		rollDices_click();
		setDiceImages();
	}

	@FXML
	private void saveValue(MouseEvent event) {

		if (event.getSource() == onesTxArea) {
			int value = Yahtzee.countValueDices(dices, 1);
			onesTxArea.setText("" + value);
			onesTxArea.setDisable(true);
			totalFaces += value;
			numberofthrows = 0;
			reset();
			rollDices_click();


		}
		if (event.getSource() == twosTxArea) {
			int value = Yahtzee.countValueDices(dices, 2);
			//twosTxArea.setText("" + value);

			double layoutX = twosTxArea.getLayoutX();
			double layoutY = twosTxArea.getLayoutY();
			System.out.println("X: " +layoutX);
			System.out.println("Y: " +layoutY);
			Label twosTxLabel = new Label();
			int index = gridcolumn1.getChildren().indexOf(twosTxArea);
			System.out.println("Index"+ index);
			gridcolumn1.getChildren().remove(index);
			gridcolumn1.add(twosTxLabel, 1, 1);
			twosTxLabel.setText(""+ value);


			totalFaces += value;
			numberofthrows = 0;
			reset();
			rollDices_click();

		}
		if (event.getSource() == threesTxArea) {
			int value = Yahtzee.countValueDices(dices, 3);
			threesTxArea.setText("" + value);
			totalFaces += value;
			numberofthrows = 0;
			reset();
			rollDices_click();

		}
		if (event.getSource() == foursTxArea) {
			int value = Yahtzee.countValueDices(dices, 4);
			foursTxArea.setText("" + value);
			totalFaces += value;
			numberofthrows = 0;
			reset();
			rollDices_click();

		}
		if (event.getSource() == fivesTxArea) {
			int value = Yahtzee.countValueDices(dices, 5);
			fivesTxArea.setText("" + value);
			totalFaces += value;
			numberofthrows = 0;
			reset();
			rollDices_click();

		}
		if (event.getSource() == sixesTxArea) {
			int value = Yahtzee.countValueDices(dices, 6);
			sixesTxArea.setText("" + value);
			totalFaces += value;
			numberofthrows = 0;
			reset();
			rollDices_click();

		}
		totalScoreTxArea.setText("" + totalFaces);

		if (event.getSource() == threeOfAKindTxArea) {
			if (Yahtzee.isSameDices(dices, 3)) {
				int value = Yahtzee.countAllDices(dices);
				threeOfAKindTxArea.setText("" + value);
				totalDiceComb += value;
				numberofthrows = 0;
				reset();
				rollDices_click();
			}

		}
		totalDiceCombTxArea.setText("" + totalDiceComb);
		numberofrounds--;
		numberofroundsLabel.setText("" + numberofrounds);
	}

	@FXML
	private void rollDices_click() {

		if (numberofthrows < 3) {
			for (int i = 0; i < dices.length; i++) {
				dices[i].roll();
			}
			numberofthrows++;
			numberofthrowsLabel.setText(numberofthrows + "");

			setDiceImages();

		}

	}

	public void reset() {
		for (int i = 0; i < dices.length; i++) {
			if (dices[i].isHold()) {
				dices[i].setHold();
			}
		}
		ColorAdjust colorNotHold = new ColorAdjust();
		colorNotHold.setBrightness(0.0);
		dice_1_imageV.setEffect(colorNotHold);
		dice_2_imageV.setEffect(colorNotHold);
		dice_3_imageV.setEffect(colorNotHold);
		dice_4_imageV.setEffect(colorNotHold);
		dice_5_imageV.setEffect(colorNotHold);

	}

	@FXML
	private void dice_click(ActionEvent event) {
		ColorAdjust colorOnHold = new ColorAdjust();
		ColorAdjust colorNotHold = new ColorAdjust();
		colorOnHold.setBrightness(-0.5);
		colorNotHold.setBrightness(0.0);

		if (event.getSource() == button_1) {
			dices[0].setHold();
			if (dices[0].isHold()) {
				dice_1_imageV.setEffect(colorOnHold);
			} else {
				dice_1_imageV.setEffect(colorNotHold);
			}
		}

		if (event.getSource() == button_2) {
			dices[1].setHold();
			if (dices[1].isHold()) {
				dice_2_imageV.setEffect(colorOnHold);
			} else {
				dice_2_imageV.setEffect(colorNotHold);
			}
		}

		if (event.getSource() == button_3) {
			dices[2].setHold();
			if (dices[2].isHold()) {
				dice_3_imageV.setEffect(colorOnHold);
			} else {
				dice_3_imageV.setEffect(colorNotHold);
			}
		}

		if (event.getSource() == button_4) {
			dices[3].setHold();
			if (dices[3].isHold()) {
				dice_4_imageV.setEffect(colorOnHold);
			} else {
				dice_4_imageV.setEffect(colorNotHold);
			}
		}

		if (event.getSource() == button_5) {
			dices[4].setHold();
			if (dices[4].isHold()) {
				dice_5_imageV.setEffect(colorOnHold);
			} else {
				dice_5_imageV.setEffect(colorNotHold);
			}
		}
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
			if (dices[i].isHold() == false) {
				imageView[i].setImage(image[dices[i].getValue() - 1]);
			}
		}

	}// end method setDiceImages

	public void setMainApp(Main main) {
		this.main = main;

	}

}
