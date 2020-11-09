package servertest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Provides data fields and methods to create a Java server that hosts a game of tic tac toe for pairs of clients.
 * Creates a game thread for every pair of players.
 * @author Patrick Kwan
 * @version 1.0
 * @since November 9, 2020
 *
 */
public class ServerWithThreadPool {
	/**
	 * This is the socket of the player 1 client that the server accepts.
	 */
	private Socket aSocket;
	
	/**
	 * This is the socket of the player 2 client that the server accepts.
	 */
	private Socket bSocket;
	
	/**
	 * This is the socket that the server is hosted on.
	 */
	private ServerSocket serverSocket;
		
	/**
	 * This is the pool of threads.
	 */
	private ExecutorService pool;

	/**
	 * This constructor is used to create a thread pool that hosts threads of games for every pair
	 * of players.
	 */
	public ServerWithThreadPool() {
		try {
			serverSocket = new ServerSocket(9897);
			pool = Executors.newFixedThreadPool(2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to run the server. It accepts two clients and creates a game thread using the 
	 * two accepted sockets.
	 */
	public void runServer() {

		try {
			while (true) {
				
				aSocket = serverSocket.accept();
				System.out.println("Console at Server side says: Player 1 connection accepted by the server!");
				
				bSocket = serverSocket.accept();
				System.out.println("Console at Server side says: Player 2 connection accepted by the server!");
		
				Game game = new Game(aSocket, bSocket);
				
				pool.execute(game);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.shutdown();
		try {
			aSocket.close();
			bSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * This is the main method that creates the server with a thread pool that hosts tic tac toe games
	 * between pairs of players.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Server Started");
		ServerWithThreadPool myServer = new ServerWithThreadPool();
		myServer.runServer();
	}

}
