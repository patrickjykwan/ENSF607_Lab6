package servertest3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerWithThreadPool {

	private Socket aSocket;
	private Socket bSocket;
	
	private ServerSocket serverSocket;
	
	private PrintWriter socketOut1;
	private BufferedReader socketIn1;
	private PrintWriter socketOut2;
	private BufferedReader socketIn2;
	
	
	
	
	private ExecutorService pool;

	public ServerWithThreadPool() {
		try {
			serverSocket = new ServerSocket(9897);
			pool = Executors.newFixedThreadPool(2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void runServer() {

		try {
			while (true) {
				String line = "";
				
				aSocket = serverSocket.accept();
				System.out.println("Console at Server side says: Player 1 connection accepted by the server!");
				//here we call playert!
				
				//socketIn1 = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				//socketOut1 = new PrintWriter(aSocket.getOutputStream(), true);
				
				//socketOut1.println("PLAYER1\n");
				
				
				bSocket = serverSocket.accept();
				System.out.println("Console at Server side says: Player 2 connection accepted by the server!");
				//System.out.println("Console at Server side says: Player 2 connection accepted by the server!");
				
				//socketIn2 = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				//socketOut2 = new PrintWriter(aSocket.getOutputStream(), true);
				
				//socketOut2.println("PLAYER2\n");
				
				
				Game game = new Game(aSocket, bSocket);
				
				//Capitalizer cap = new Capitalizer(socketOut, socketIn);
				pool.execute(game);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.shutdown();
		try {
			//socketIn.close();
			//socketOut.close();
			aSocket.close();
			bSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}


	public static void main(String[] args) throws IOException {
		System.out.println("Server Started");
		ServerWithThreadPool myServer = new ServerWithThreadPool();
		myServer.runServer();
	}

}
