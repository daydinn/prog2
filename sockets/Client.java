package sockets;

import java.net.*;
import java.io.*;

public class Client {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void startConnection(String ip, int port) throws IOException{
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	public String sendMessage(String msg) throws IOException {
		out.println(msg);
		String resp = in.readLine();
		return resp;
	}
	
	public void stopConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
	
	//Test
	public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() throws IOException {
	    Client client = new Client();
	    client.startConnection("127.0.0.1", 80);
	    String response = client.sendMessage("hello server");
	    if(response.equals("hello client")) {
	    	System.out.println("success");
	    }
	}
	
	public static void main(String args[]) throws IOException {
		Client client = new Client();
	    client.startConnection("127.0.0.1", 6666);
	    String response = client.sendMessage("hello server");
	    if(response.equals("hello client")) {
	    	System.out.println("success");
	    }
	}
}
