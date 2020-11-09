package exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Provides data fields and methods to create a Java server that communicates with a client that identifies whether or not a
 * given user string is a Palindrome.
 * @author Patrick Kwan
 * @version 1.0
 * @since November 9, 2020
 *
 */
public class ServerExample {
	/**
	 * This is the socket of the client that the server accepts. It is the accepted socket.
	 */
	private Socket aSocket;
	
	/**
	 * This is the socket that the server is being hosted on.
	 */
	private ServerSocket serverSocket;
	
	/**
	 * This is the socket that the server outputs to to communicate with the client.
	 */
	private PrintWriter socketOut;
	
	/**
	 * This is the socket that the server uses to take inputs from client 
	 */
	private BufferedReader socketIn;
	
	/**
	 * This is the constructor used to create a server object that is hosted on port 8099.
	 */
	public ServerExample() {
		try {
			serverSocket = new ServerSocket(8099);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This is the method used to determine if a word is a palindrome. It takes the word from the socketIn then runs logic to determine if it is a palindrome.
	 * If the word is a palidrome a corresponding statement is sent to the client saying that the word is a palindrome. Else it sends to a client a statement
	 * saying the word is not a palindrome.
	 * If the logic detects the word QUIT the method ends.
	 */
	public void palindrome() {
		String line = null;
		while(true) {
			try {
				line = socketIn.readLine();
				
				if (line.equals("QUIT")) {
					line = "Good Bye!";
					socketOut.println(line);
					break;
				}
				
				//Here is palindrome code
				int end = line.length()-1;
				int start = 0;
				boolean pal = true;
				while(start<end) {
					if(line.charAt(start)!=line.charAt(end)) {
						pal = false;
						break;
					}
					start = start + 1;
					end= end - 1;
						
				}
				
				if(pal) {
					
					line = line + " is a Palindrome";
					System.out.println("Server says " + line);
				}else {
					line = line + " is not a Palindrome";
					System.out.println("Server says " + line);
				}
				
				socketOut.println(line);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// reading form
		}
		
	}
	
	
	/**
	 * This is the main method for the ServerExample class. It creates a server object and outputs a statement indicating the server is running. It then waits to accept
	 * a client. Upon accepting the client it creates the corresponding input/output sockets and calls the palindrome method. After the palindrome method is complete it closes
	 * the sockets. 
	 * @param args
	 * @throws IOException
	 */
	public static void main (String [] args)throws IOException {
		
		ServerExample myServer = new ServerExample();
		System.out.println("Server is now running.");
		
		try {
			myServer.aSocket = myServer.serverSocket.accept();
			System.out.println("Console at Server side says: Connection accepted by the server!");
			myServer.socketIn = new BufferedReader (new InputStreamReader(myServer.aSocket.getInputStream()));
			myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(), true);
			myServer.palindrome();
			
			
			myServer.socketIn.close();
			myServer.socketOut.close();
			
			
			
		}catch( IOException e) {
			e.printStackTrace();
		}
		
	}

}
