package servertest;
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
		//int clientcounter = 0;
		try {
			while (true) {
				aSocket = serverSocket.accept();
				System.out.println("Console at Server side says: Player 1 has entered!");
			
				socketIn1 = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				socketOut1 = new PrintWriter(aSocket.getOutputStream(), true);
				
				//socketOut1.println("Hello Player 1 please await orders");
				
				bSocket = serverSocket.accept();
				System.out.println("Console at Server side says: Player 2 has entered!");
				
				socketIn2 = new BufferedReader(new InputStreamReader(bSocket.getInputStream()));
				socketOut2 = new PrintWriter(bSocket.getOutputStream(), true);
				
				//socketOut2.println("Hello Player 2 please await orders");
				
				System.out.println("Game created");
				Capitalizer cap = new Capitalizer(socketOut1, socketIn1, socketOut2, socketIn2);
				
				pool.execute(cap);
				
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.shutdown();
		try {
			socketIn1.close();
			socketOut1.close();
			socketIn2.close();
			socketOut2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}


	public static void main(String[] args) throws IOException {

		ServerWithThreadPool myServer = new ServerWithThreadPool();
		myServer.runServer();
	}

}
