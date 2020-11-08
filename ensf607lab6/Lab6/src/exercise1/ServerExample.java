package exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {
	
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	public ServerExample() {
		try {
			serverSocket = new ServerSocket(8099);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void capitalize() {
		String line = null;
		while(true) {
			try {
				line = socketIn.readLine();
				
				if (line.equals("QUIT")) {
					line = "Good Bye!";
					socketOut.println(line);
					break;
				}
				
				line = line.toUpperCase();
				socketOut.println(line);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// reading form
		}
		
	}
	
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
