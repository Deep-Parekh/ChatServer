import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueHandler implements Runnable{

	private static List<User> users = new LinkedList<User>();
	private static Queue<String> messageQueue = null;
	User user;
	
	public static void setUsers(List<User> tempusers)
	{
		users = tempusers;
	}
	
	public static void setQueue(Queue<String> messages) {
		messageQueue = messages;
	}
	
	public void run()
	{
		while (true)
		{
			try {
				if(messageQueue.size() == 0)
					wait();
				while(messageQueue.size() > 0) {
					for(User user : users) {
						user.sendToUser(messageQueue.poll());
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}