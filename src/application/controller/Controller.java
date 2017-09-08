package application.controller;

import java.util.Optional;

import application.Main;
import application.model.Dice;
import application.model.Yahtzee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	private GridPane gridcolumn1, gridcolumn2;
	@FXML
	private Label numberofthrowsLabel;
	@FXML
	private Label numberofroundsLabel;
	@FXML
	private Label numberofplayerLabel;


	private int numberofrounds = 13;
	private int numberofthrows = 0;
	private int player = 1;
	private int totalFaces = 0;
	private int bonus = 35;
	private int yahtzeeBonus = 100;
	private int amountYahtzeeBonuses = 0;
	private int yahtzee = 0;
	private int totalDiceComb;
	private Main main;
	private Dice[] dices = new Dice[5];
	private ImageView[] imageView;
	private ColorAdjust colorOnHold;
	private ColorAdjust colorNotHold;
	FillScoreTabel fillScoreTabel;
	private boolean isSaved = false;
	private Alert alert;
	private Optional<ButtonType> result;

	public void start() {
		fillScoreTabel = new FillScoreTabel();
		numberofroundsLabel.setText("" + numberofrounds);
		numberofthrowsLabel.setText("" + numberofthrows);
		numberofplayerLabel.setText("" + player);
		colorOnHold = new ColorAdjust();
		colorNotHold = new ColorAdjust();
		imageView = new ImageView[5];
		imageView[0] = dice_1_imageV;
		imageView[1] = dice_2_imageV;
		imageView[2] = dice_3_imageV;
		imageView[3] = dice_4_imageV;
		imageView[4] = dice_5_imageV;
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setContentText("This will give you 0 points.\nAre you sure?");

		for (int i = 0; i < dices.length; i++) {
			dices[i] = new Dice();
		}

		for (int i = 0; i < imageView.length; i++) {
			imageView[i].setImage(new Image("file:resources/images/dice.png"));
		}

	}

	@FXML
	private void saveValue(MouseEvent event) {
		if(isSaved == false){

			if (event.getSource() == onesTxArea) {
				setOneToSixTxAreas(onesTxArea, 1);
			}
			else if (event.getSource() == twosTxArea) {
				setOneToSixTxAreas(twosTxArea, 2);
			}
			else if (event.getSource() == threesTxArea) {
				setOneToSixTxAreas(threesTxArea, 3);
			}
			else if (event.getSource() == foursTxArea) {
				setOneToSixTxAreas(foursTxArea, 4);
			}
			else if (event.getSource() == fivesTxArea) {
				setOneToSixTxAreas(fivesTxArea, 5);
			}
			else if (event.getSource() == sixesTxArea) {
				setOneToSixTxAreas(sixesTxArea, 6);
			}

			if(totalFaces != 0){
				totalScoreTxArea.setText("" + totalFaces);
			}

			if(totalFaces >= 63){
				bonusTxArea.setText("" + bonus);
				if(numberofrounds == 0){
					totalFaces += bonus;
				}
			}

			if(totalFaces != 0){
				totalFacesTxArea.setText("" + totalFaces);
			}


			if (event.getSource() == threeOfAKindTxArea) {
				threeFourOfaKind(threeOfAKindTxArea, 3);
			}
			else if(event.getSource() == fourOfAKindTxArea){
				threeFourOfaKind(fourOfAKindTxArea, 4);
			}
			else if(event.getSource() == fullHouseTxArea){
				if(fullHouseTxArea.getText().contains("0") == false){
					Label label = new Label();
					int index = gridcolumn2.getChildren().indexOf(fullHouseTxArea);
					gridcolumn2.getChildren().remove(index);
					gridcolumn2.add(label, 1, 2);
					label.setText("" + 25);
					totalDiceComb += 25;
					numberofthrows = 0;
					numberofthrowsLabel.setText("" + numberofthrows);
					reset();
					isSaved = true;
				}
				else{
					result = alert.showAndWait();
					if(result.get() == ButtonType.OK){
						Label label = new Label();
						int index = gridcolumn2.getChildren().indexOf(fullHouseTxArea);
						gridcolumn2.getChildren().remove(index);
						gridcolumn2.add(label, 1, 2);
						label.setText("" + 0);
						totalDiceComb += 0;
						numberofthrows = 0;
						numberofthrowsLabel.setText("" + numberofthrows);
						reset();
						isSaved = true;
					}
				}
			}
			else if(event.getSource() == smStraightTxArea){
				if(smStraightTxArea.getText().contains("0") == false){
					Label label = new Label();
					int index = gridcolumn2.getChildren().indexOf(smStraightTxArea);
					gridcolumn2.getChildren().remove(index);
					gridcolumn2.add(label, 1, 3);
					label.setText("" + 30);
					totalDiceComb += 30;
					numberofthrows = 0;
					numberofthrowsLabel.setText("" + numberofthrows);
					reset();
					isSaved = true;
				}
				else{
					result = alert.showAndWait();
					if(result.get() == ButtonType.OK){
						Label label = new Label();
						int index = gridcolumn2.getChildren().indexOf(smStraightTxArea);
						gridcolumn2.getChildren().remove(index);
						gridcolumn2.add(label, 1, 3);
						label.setText("" + 0);
						totalDiceComb += 0;
						numberofthrows = 0;
						numberofthrowsLabel.setText("" + numberofthrows);
						reset();
						isSaved = true;
					}
				}
			}
			else if(event.getSource() == lgStraightTxArea){
				if(lgStraightTxArea.getText().contains("0") == false){
					Label label = new Label();
					int index = gridcolumn2.getChildren().indexOf(lgStraightTxArea);
					gridcolumn2.getChildren().remove(index);
					gridcolumn2.add(label, 1, 4);
					label.setText("" + 40);
					totalDiceComb += 40;
					numberofthrows = 0;
					numberofthrowsLabel.setText("" + numberofthrows);
					reset();
					isSaved = true;
				}
				else{
					result = alert.showAndWait();
					if(result.get() == ButtonType.OK){
						Label label = new Label();
						int index = gridcolumn2.getChildren().indexOf(lgStraightTxArea);
						gridcolumn2.getChildren().remove(index);
						gridcolumn2.add(label, 1, 4);
						label.setText("" + 0);
						totalDiceComb += 0;
						numberofthrows = 0;
						numberofthrowsLabel.setText("" + numberofthrows);
						reset();
						isSaved = true;
					}
				}
			}
			else if(event.getSource() == yahtzeeTxArea){
				if(yahtzeeTxArea.getText().contains("0") == false){
					if(yahtzee >= 1){
						amountYahtzeeBonuses += 100;
						yahtzeeBonusTxArea.setText("" + amountYahtzeeBonuses);
						totalDiceComb += yahtzeeBonus;
					}
					if(yahtzee < 1){
						totalDiceComb += 50;
					}
					numberofthrows = 0;
					numberofthrowsLabel.setText("" + numberofthrows);
					reset();
					yahtzee++;
					isSaved = true;
				}
				else{
					result = alert.showAndWait();
					if(result.get() == ButtonType.OK){
						numberofthrows = 0;
						numberofthrowsLabel.setText("" + numberofthrows);
						reset();
						yahtzee++;
						isSaved = true;
					}
				}
			}
			else if(event.getSource() == chanceTxArea){
				int value = Yahtzee.countAllDices(dices);
				Label label = new Label();
				int index = gridcolumn2.getChildren().indexOf(chanceTxArea);
				gridcolumn2.getChildren().remove(index);
				gridcolumn2.add(label, 1, 6);
				label.setText("" + value);
				totalDiceComb += value;
				numberofthrows = 0;
				numberofthrowsLabel.setText("" + numberofthrows);
				reset();
				isSaved = true;
			}

			if(totalDiceComb != 0){
				totalDiceCombTxArea.setText("" + totalDiceComb);
			}

			numberofrounds--;
			numberofroundsLabel.setText("" + numberofrounds);

			if(numberofrounds == 0){
				grandTotalTxArea.setText("" + (totalFaces + totalDiceComb));
			}
		}
	}

	public void threeFourOfaKind(TextArea textArea, int numberOfKind) {
		if (Yahtzee.amountOfSameDices(dices, numberOfKind) >= numberOfKind) {
			int value = Yahtzee.countAllDices(dices);
			Label label = new Label();
			int index = gridcolumn2.getChildren().indexOf(textArea);
			gridcolumn2.getChildren().remove(index);
			gridcolumn2.add(label, 1, numberOfKind - 3);
			label.setText("" + value);
			totalDiceComb += value;
			numberofthrows = 0;
			numberofthrowsLabel.setText("" + numberofthrows);
			reset();
			isSaved = true;
		}
	}

	public void setOneToSixTxAreas(TextArea txArea, int diceFace) {
		int value = Yahtzee.countValueDices(dices, diceFace);

		Label label = new Label();
		int index = gridcolumn1.getChildren().indexOf(txArea);
		gridcolumn1.getChildren().remove(index);
		gridcolumn1.add(label, 1, diceFace - 1);
		label.setText("" + value);

		totalFaces += value;
		numberofthrows = 0;
		numberofthrowsLabel.setText("" + numberofthrows);
		reset();
		isSaved = true;
	}

	@FXML
	private void rollDices_click() {
		isSaved = false;
		if (numberofthrows < 3) {
			for (int i = 0; i < dices.length; i++) {
				dices[i].roll();
			}
			numberofthrows++;
			numberofthrowsLabel.setText(numberofthrows + "");

			setValuesInTextAreas();
			setDiceImages();

		}
	}

	public void setValuesInTextAreas() {
		onesTxArea.setText("" + fillScoreTabel.getOnes(dices));
		twosTxArea.setText("" + fillScoreTabel.getTwos(dices));
		threesTxArea.setText("" + fillScoreTabel.getThrees(dices));
		foursTxArea.setText("" + fillScoreTabel.getFours(dices));
		fivesTxArea.setText("" + fillScoreTabel.getFives(dices));
		sixesTxArea.setText("" + fillScoreTabel.getSixes(dices));
		threeOfAKindTxArea.setText("" + fillScoreTabel.getThreeOfaKind(dices));
		fourOfAKindTxArea.setText("" + fillScoreTabel.getFourOfaKind(dices));
		fullHouseTxArea.setText("" + fillScoreTabel.getFullHouse(dices));
		smStraightTxArea.setText("" + fillScoreTabel.getSmallStraight(dices));
		lgStraightTxArea.setText("" + fillScoreTabel.getLargeStraight(dices));
		yahtzeeTxArea.setText("" + fillScoreTabel.getYahtzee(dices));
		chanceTxArea.setText("" + fillScoreTabel.getChange(dices));
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

		onesTxArea.setText("");
		twosTxArea.setText("");
		threesTxArea.setText("");
		foursTxArea.setText("");
		fivesTxArea.setText("");
		sixesTxArea.setText("");
		threeOfAKindTxArea.setText("");
		fourOfAKindTxArea.setText("");
		fullHouseTxArea.setText("");
		smStraightTxArea.setText("");
		lgStraightTxArea.setText("");
		yahtzeeTxArea.setText("");
		chanceTxArea.setText("");

	}

	@FXML
	private void dice_click(ActionEvent event) {

		if (event.getSource() == button_1) {
			setColorHold(0);
		}
		else if (event.getSource() == button_2) {
			setColorHold(1);
		}
		else if (event.getSource() == button_3) {
			setColorHold(2);
		}
		else if (event.getSource() == button_4) {
			setColorHold(3);
		}
		else if (event.getSource() == button_5) {
			setColorHold(4);
		}
	}

	public void setColorHold(int buttonNumber) {
		colorOnHold.setBrightness(-0.5);
		colorNotHold.setBrightness(0.0);

		dices[buttonNumber].setHold();
		if (dices[buttonNumber].isHold()) {
			imageView[buttonNumber].setEffect(colorOnHold);
		} else {
			imageView[buttonNumber].setEffect(colorNotHold);
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
