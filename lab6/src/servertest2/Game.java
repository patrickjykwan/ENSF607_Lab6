package servertest2;
import java.io.*;
import java.net.Socket;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 
/**
 * Provides the data fields and methods to host a game of tic tac toe. The game has the board the the game is being
 * played on along with a referee who starts the game and determines the play order of the players. Game is
 * responsible for setting up the game starting with the board, than its referee, than its players. 
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since September 28 2020
 */
public class Game implements Constants, Runnable {
	/**
	 * The typically 3x3 board/play area that the game is played on.
	 */
	private Board theBoard;
	
	/**
	 * The object referee that determines player's opponent, starts the game, determines play order.
	 */
	private Referee theRef;
	
	/**
	 * The socket for player 1
	 */
	private Socket aSocket;
	/**
	 * The socket for player 2
	 */
	private Socket bSocket;
	
	
	/**
	 * This is the constructor method for Game. It initiates a Board object for the game to be played on
	 */
    public Game( ) {
        theBoard  = new Board();
	}
    
    /**
     * 
     * @param aSocket
     * @param bSocket
     */
    public Game(Socket aSocket, Socket bSocket) {
		// TODO Auto-generated constructor stub
    	theBoard  = new Board();
    	this.aSocket = aSocket;
    	this.bSocket = bSocket;
    	
	}


	/**
     * Assigns a referee object to the Game's theRef attribute.
     * @param r is the referee object to be assigned to the Game's theRef attribute.
     * @throws IOException when the user inputs an invalid player name.
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }
	
    /**
     * Implements the run() from runnable interface. Creates all necessary objects and assigns them to
     * referee.
     */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Referee theRef;
		Player xPlayer, oPlayer;
		String name = "Player1";
		
		xPlayer = new Player(name, LETTER_X);
		xPlayer.setBoard(this.theBoard);
		
		
		name = "Player2";
		oPlayer = new Player(name, LETTER_O);
		oPlayer.setBoard(this.theBoard);
		
		theRef = new Referee(aSocket,bSocket);
		
		
		theRef.setBoard(this.theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
		
		try {

			this.appointReferee(theRef);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Game is over");
		
	}
	

}
