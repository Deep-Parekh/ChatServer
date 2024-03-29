import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

public class User {
	
	String username;
	Socket userSocket;
	ObjectOutputStream outToUser; 
	ObjectInputStream inFromUser;
	String userMsg;
	ChatRoom chatRoom;
	
	public User(String username) {
		this.username = username;
	}
	
	public User(Socket client, ChatRoom chatRoom) {
		this.userSocket = client;
		try {
			this.outToUser = new ObjectOutputStream(client.getOutputStream());
			this.inFromUser = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.chatRoom = chatRoom;
	}
	
	public User(Socket client, String username, ChatRoom chatRoom) {
		this.userSocket = client;
		this.username = username;
		try {
			this.outToUser = new ObjectOutputStream(client.getOutputStream());
			this.inFromUser = new ObjectInputStream(client.getInputStream());
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
	
	public String receiveFromUser() throws ClassNotFoundException {
		String msg = null;
		try {
			msg = (String) inFromUser.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg.trim();
	}
	
	@Override
	public String toString() {
		return this.username;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof User)) return false;
		User usr = (User) o;
		return this.username.equals(usr.username);
	}
}
