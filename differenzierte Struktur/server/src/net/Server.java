package net;

import java.net.*;
import java.io.*;

public class Server {
	
	ServerSocket serverSocket;
	Socket clientSocket;
	
	InputStreamReader in;
	BufferedReader bf;
	
	PrintWriter pr;
	
	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		
		System.out.println("client connected");
		
		in = new InputStreamReader(clientSocket.getInputStream());
		bf = new BufferedReader(in);
		
		System.out.println("client: "+bf.readLine());
		
		pr = new PrintWriter(clientSocket.getOutputStream());
		pr.println("Connection Established.");
		pr.flush();
	}
	
}