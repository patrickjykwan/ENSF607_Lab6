package servertest3;


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
			//socketIn = new BufferedReader (new InputStreamReader (aSocket.getInputStream()));
			//socketOut = new PrintWriter (aSocket.getOutputStream(), true);
			
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
	public void communicate () throws IOException, ClassNotFoundException {
		
		//String line;
		//line = socketIn.readLine();
		//line = (String) objectInput.readObject();
		//player = line;
		//player="PLAYER1";
		//System.out.println("I am: "+player);
		
		//?If avaliable?
		/*
		myPlayer = (Player) objectInput.readObject();
		System.out.println("Received Player object");
		
		
		if(myPlayer.getMark()== 'X') {
			System.out.println("I am player 1");
		}else {
			System.out.println("I am player 2");
		}
		*/
		System.out.println("Waiting for an opponent to appear");
		boolean gameContinue = true; 
		while(gameContinue) {
			
			
			myPlayer = (Player) objectInput.readObject();
			//System.out.println("Received Player object");
			//myPlayer.getBoard().display();
			
			//System.out.println("Call my Player play");
			gameContinue = myPlayer.play();
			
			//System.out.println("Send my Player data");
			objectOutput.writeObject(myPlayer);
			System.out.println("\n\n\n");
		}
		System.out.println("Game is over");
		
		/*
		
		if(player.equals("PLAYER1") ) {
			System.out.println("I am player 1");
			
			myPlayer = (Player) objectInput.readObject();
			
		}else if(player.equals("PLAYER2")) {
			System.out.println("I am player 2");
			myPlayer = (Player) objectInput.readObject();	
			
		}
		
		while(true) {
			
			if(player.equals("PLAYER1") ) {
				
				myPlayer.play();
				objectOutput.writeObject(myPlayer);
				myPlayer = (Player) objectInput.readObject();
				
			}else if(player == "PLAYER2") {
				
				myPlayer = (Player) objectInput.readObject();
				myPlayer.play();
				objectOutput.writeObject(myPlayer);
				
				
			}
			
		}
		
		*/
		
	
		
		
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
		aClient.communicate();
		aClient.closeSocket();
		
	}

}
