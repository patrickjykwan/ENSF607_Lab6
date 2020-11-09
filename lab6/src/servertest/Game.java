package servertest;
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
public class Game implements Constants, Runnable {
	///Here is server code
	private PrintWriter socketOut1;
	private BufferedReader socketIn1;
	private PrintWriter socketOut2;
	private BufferedReader socketIn2;
	
	
	public Game (PrintWriter socketOut1, BufferedReader socketIn1, PrintWriter socketOut2, BufferedReader socketIn2) {
		this.socketOut1 = socketOut1;
		this.socketIn1 = socketIn1;
		this.socketOut2 = socketOut2;
		this.socketIn2 = socketIn2;
		theBoard  = new Board();
	}
	
	///
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//At this point all your sockets should be assigned
		//All you need to do is the following
		
		
		
		
		
		
		
		//Player 1
		
		
		try {
			
			Referee theRef;
			Player xPlayer, oPlayer;
			String name;
			
			//Accept names and create your players.
			
			socketOut1.println("Please enter the name of the \'X\' player: \n");
			name = this.socketIn1.readLine();
			xPlayer = new Player(name, LETTER_X);
			xPlayer.setBoard(this.theBoard);
			
			socketOut2.println("Please enter the name of the \\'O\\' player: \n");
			name = this.socketIn2.readLine();
			oPlayer = new Player(name, LETTER_O);
			oPlayer.setBoard(this.theBoard);
			
			theRef = new Referee();
			theRef.setBoard(this.theBoard);
			theRef.setoPlayer(oPlayer);
			theRef.setxPlayer(xPlayer);
	        
	        this.appointReferee(theRef);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		//Create 
		
	}
	

}
