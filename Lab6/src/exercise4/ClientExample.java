package exercise4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Provides data fields and methods to create a Java client that communicates with a server to play a game of tic tac toe
 * with another person.
 * @author Patrick Kwan
 * @version 1.0
 * @since November 9, 2020
 *
 */
public class ClientExample {
	
	/**
	 * This socket is the accepted socket that has the connection with the server
	 */
	private Socket aSocket;
	
	/**
	 * This is the buffer used to communicate between the user and the client via a keyboard
	 */
	private BufferedReader stdIn;	
	
	/**
	 * This is the object input stream used to read the player objects being pass to the client
	 * from the server.
	 */
	private ObjectInputStream objectInput;
	
	/**
	 * This is the object output stream used to write the player objects from the client to the server.
	 */
	private ObjectOutputStream objectOutput;
	
	/**
	 * This is the player object that the client uses to make moves.
	 */
	private Player myPlayer;

	
	/**
	 * This constructor creates a client that connects to a server with a given name and port number.
	 * It also instantiates the object streams using the accepted socket.
	 * @param serverName the name of the server.
	 * @param portNumber the port number of the server.
	 */
	public ClientExample (String serverName, int portNumber) {
		
		try {
			aSocket = new Socket (serverName, portNumber);
			//keyboard input stream
			stdIn = new BufferedReader (new InputStreamReader (System.in));
			//object streams
			objectInput = new ObjectInputStream(aSocket.getInputStream());
			objectOutput = new ObjectOutputStream(aSocket.getOutputStream());
			
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method is used to communicate between the client and the server. This typically happens after
	 * the client asks for the user's name. In communicate, the client accepts the player object from the 
	 * server that represents the player that is being used by the user. The client than plays a turn then 
	 * sends that player object back to the server.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void communicate() throws IOException, ClassNotFoundException {
		

		System.out.println("Waiting for an opponent to appear");
		boolean gameContinue = true; 
		while(gameContinue) {

			myPlayer = (Player) objectInput.readObject();
			gameContinue = myPlayer.play();
			objectOutput.writeObject(myPlayer);
			System.out.println("\n\n\n");
			
		}
		System.out.println("Game is over");
		
		
		
	}
	
	/**
	 * This method is used to set the player's name. It does so by accepting the player object created by
	 * the server for the user of this client then change the players name in the player object and 
	 * send it back to the server.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void askPlayerName() throws IOException, ClassNotFoundException{
		myPlayer = (Player) objectInput.readObject();
		System.out.println("Enter Name");
		myPlayer.setName(stdIn.readLine());	
		objectOutput.writeObject(myPlayer);
	}
	
	/**
	 * This methods closed any sockets that are no longer needed.
	 */
	private void closeSocket () {
		
		try {
			stdIn.close();
			objectInput.close();
			objectOutput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This is the main method used to create the client that the user uses to communicate with the 
	 * server to play tic tac toe with another client. When the server have two clients connected to it
	 * it creates a game thread. The client asks for the player's name and then communicates with the server
	 * to play the game. It closes the sockets when the game is over.
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main (String [] args) throws IOException, ClassNotFoundException {
		System.out.println("Client started");
		ClientExample aClient = new ClientExample ("localhost", 9897);
		aClient.askPlayerName();
		aClient.communicate();
		aClient.closeSocket();
		
	}

}
