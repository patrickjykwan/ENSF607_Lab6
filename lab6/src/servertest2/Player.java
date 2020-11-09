package servertest2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Provide the data fields and methods to create a Java data-type representing a player in a game of tic tac toe.
 * The Player stores their name, the board that the game is being played on, its opposing player (opponent) and the
 * play piece that it is using. 
 * <p>
 * Player plays the game by making moves, placing markers in an appropriate coordinate point on the board.
 * The player also determines whether or not their opponent has won or if they have tied.
 * <p>
 * New functionality for lab 6 is to make Player serializable such that it can be transfered
 * between the client and the server.
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since September 28 2020
 */
public class Player implements Serializable{
	/**
	 * The player's name.
	 */
	private String name;
	
	/**
	 * The typically 3x3 board (play area) that the game is being played on.
	 */
	private Board board;
	
	/**
	 * The opposing player that this player is competing against.
	 */
	private Player opponent;
	
	/**
	 * The play piece that the player is using, typically either X or O
	 */
	private char mark;
	
	/**
	 * Constructs the player with a given name and mark.
	 * @param name the name of the player.
	 * @param mark the play piece of the player.
	 */
	public Player(String name, char mark){
		this.name = name;
		this.mark = mark;
	}
	
	/**
	 * Calls makeMove() as long as the game is not over.
	 * Displays the board after each move.
	 * If game is over it announces who the winner is.
	 */
	//was void
	public boolean play() {
		boolean gameContinue= false;
		board.display();
		
		if(board.xWins() == true) {
			if(this.getMark() == 'X') {
				System.out.println(this.getName()+" is the winner!");
			}else
				System.out.println(opponent.getName()+" is the winner!");
		}else if(board.oWins() == true) {
			if(this.getMark() == 'O') {
				System.out.println(this.getName()+" is the winner!");
			}else
				System.out.println(opponent.getName()+" is the winner!");
		}else if(board.isFull() == true) {
			System.out.println("Tie");
		}else {
			
			this.makeMove();
			board.display();
			gameContinue = true;
			
		}
		
		
			
		/*
		if(board.isFull()==false && board.oWins()==false && board.xWins()==false) {
			this.makeMove();
			
		}else {
			System.out.print("THE GAME IS OVER: ");
			if(board.xWins()==true || board.oWins()==true)
				System.out.println(opponent.getName()+" is the winner!");
			else
				System.out.println("It is a tie!");
			
			//System.exit(0);	
		}
		*/
		
		
		
		return gameContinue;
	}
	/**
	 * Asks the player to make a move by entering the row and column number and puts X or O marker on the board
	 * by calling method addMark in the class Board. An invalid input results in the turn being skipped.
	 * That would be either choosing rows or columns out of the domain {0,1,2} or by selecting a coordinate
	 * thats already occupied by a play piece.
	 */
	public void makeMove() {
		Scanner scan = new Scanner(System.in);
		System.out.print("\n"+this.name + ", what row should your next "+this.getMark()+" be placed in? ");
		int row= scan.nextInt();
		if(row>2||row<0) {
			System.out.println("Invalid domain for row, turn is skipped.");
			return;
		}
			
		
		System.out.print("\n"+ this.name + ", what col should your next "+this.getMark()+" be placed in? ");
		int col= scan.nextInt();
		if(col>2||col<0) {
			System.out.println("Invalid domain for col, turn is skipped.");
			return;
		}
		
		if(board.getMark(row, col)==this.getMark() ||board.getMark(row, col)==opponent.getMark() ) {
			System.out.println("Invalid play, cannot replace a set marker, turn is skipped.");
		}else
		board.addMark(row, col, this.mark);
		
		
	}
	
	
	/**
	 * Gets the name of the player object.
	 * @return name	of the player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the player object.
	 * @param name is the name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the board that the game is being played on.
	 * @return board the play area of the game.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Sets the board that the game is being played on.
	 * @param board	the play area of the game.
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Get the opponent of this player object.
	 * @return opponent the player object that is playing in opposition to this player.
	 */
	public Player getOpponent() {
		return opponent;
	}
	/**
	 * Sets the opponent of this player object.
	 * @param opponent the player object that is playing in opposition to this player.
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}
	
	/**
	 * Gets the play piece/ marker that this player is using.
	 * @return marker the play piece/marker this player is using.
	 */
	public char getMark() {
		return mark;
	}

}
