package net;

import java.net.*;
import java.io.*;

public class Client {

	public static void main(String args[]) throws IOException{
		Socket clientSocket = new Socket("localhost", 4999);
		
		PrintWriter pr = new PrintWriter(clientSocket.getOutputStream());
		pr.println("is it working?");
		pr.flush();
		
		InputStreamReader in = new InputStreamReader(clientSocket.getInputStream());
		BufferedReader bf = new BufferedReader(in);
		
		String str = bf.readLine();
		System.out.println("server: "+str);
	}
}
