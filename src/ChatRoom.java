import java.util.LinkedList;
import java.util.List;

public class ChatRoom implements Runnable{
	
	List<User> onlineUsers;
	
	public ChatRoom(User user) {
		this.onlineUsers = new LinkedList<User>();
		this.onlineUsers.add(user);
	}
	
	public void sendAll(String msg) {
		for(User user: onlineUsers) {
			user.sendToUser(msg);
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
