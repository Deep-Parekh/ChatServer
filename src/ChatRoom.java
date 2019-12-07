import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChatRoom{
	
	private List<User> onlineUsers;
	private MessageQueue messageQueue;
	int users;
	ServerSocket socket;
	//Lock lock = new ReentrantLock();	// May need to remove this 
	
	public ChatRoom(int portNum) {
		this.onlineUsers = new LinkedList<User>();
		this.users = 0;
		this.messageQueue = new MessageQueue();
		try {
			this.socket = new ServerSocket(portNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ChatRoom(User user) {
		this.onlineUsers = new LinkedList<User>();
		this.onlineUsers.add(user);
		++this.users;
		this.messageQueue = new MessageQueue();
	}
	
	public void addUser(User user) {
		this.onlineUsers.add(user);
		++this.users;
	}
	
	public void sendAll(String msg) {
		for(User user: onlineUsers) {
			user.sendToUser(msg);
		}
	}
	
	public void removeUser(User user) {
		this.onlineUsers.remove(user);
		--this.users;
	}
	
	public int getNumberOfUsers() {
		return this.users;
	}
	
	public static void main(String[] args) {
		
		ChatRoom chat = new ChatRoom(5000);
		
		try {

			System.out.println("Server has started...");
			
			while(true) {
				System.out.println("Accepting clients...");
				Socket client = chat.socket.accept();
				User user = new User(client, chat);
				chat.addUser(user);
				System.out.println("Client accepted.\nOnline Users: " + chat.users);
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
