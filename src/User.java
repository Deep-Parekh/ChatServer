import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class User implements Runnable{
	
	String username;
	Socket userSocket;
	OutputStream outToUser; 
	InputStream inFromUser;
	String userMsg;
	ChatRoom chatRoom;
	
	public User(Socket client, ChatRoom chatRoom) {
		this.userSocket = client;
		try {
			this.outToUser = client.getOutputStream();
			this.inFromUser = client.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.chatRoom = chatRoom;
	}
	
	public User(Socket client, String username, ChatRoom chatRoom) {
		this.userSocket = client;
		this.username = username;
		try {
			this.outToUser = client.getOutputStream();
			this.inFromUser = client.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.chatRoom = chatRoom;
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
	
	public String receiveFromUser() {
		String msg = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.inFromUser));
			msg = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
