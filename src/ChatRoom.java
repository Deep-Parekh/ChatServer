import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChatRoom implements Runnable{
	
	private List<User> onlineUsers;
	private int users;
	private MessageQueue messageQueue;
	//Lock lock = new ReentrantLock();	// May need to remove this 
	
	public ChatRoom() {
		this.onlineUsers = new LinkedList<User>();
		this.users = 0;
		this.messageQueue = new MessageQueue();
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
	
	/*
	 * Will need to be synchronized (Using locks or synchronized)
	 * to only allow 1 user to send a message at one time
	 */
	public synchronized void receiveMessage() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
