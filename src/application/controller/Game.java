package application.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	private Dice[] dices;
	private int rounds;
	private Player[] player;
	private boolean repeat;
	private int numberOfthrows;
	private Yahtzee yahtzee;
	private boolean tries = true;
	private boolean yahtzeeBonus = false;

	public Game(int players, int rounds) {
		this.rounds = rounds;
		this.dices = new Dice[5];
		for (int i = 0; i < dices.length; i++) {
			dices[i] = new Dice();
		}

		this.player = new Player[players];
		Scanner keyboard = new Scanner(System.in);

		for (int j = 0; j < players; j++) {
			System.out.print("Enter name of player " + (j + 1) + ": ");
			String name = keyboard.nextLine();
			player[j] = new Player(name);
		}
		yahtzee = new Yahtzee();
	}

	public void start() {

		for (int p = 0; p < player.length; p++) {
			System.out.println("The players are: " + player[p].getName());
		}

		for (int i = rounds; i > 0; i--) {
			for (int j = 0; j < player.length; j++) {
				playerTurn(player[j]);
			}
		}

		for (int j = 0; j < player.length; j++) {
			player[j].getScoreForm().printEndResult();
		}
	}

	public void playerTurn(Player player) {

		System.out.println();
		System.out.println("It is " + player.getName() + "'s turn");
		System.out.println("type dice number to hold/onhold");
		System.out.println("type 7 to hold all dices");
		System.out.println("zero to roll dices");
		System.out.println("and 10 to end turn");
		roll();
		numberOfthrows = 1;
		printDicesValue();
		repeat = true;
		while (repeat) {
			try {
				holdDices(player);
			} catch (InputMismatchException ex) {
				System.out.println("Only numbers form 0 to 7 and 10 are allowed");
				playerTurn(player);
			}
		}
		yahtzeeBonus = false;
	}

	public void holdDices(Player player) throws InputMismatchException {
		Scanner keyboard = new Scanner(System.in);
		repeat = true;
		int numberofDice = keyboard.nextInt();
		keyboard.nextLine();

		if(numberOfthrows == 3){
			numberofDice = 10;
		}

		switch (numberofDice) {
		case 1:
			dices[0].setHold();
			printDicesValue();
			break;
		case 2:
			dices[1].setHold();
			printDicesValue();
			break;
		case 3:
			dices[2].setHold();
			printDicesValue();
			break;
		case 4:
			dices[3].setHold();
			printDicesValue();
			break;
		case 5:
			dices[4].setHold();
			printDicesValue();
			break;
		case 0:// roll
			if(numberOfthrows < 3){
				roll();
				numberOfthrows++;
				System.out.println("Throw " + numberOfthrows + " of "  + player.getName());
				printDicesValue();
			}else{
				System.out.println("End of turn: you have to choose 10 to end turn");
			}

			break;
		case 7:// hold all dices
			for (int i = 0; i < dices.length; i++) {
				if (!dices[i].isHold()) {
					dices[i].setHold();
				}
			}
			printDicesValue();
			break;
		case 10:
			try {
				chooseScore(player);
			} catch (InputMismatchException e) {
				System.out.println("Only number from 1 to 13 are allowed.");
				executeScore(player, 10);
			}

			//player.displayScoreForm();
			reset();
			repeat = false;
			break;
		}

	}

	public void roll() {
		for (int i = 0; i < dices.length; i++) {
			dices[i].roll();
		}
	}

	public void printDicesValue() {
		for (int i = 0; i < dices.length; i++) {
			System.out.printf("Dice %d. %10d %10s\n", i + 1,
					dices[i].getValue(), dices[i].isHold() ? "Hold" : "");
		}

	}

	public void reset() {
		for (int i = 0; i < dices.length; i++) {
			if (dices[i].isHold()) {
				dices[i].setHold();
			}
		}
	}

	public void chooseScore(Player player) {
		player.displayScoreForm();
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Choose score: ");
		int score = keyboard.nextInt();
		executeScore(player, score);
	}

	public void executeScore(Player player, int score) throws InputMismatchException {
		//player.displayScoreForm();

		switch (score) {
		case 1:
			addScore(player, score);
			break;
		case 2:
			addScore(player, score);
			break;
		case 3:
			addScore(player, score);
			break;
		case 4:
			addScore(player, score);
			break;
		case 5:
			addScore(player, score);
			break;
		case 6:
			addScore(player, score);
			break;
		case 7:// Three of a kind
			if(yahtzee.isSameDices(dices, 3) && yahtzeeBonus == false){
				player.getScoreForm().addScore(yahtzee.countAllDices(dices),score);
			}
			else if(yahtzee.isSameDices(dices, 3) && yahtzeeBonus){
				System.out.println("You can not fill the yahtzee in three of a kind.");
			}
			else{
				System.out.println("This is not ThreeOfKind choose again.");
				if(tries){
					chooseScore(player);
				}
			}
			break;
		case 8:// Four of a kind
			if(yahtzee.isSameDices(dices, 4) && yahtzeeBonus == false){
				player.getScoreForm().addScore(yahtzee.countAllDices(dices),score);
			}
			else if(yahtzee.isSameDices(dices, 4) && yahtzeeBonus){
				System.out.println("Can not fill the yahtzee in four of kind.");
			}
			else{
				System.out.println("This is not FourOfKind choose again.");
				if(tries){
					chooseScore(player);
				}
			}
			break;
		case 9:
			if(yahtzee.isFullHouse(dices)){
				player.getScoreForm().addScore(Yahtzee.FULLHOUSE, 9);
			}
			else if(yahtzee.isSameDices(dices, 5) && yahtzeeBonus){
				if(player.getScoreForm().isOneToSevenFilled(dices)){
					player.getScoreForm().addScore(Yahtzee.FULLHOUSE, 9);
				}
				else if(player.getScoreForm().getScorePositon(dices) == 0){
					System.out.println("Can not fill full house because compartment: " +
							dices[0].getValue() + " is not filled.");
				}
			}
			else if(yahtzee.isSameDices(dices, 5) && player.getScoreForm().isYahtzeeFilled()){
				player.getScoreForm().addScore(Yahtzee.FULLHOUSE, 9);
			}
			else{
				System.out.println("This is not Fullhouse choose again.");
				if(tries){
					chooseScore(player);
				}
			}
			break;
		case 10:
			if(yahtzee.isStraight(dices, 4) && yahtzeeBonus == false){
				player.getScoreForm().addScore(Yahtzee.SMALLSTRAIGHT, 10);
			}
			else if(yahtzee.isStraight(dices, 4) && yahtzeeBonus){
				if(player.getScoreForm().isOneToSevenFilled(dices)){
					player.getScoreForm().addScore(Yahtzee.SMALLSTRAIGHT, 10);
				}
				else if(player.getScoreForm().getScorePositon(dices) == 0){
					System.out.println("Can not fill small straight because compartment: " +
							dices[0].getValue() + " is not filled.");
				}
			}
			else{
				System.out.println("This is not Small Straight choose again.");
				if(tries){
					chooseScore(player);
				}
			}
			break;
		case 11:
			if(yahtzee.isStraight(dices, 5) && yahtzeeBonus == false){
				player.getScoreForm().addScore(Yahtzee.LARGESTRAIGHT, 11);
			}
			else if(yahtzee.isStraight(dices, 5) && yahtzeeBonus){
				if(player.getScoreForm().isOneToSevenFilled(dices)){
					player.getScoreForm().addScore(Yahtzee.LARGESTRAIGHT, 11);
				}
				else if(player.getScoreForm().getScorePositon(dices) == 0){
					System.out.println("Can not fill large straight because compartment: " +
							dices[0].getValue() + " is not filled.");
				}
			}
			else{
				System.out.println("This is not Large Straight choose again.");
				if(tries){
					chooseScore(player);
				}
			}
			break;
		case 12:
			if(yahtzee.isSameDices(dices, 5)){
				if(player.getScoreForm().isYahtzeeFilled() == false){
					player.getScoreForm().addScore(Yahtzee.YAHTZEE, 12);
				}
				else{
					player.getScoreForm().addScore(Yahtzee.YAHTZEEBONUS, 12);
					yahtzeeBonus = true;
					System.out.println("Fill the score also in small straight or large straight or full house or compartment " +
							dices[0].getValue() + " .");
					if(tries){
						chooseScore(player);
					}
				}
			}
			else{
				System.out.println("This is not Yahtzee choose again.");
				if(tries){
					chooseScore(player);
				}
			}
			break;
		case 13:
			if(player.getScoreForm().canAddScore(yahtzee.countAllDices(dices), 13)){
				player.getScoreForm().addScore(yahtzee.countAllDices(dices), 13);
			}

			break;
		default:
			System.out.println("Only number from 1 to 13 are allowed.");
			if(tries){
				chooseScore(player);
			}
		}
		player.displayScoreForm();
	}

	public void addScore(Player player,int score) {
		if( player.getScoreForm().canAddScore(yahtzee.countValueDices(dices, score), score) ){
			player.getScoreForm().addScore(yahtzee.countValueDices(dices, score), score);
		}
		else{
			System.out.println("Position in score form is filled choose again.");
			if(tries){
				chooseScore(player);
			}
		}
	}

	public void setDices(Dice[] dices) {
		this.dices = dices;
	}

	public void testGameClass(boolean tries) {
		this.tries = tries;
	}

	public int getNumberOfThrows() {
		return numberOfthrows;
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter number of players: ");
		int nPlayers = keyboard.nextInt();
		Game game = new Game(nPlayers, 2);
		game.start();

	}

}