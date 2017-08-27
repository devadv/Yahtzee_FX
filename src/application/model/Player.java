package application.model;

public class Player {

	private String name;
	private ScoreFormYahtzee form;

	public Player(String name) {
		this.name = name;
		this.form = new ScoreFormYahtzee(this);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void displayScoreForm() {
		form.printScoreForm();
	}

	public ScoreFormYahtzee getScoreForm() {
		return form;
	}
}