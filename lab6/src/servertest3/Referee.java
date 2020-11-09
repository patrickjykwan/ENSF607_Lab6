package servertest3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	 * The socket for player 1
	 */
	private Socket aSocket;
	/**
	 * The socket for player 2
	 */
	private Socket bSocket;
	
	
	/**
	 * List of sockets
	 * 
	 */
	
	
	/**
	 * Basic constructor for Referee, sets xPlayer, oPlayer and Board to null.
	 */
	public Referee() {
		xPlayer= null;
		oPlayer = null;
		board = null;
	}
	
	/**
	 * 
	 */
	public Referee(Socket aSocket, Socket bSocket) {
		xPlayer= null;
		oPlayer = null;
		board = null;
		this.aSocket= aSocket;
		this.bSocket = bSocket;
	}
	
	/**
	 * This method sets the player's opponents, starts the game, does the initial display of the board and
	 * manages turn order.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void runTheGame()  {
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		
		System.out.println("\nReferee started the game...");
		board.display();
		
		try {
			System.out.println("\nCreating Streams for game");
			ObjectOutputStream	bObjectOutput = new ObjectOutputStream(bSocket.getOutputStream());
			ObjectInputStream	bObjectInput = new ObjectInputStream(bSocket.getInputStream());
			ObjectOutputStream	aObjectOutput = new ObjectOutputStream(aSocket.getOutputStream());
			ObjectInputStream	aObjectInput = new ObjectInputStream(aSocket.getInputStream());
			System.out.println("\nDone creating Streams for game");
			
			boolean gameContinue = true;
			
			//while(true) {
			while(true) {
			System.out.println("\nEntering While loop for game");
			
			System.out.println("Writing to Player 1");
			aObjectOutput.writeObject(xPlayer);
			
			System.out.println("Reading to Player 1");
			xPlayer = (Player) aObjectInput.readObject();
			
			//set player oponnent again??
			oPlayer.setBoard(xPlayer.getBoard());
			this.setBoard(xPlayer.getBoard());
			
			System.out.println("Writing to Player 2");
			bObjectOutput.writeObject(oPlayer);
			
			System.out.println("Reading to Player 2");
			oPlayer = (Player) bObjectInput.readObject();
			
			xPlayer.setBoard(oPlayer.getBoard());
			this.setBoard(oPlayer.getBoard());
			//set player oponnent again??
			//xPlayer.play();
			//oPlayer.play();
			
			if(gameContinue==false) {
				//at this point the winning board should be shared with all players
				//meaning we can safely exit this loop.
				break;
			}
			//Here we need to some how 
			if(board.oWins()||board.xWins()||board.isFull()) {
				System.out.println("Referee Says End Game");
				gameContinue=false;
				//Technically after we leave the try catch all the streams should no longer exist
				
			}
			}
		}catch(IOException e){
			System.out.println("IOException error");
			
		}catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException error");
		}
		
		//Close socket
		/*
		try {
			aSocket.close();
			bSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
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
