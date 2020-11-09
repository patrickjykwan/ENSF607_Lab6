package servertest;

/**
 * Provides data fields and methods to create a Java data-type, representing a referee in a game of tic tac toe.
 * A referee selects the opponents for each player, starts the game and managing turns. 
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since September 28 2020
 *
 */
public class Referee {
	/**
	 * Player that uses the marker X and also starts first in every game.
	 */
	
	private Player xPlayer;
	
	/**
	 * Player that uses the marker O and also starts second in every game.
	 */
	private Player oPlayer;
	
	/**
	 * The typically 3x3 board (play area) that the game is being played on.
	 */
	private Board board;
	
	/**
	 * Basic constructor for Referee, sets xPlayer, oPlayer and Board to null.
	 */
	public Referee() {
		xPlayer= null;
		oPlayer = null;
		board = null;
	}
	
	/**
	 * This method sets the player's opponents, starts the game, does the initial display of the board and
	 * manages turn order.
	 */
	public void runTheGame() {
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		
		System.out.println("\nReferee started the game...");
		board.display();
		
		while(true) {
		xPlayer.play();
		oPlayer.play();
		}
		
	}

	/**
	 * Gets the xPlayer object
	 * @return xPlayer from the Referee object.
	 */
	public Player getxPlayer() {
		return xPlayer;
	}

	/**
	 * Sets the xPlayer for the game
	 * @param xPlayer the player that is being set to Referee's xPlayer
	 */
	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}

	/**
	 * Gets the oPlayer object.
	 * @return oPlayer from the Referee object.
	 */
	public Player getoPlayer() {
		return oPlayer;
	}
	
	/**
	 * Sets the oPlayer object.
	 * @param oPlayer the player that is being set to Referee's oPlayer.
	 */
	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}
	
	/**
	 * Gets the board that the game is being played on.
	 * @return board the board being played on.
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Sets the board that the game is being played on for the Referee object.
	 * @param board	the board being played on.
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

}
