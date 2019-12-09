import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientReceiver implements Runnable
{
	ObjectInputStream in;
	
	public ClientReceiver(ObjectInputStream inFromServer)
	{
		in = inFromServer;
	}
	
	public void run()
	{
		String str;
		while(true) 
		{
			try 
			{
				str = (String) in.readObject();
				System.out.println(str);
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
