package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;



public class Yahtzee {
	private final int rounds = 1;
	private final int turns = 3;
	private final int amountDices = 5;
	public final static int FULLHOUSE = 25;
	public final static int SMALLSTRAIGHT = 30;
	public final static int LARGESTRAIGHT = 40;
	public final static int YAHTZEE = 50;
	public final static int YAHTZEEBONUS = 100;

	public Yahtzee() {

	}

	/*
	 * Drie gelijke: De score is het totaal van alle ogen,
	 * als er minstens 3 dobbelstenen met hetzelfde aantal ogen zijn.
	 *
	 * Vier gelijke: De score is het totaal van alle ogen,
	 * als er minstens 4 dobbelstenen met hetzelfde aantal ogen zijn.
	 *
	 * Yahtzee: 50 punten als alle dobbelstenen hetzelfde aantal ogen hebben.
	 */

	/*
	 * Full House: 25 punten voor 3 gelijke en één paar. (5 gelijke telt niet
	 * als Full House, tenzij het vak Yahtzee reeds ingevuld is).
	 */
	public static boolean isFullHouse(Dice[] dice) {

		if(isSameDices(dice, 3) && isSameDices(dice, 2)){
			return true;
		}

		return false;
	}

	/*
	 * Kleine straat: 30 punten voor 4 oplopende dobbelstenen. (de volgorde
	 * speelt geen rol 1234, 2345, 3456 )
	 * Grote straat: 40 punten voor 5 oplopende dobbelstenen. (de volgorde
	 * speelt geen rol)
	 */

	/**
	 *
	 * @param dices
	 * @param streetLenght
	 * @return true if it is a straight
	 */
	public static boolean isStraight(Dice[] dices, int streetLenght) {
		List<Integer> list = new ArrayList<>(dices.length);
		int successive = 0;
		for(int i = 0; i < dices.length; i++){
			list.add((dices[i].getValue()));
		}

		Collections.sort(list);

		for(int i = 0; i < dices.length - 1; i++){
			if(list.get(i + 1) - list.get(i) == 1){
				successive++;
			}
			else if(list.get(i + 1) - list.get(i) == 2 && successive == 1){
				return false;
			}
			else if(list.get(i + 1) - list.get(i) == 2 && successive == 2){
				return false;
			}

		}

		if(successive == (streetLenght)){
			return true;
		}
		if(successive == (streetLenght - 1)){
			return true;
		}

		return false;
	}



	/*
	 *
	 */

	public static int countValueDices(Dice[] dice, int digit) {
		int total = 0;
		for (int i = 0; i < dice.length; i++) {
			if (dice[i].getValue() == digit) {
				total += dice[i].getValue();
			}
		}

		return total;
	}

	/*
	 *
	 */
	public static int countAllDices(Dice[] dice) {
		int total = 0;
		for (int i = 0; i < dice.length; i++) {
			total += dice[i].getValue();
		}

		return total;
	}

	public static boolean isSameDices(Dice[] dice, int amoutOfDices) {
		int[] n = new int[7];// contains dice digits 1 to 6 included

		for (int i = 0; i < dice.length; i++) {
			n[dice[i].getValue()]++;
		}

		for (int j = 1; j < n.length; j++) {
			if (n[j] == amoutOfDices) {
				return true;
			}
		}
		return false;
	}

	public static int amountOfSameDices(Dice[] dice, int amoutOfDices) {
		int[] n = new int[7];// contains dice digits 1 to 6 included

		for (int i = 0; i < dice.length; i++) {
			n[dice[i].getValue()]++;
		}

		int a = n[1];
		for (int i = 2; i < n.length; i++) {
			if(n[i] > a)
				a = n[i];
		}
		return a;
	}

	public static boolean containDiceFace(Dice[] dices, int faceNumber) {
		for (int i = 0; i < dices.length; i++) {
			if(dices[i].getValue() == faceNumber){
				return true;
			}
		}
		
		return false;
	}

}
