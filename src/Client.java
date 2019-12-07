import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	static Scanner kb = new Scanner(System.in);
	static String username;
	static ObjectOutputStream outToServer;
	static ObjectInputStream inFromServer;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket server;
		User user;
		try {
			server = new Socket("localhost",5000);
			System.out.println("Connection with server established");
			getUsername();
			user = new User(username);
			System.out.println("Your username is: " + username);
			inFromServer = new ObjectInputStream(server.getInputStream());		// Order should be opposite of
			outToServer = new ObjectOutputStream(server.getOutputStream());		// that on the server
			String msg = getMessage();
			outToServer.writeUTF(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}
	
	public static void getUsername() {
		System.out.print("Enter your username: ");
		username = kb.nextLine().trim();
	}
	
	public static String getMessage() {
		System.out.print("Enter your message: ");
		String msg = kb.nextLine().trim();
		return msg;
	}

}
