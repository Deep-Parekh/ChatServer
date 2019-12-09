import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UserHandler implements Runnable{

	private static List<User> users = new LinkedList<User>();
	private static Queue<String> messageQueue = null;
	User user;
	
	public UserHandler(User user)
	{
		this.user = user;
		users.add(user);
	}
	
	public static void setUsers(List<User> tempusers)
	{
		users = tempusers;
	}
	
	public static void setQueue(Queue<String> messages) {
		messageQueue = messages;
	}
	
	public synchronized boolean addMessage(String msg) {
		if(msg == ".") {
			users.remove(this.user);
			return true;
		}
		try {
			messageQueue.add(this.user.username + ":" + msg);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public void run()
	{
		while (true)
		{
			String str = user.receiveFromUser();
			addMessage(str);
		}
	}
}