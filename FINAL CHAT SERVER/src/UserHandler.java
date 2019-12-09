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
	
	public synchronized int addMessage(String msg) {
		if(msg == ".") {
			users.remove(this.user);
			return -1;
		}
		try {
			messageQueue.add(this.user.username + ": " + msg);
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}
	
	public void run()
	{
		try {
			this.user.username = user.receiveFromUser();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true)
		{
			try {
				String str = user.receiveFromUser();
				int status = addMessage(str);
				
				while(!messageQueue.isEmpty())
				{
					System.out.println(messageQueue.remove());
				}
				
				if(status == -1)
					break;
			}
			
			catch (ClassNotFoundException e) {
				
			}
		}
	}
}