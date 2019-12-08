import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChatRoom{
	
	private transient List<User> onlineUsers;
	private Queue<String> messageQueue;
	int users;
	ServerSocket socket;
	//Lock lock = new ReentrantLock();	// May need to remove this 
	
	public ChatRoom(int portNum) {
		this.onlineUsers = new LinkedList<User>();
		UserHandler.setUsers(onlineUsers);
		this.users = 0;
		this.messageQueue = new LinkedList<String>();
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
		this.messageQueue = new LinkedList<String>();
	}
	
	public Socket accept() throws IOException{
		return this.socket.accept();
	}
	
	public void addUser(User user) {
		this.onlineUsers.add(user);
		++this.users;
	}
	
	public void sendAll() {
		while(!this.messageQueue.isEmpty()) {
			String msg = messageQueue.remove();
			for(User user: onlineUsers) {
				user.sendToUser(msg);
			}
		}
	}
	
	public void removeUser(User user) {
		this.onlineUsers.remove(user);
		--this.users;
	}
	
	public int getNumberOfUsers() {
		return this.users;
	}
	
	public synchronized boolean addMessage(String msg) {
		if(msg == ".") {
			--this.users;
			return true;
		}
		try {
			this.messageQueue.add(msg);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
}
