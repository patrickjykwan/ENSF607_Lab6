package servertest2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientExample {
	
	private Socket aSocket;
	//private PrintWriter socketOut;
	//private BufferedReader socketIn;
	private BufferedReader stdIn;
	
	private ObjectInputStream objectInput;
	private ObjectOutputStream objectOutput;
	
	
	private Player myPlayer;
	private String player;
	//private boolean myTurn;
	
	
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
	
	
	public void askPlayerName() throws IOException, ClassNotFoundException{
		myPlayer = (Player) objectInput.readObject();
		System.out.println("Enter Name");
		myPlayer.setName(stdIn.readLine());	
		objectOutput.writeObject(myPlayer);
	}
	
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
	
	
	
	
	public static void main (String [] args) throws IOException, ClassNotFoundException {
		System.out.println("Client started");
		ClientExample aClient = new ClientExample ("localhost", 9897);
		aClient.askPlayerName();
		aClient.communicate();
		aClient.closeSocket();
		
	}

}
