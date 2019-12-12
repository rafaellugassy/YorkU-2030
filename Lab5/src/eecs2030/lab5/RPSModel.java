package eecs2030.lab5;

import java.util.Random;

/**
 * A model representing the game of rock, paper, scissors. The model is capable
 * of generating random hands for the computer, and comparing a computer hand to
 * the hand played by another player. The model keeps track of the number of
 * rounds played, the number of rounds won by the player, the number of rounds
 * won by the computer, and the number of rounds that resulted in a draw between
 * the computer and player.
 *
 */
public class RPSModel {

	// DO NOT MAKE THESE FIELDS PRIVATE; THE TESTER NEEDS DIRECT
	// DIRECT ACCESS TO THEM
	String player; // the hand played by the player
	String computer; // the hand played by the computer
	Random rng; // a random number generator
	int roundsPlayed; // the number of rounds played
	int roundsWonByPlayer; // the number of rounds won by the player
	int roundsWonByComputer; // the number of rounds won by the computer
	int roundsDrawn; // the number of rounds that resulted in a draw
	private static String[] hand = { RPSUtils.ROCK, RPSUtils.PAPER, RPSUtils.SCISSORS };

	/**
	 * Initializes the model.
	 * 
	 * @param seed
	 *            a seed value to initialize the random number generator used to
	 *            generate hands for the computer
	 */
	public RPSModel(long seed) {
		this.player = "";
		this.computer = "";
		this.rng = new Random(seed);
		this.roundsPlayed = 0;
		this.roundsWonByPlayer = 0;
		this.roundsWonByComputer = 0;
		this.roundsDrawn = 0;
	}

	/**
	 * Play a round of rock, paper, scissors. A hand is randomly drawn for the
	 * computer and compared to the hand played by the player, the result
	 * (player wins, computer wins, hand is drawn) is recorded, and the number
	 * of rounds played is increased by one.
	 * 
	 * @param player
	 *            the hand played by the player; equal to one of RPSUtils.ROCK,
	 *            RPSUtils.PAPER, or RPSUtils.SCISSORS
	 * @throws IllegalArgumentException
	 *             if player is not equal to one of RPSUtils.ROCK,
	 *             RPSUtils.PAPER, or RPSUtils.SCISSORS
	 */
	public void playRound(String player) {
		if (player != hand[0] && player != hand[1] && player != hand[2])
			throw new IllegalArgumentException("invalid input");

		this.computer = RPSModel.hand[rng.nextInt(3)];
		this.player = player;

		if (this.draw())
			this.roundsDrawn++;
		else if (this.playerWins())
			this.roundsWonByPlayer++;
		else if (this.computerWins())
			this.roundsWonByComputer++;
	}

	/**
	 * Returns the hand most recently played by the computer.
	 * 
	 * @return the hand most recently played by the computer; equal to one of
	 *         RPSUtils.ROCK, RPSUtils.PAPER, or RPSUtils.SCISSORS
	 */
	public String getComputerHand() {
		return this.computer;
	}

	/**
	 * Returns <code>true</code> if the most recently played hand resulted in a
	 * draw and <code>false</code> otherwise.
	 * 
	 * @return <code>true</code> if the most recently played hand resulted in a
	 *         draw and <code>false</code> otherwise
	 */
	public boolean draw() {
		return !(this.computerWins() || this.playerWins());
	}

	/**
	 * Returns <code>true</code> if the most recently played hand resulted in a
	 * win for the computer and <code>false</code> otherwise.
	 * 
	 * @return true if the most recently played hand resulted in a win for the
	 *         computer and false otherwise
	 */
	public boolean computerWins() {
		return checkWin(this.computer, this.player);
	}

	/**
	 * Returns <code>true</code> if the most recently played hand resulted in a
	 * win for the player and <code>false</code> otherwise.
	 * 
	 * @return true if the most recently played hand resulted in a win for the
	 *         player and false otherwise
	 */
	public boolean playerWins() {
		return checkWin(this.player, this.computer);
	}

	/**
	 * Returns <code>true</code> if the most recently played hand resulted in a
	 * win for x and <code>false</code> otherwise.
	 * 
	 * @return true if the most recently played hand resulted in a win for the x
	 *         and false otherwise
	 */
	private boolean checkWin(String x, String y) {
		for (int i = 0; i < 3; i++)
			if (x == RPSModel.hand[i] && y == RPSModel.hand[i == 0 ? 2 : i - 1])
				return true;
		return false;
	}

	/**
	 * Returns the total number of rounds played.
	 * 
	 * @return the total number of rounds played
	 */
	public int getRoundsPlayed() {
		return this.roundsPlayed;
	}

	/**
	 * Returns the number of rounds won by the player.
	 * 
	 * @return the number of rounds won by the player
	 */
	public int getRoundsWonByPlayer() {
		return this.roundsWonByPlayer;
	}

	/**
	 * Returns the number of rounds won by the computer.
	 * 
	 * @return the number of rounds won by the computer
	 */
	public int getRoundsWonByComputer() {
		return this.roundsWonByComputer;
	}

	/**
	 * Returns the number of rounds played that resulted in draw.
	 * 
	 * @return the number of rounds played that resulted in draw
	 */
	public int getRoundsDrawn() {
		return this.roundsDrawn;
	}

}
