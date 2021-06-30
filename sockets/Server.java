package sockets;

import java.net.*;
import java.io.*;

public class Server {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		out = new PrintWriter(clientSocket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String greeting = in.readLine();
		if(greeting.equals("hello server")) {
			out.println("hello client");
		}else {
			out.println("unrecognised greeting");
		}
		
	}
	
	public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
	
	public static void main(String args[]) throws IOException {
		Server server = new Server();
		server.start(80);
	}
	
}