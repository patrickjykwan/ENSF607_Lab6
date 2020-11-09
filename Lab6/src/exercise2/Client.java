package exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import test.ClientExample;

/**
 * Provides data fields and methods to create a Java client that communicates with a server to get either the Date or the Time.
 * @version 1.0
 * @since November 9, 2020
 *
 */
public class Client {
	/**
	 * This socket is used to send words to the server.
	 */
	private PrintWriter socketOut;
	
	/**
	 * This socket is used establish a connection between the server and the client.
	 */
	private Socket palinSocket;
	
	/**
	 * This is the buffer reader used to receive words from the user via the keyboard
	 */
	private BufferedReader stdIn;
	
	/**
	 * this is used to receive information from the server.
	 */
	private BufferedReader socketIn;

	/**
	 * Constructs a client that connects to a server with a given name and port number. The values of the datafields are supplied by constructing 
	 * corresponding objects using the given parameters.
	 * @param serverName the palidrome server's name
	 * @param portNumber the palidrome server's port nunber
	 */
	public Client(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(
					palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	/**
	 * Takes user input and sends it to the server via socketOut and takes information sent by the server via socketIn.
	 * Closes sockets when the user enters QUIT.
	 */
	public void communicate()  {

		String line = "";
		String response = "";
		boolean running = true;
		while (running) {
			try {
				System.out.println("Please select an option (DATE/TIME) ");
				line = stdIn.readLine();
				if (!line.equals("QUIT")){
					System.out.println(line);
					socketOut.println(line);
					response = socketIn.readLine();
					System.out.println(response);	
				}else{
					running = false;
				}
				
			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}
		}
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}

	}
	

		
		
	
	
	/**
	 * This is the main method that is runned when a client is runned. It creates a client that connects to the local host at port number 9090.
	 * After getting a connect to the server it starts to communicate as outlined in the communicate method above.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException  {
		Client aClient = new Client("localhost", 9090);
		aClient.communicate();
	}
}