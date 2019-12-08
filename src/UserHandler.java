import java.util.List;

public class UserHandler implements Runnable{

	private static List<User> users;
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
	
	public void run()
	{
		while (true)
		{
			String str = user.receiveFromUser();
			
			for (User user: users) 
			{
				user.sendToUser(str);
			}
		}
	}
}