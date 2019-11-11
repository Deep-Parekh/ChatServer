import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class User {
	
	String username;
	Socket userSocket;
	OutputStream outToUser; 
	InputStream inFromUser;
	
	public User(Socket client) {
		this.userSocket = client;
		try {
			this.outToUser = client.getOutputStream();
			this.inFromUser = client.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public User(Socket client, String username) {
		this.userSocket = client;
		this.username = username;
		try {
			this.outToUser = client.getOutputStream();
			this.inFromUser = client.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean sendToUser(String msg) {
		PrintWriter writer = new PrintWriter(this.outToUser);
		try {
			writer.write(msg);
			writer.flush();
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
