package application.controller;

import application.model.Dice;
import application.model.Yahtzee;

public class FillScoreTabel {

	public final int FULLHOUSE = 25;
	public final int SMALLSTRAIGHT = 30;
	public final int LARGESTRAIGHT = 40;
	public final int YAHTZEE = 50;
	private boolean yahtzeeIsFilled;


	public int getOnes(Dice[] dices){
		return calculateOnesToSixes(dices, 1);
	}

	public int getTwos(Dice[] dices){
		return calculateOnesToSixes(dices, 2);
	}

	public int getThrees(Dice[] dices){
		return calculateOnesToSixes(dices, 3);
	}

	public int getFours(Dice[] dices){
		return calculateOnesToSixes(dices, 4);
	}

	public int getFives(Dice[] dices){
		return calculateOnesToSixes(dices, 5);
	}

	public int getSixes(Dice[] dices){
		return calculateOnesToSixes(dices, 6);
	}

	public int calculateOnesToSixes(Dice[] dices, int diceFace) {
		int total = 0;

		for (int i = 0; i < dices.length; i++) {
			if(dices[i].getValue() == diceFace){
				total += dices[i].getValue();
			}
		}
		return total;
	}

	public int getThreeOfaKind(Dice[] dices) {
		return isThreeOrFourofKind(dices, 3);
	}

	public int getFourOfaKind(Dice[] dices) {
		return isThreeOrFourofKind(dices, 4);
	}

	public int isThreeOrFourofKind(Dice[] dices, int amount) {
		int[] n = new int[6];

		for (int i = 0; i < dices.length; i++) {
			n[dices[i].getValue() - 1] ++;
		}

		int total = 0;

		for (int k = 0; k < n.length; k++) {
			if(n[k] >= amount){
				total = Yahtzee.countAllDices(dices);
				break;
			}
		}

		return total;
	}

	public int getFullHouse(Dice[] dices) {
		if( Yahtzee.isFullHouse(dices) || yahtzeeIsFilled){
			return FULLHOUSE;
		}
		return 0;
	}

	public int getSmallStraight(Dice[] dices) {
		if(Yahtzee.isStraight(dices, 4) || yahtzeeIsFilled){
			return SMALLSTRAIGHT;
		}
		return 0;
	}

	public int getLargeStraight(Dice[] dices) {
		if(Yahtzee.isStraight(dices, 5) || yahtzeeIsFilled){
			return LARGESTRAIGHT;
		}
		return 0;
	}

	public int getYahtzee(Dice[] dices) {
		if(Yahtzee.isSameDices(dices, 5)){
			return YAHTZEE;
		}
		return 0;
	}

	public int getChange(Dice[] dices) {
		return Yahtzee.countAllDices(dices);
	}

	public void setYahtzeeIsFilled(boolean yahtzee){
		yahtzeeIsFilled = true;
	}

}// end class FillScoreTabel
