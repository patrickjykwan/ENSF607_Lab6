package test;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.io.Socket;
//import java.io.UnknownHostException;

public class ClientExample {
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private BufferedReader stdIn;
	
	public ClientExample (String serverName, int portNumber) {
		 
		try {
			aSocket = new Socket (serverName, portNumber);
			stdIn = new BufferedReader (new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader (aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(),true);
		}catch (UnknownHostException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void communicate() {
		String line = "";
		String response = "";
		
		while(!line.equals("Quit")) {
			System.out.println("Enter a word to capitalize or type QUIT to end:");
			try {
				line = stdIn.readLine();
				socketOut.println(line); // you need to println so it knows where the message end.
										//just print would not work!!!
				response = socketIn.readLine(); //read response from the socket
				System.out.println("The response is: " + response);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// reading the input from the user(i.e the keyboard);
		}
		closeSocket();
		
	}
	
	private void closeSocket() {
		
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] arg) throws IOException{
		
		ClientExample aClient = new ClientExample("localhost",9898);
		aClient.communicate();
		
	}
	
	
	
}
	