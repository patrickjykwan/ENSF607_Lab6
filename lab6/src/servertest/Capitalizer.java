package servertest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Capitalizer implements Runnable{
	
	private PrintWriter socketOut1;
	private BufferedReader socketIn1;
	private PrintWriter socketOut2;
	private BufferedReader socketIn2;
	/**
	 * Instantiate 2 players
	 * @param socketOut1
	 * @param socketIn1
	 * @param socketOut2
	 * @param socketIn2
	 */
	public Capitalizer (PrintWriter socketOut1, BufferedReader socketIn1, PrintWriter socketOut2, BufferedReader socketIn2) {
		this.socketOut1 = socketOut1;
		this.socketIn1 = socketIn1;
		this.socketOut2 = socketOut2;
		this.socketIn2 = socketIn2;
		
	}
	
	public void instantiate() {
		String line = null;
		line = "Player1 put in your name.";
		socketOut1.println(line);
		try {
			line = socketIn1.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("This is your name " + line);
		
		line = "Player2 put in your name.";
		socketOut2.println(line);
		try {
			line = socketIn2.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("This is your name " + line); 
		
	}
	
	public void play() {
		
	}
	public void capitalize() {
	
	}
	
	/*
	public void capitalize() {
		String line = null;
		//while(true) {
			try {
				
				line = "Player1 put in your name.";
				socketOut1.println(line);
				
				line = socketIn1.readLine();
				
				System.out.println("This is your name " + line); 
				
				if (line.equals("QUIT")) {
					line = "Good Bye!";
					socketOut1.println(line);
					//break;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// reading form
			
			try {
				
				line = "Player2 put in your name.";
				socketOut2.println(line);
				
				line = socketIn2.readLine();
				
				System.out.println("This is your name " + line); 
				
				if (line.equals("QUIT")) {
					line = "Good Bye!";
					socketOut1.println(line);
					//break;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// reading form
			
			
			
			
			
			
			
		//}
		
	}
	*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 capitalize();
		
	}

}
