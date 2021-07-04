package net;

import java.net.*;
import java.io.*;

public class Server {
	
	public static void main(String args[]) throws IOException {
		System.out.println("initializing...");
		ServerSocket serverSocket = new ServerSocket(4999);
		Socket clientSocket = serverSocket.accept();
		
		System.out.println("client connected");
		
		InputStreamReader in = new InputStreamReader(clientSocket.getInputStream());
		BufferedReader bf = new BufferedReader(in);
		
		String str = bf.readLine();
		System.out.println("client: "+str);
		
		PrintWriter pr = new PrintWriter(clientSocket.getOutputStream());
		pr.println("yes");
		pr.flush();
	}
	
}