package exercise4Template;
import java.io.*;

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
public class Game implements Constants {
	/**
	 * The typically 3x3 board/play area that the game is played on.
	 */
	private Board theBoard;
	
	/**
	 * The object referee that determines player's opponent, starts the game, determines play order.
	 */
	private Referee theRef;
	
	/**
	 * This is the constructor method for Game. It initiates a Board object for the game to be played on
	 */
    public Game( ) {
        theBoard  = new Board();
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
     * This is the main method.
     * @param args the name of the program.
     * @throws IOException when the user inputs an invalid player name.
     */
	public static void main(String[] args) throws IOException {
		Referee theRef;
		Player xPlayer, oPlayer;
		BufferedReader stdin;
		Game theGame = new Game();
		stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nPlease enter the name of the \'X\' player: ");
		String name= stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		xPlayer = new Player(name, LETTER_X);
		xPlayer.setBoard(theGame.theBoard);
		
		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}
		
		oPlayer = new Player(name, LETTER_O);
		oPlayer.setBoard(theGame.theBoard);
		
		theRef = new Referee();
		theRef.setBoard(theGame.theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
        
        theGame.appointReferee(theRef);
	}
	

}
