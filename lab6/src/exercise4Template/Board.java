package exercise4Template;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 

/**
 * Provide the data fields and methods to create a Java data-type representing a board in a game of tic tac toe.
 * The board stores a 3x3 array that represents itself. It keeps track of the number of markers on the board
 * which is used by the Player object to determine if a game should be stopped.
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since September 28 2020
 */
public class Board implements Constants {
	/**
	 * The character array representation of the board typically containing 3 rows and 3 columns.
	 */
	private char theBoard[][];
	
	/**
	 * The number of markers on the board.
	 */
	private int markCount;
	
	/**
	 * Constructs and initializes a new Board with no markers. The board character array is filled with empty spaces.
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
	
	/**
	 * Returns the marker at (row, col) .
	 * @param row the row number of the marker in the board.
	 * @param col the column number of the marker in the board.
	 * @return the coordinate of the marker
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
	
	/**
	 * Tests if all markers (9) are on the board
	 * @return true if board is full, false otherwise.
	 */
	public boolean isFull() {
		return markCount == 9;
	}
	
	/**
	 * Checks if player with marker X has won.
	 * @return true if player X has won, false otherwise.
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Checks if player with marker O has won.
	 * @return true if player X has won, false otherwise.
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Displays the board onto the console.
	 */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}
	
	/**
	 * Adds a specific marker onto the board at coordinate (row, col) .
	 * @param row the row number of the marker.
	 * @param col the column number of the marker.
	 * @param mark the type of the marker.
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}
	
	/**
	 * Clears the board.
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}
	
	/**
	 * Checks if a given mark has won by evaluating all possible win patterns
	 * @param mark the type of marker being evaluated
	 * @return 1 if mark is the winner, 0 otherwise
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
	/**
	 * Displays the column header on the console.
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println("|");
	}
	/**
	 * Adds hyphens to the board to denote horizontal line boundaries. 
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}
	
	/**
	 * Adds spaces to the board along with vertical line boundaries.
	 */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
