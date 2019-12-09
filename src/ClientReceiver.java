import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientReceiver implements Runnable
{
	ObjectInputStream in;
	String username;
	
	public ClientReceiver(ObjectInputStream inFromServer, String name)
	{
		in = inFromServer;
		username = name;
	}
	
	public void run()
	{
		String str;
		
		
		JTextArea textarea = new JTextArea(30, 60);
		textarea.setEditable(false);
		
		JScrollPane scrollbar = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		JFrame frame = new JFrame("Chat Box");
		frame.add(scrollbar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(700,400);
		
		frame.setVisible(true); 
		
		textarea.append("Welcome " + username + "\n");
		
		while(true) 
		{
			try 
			{
				str = (String) in.readObject();
				textarea.append(str);
				textarea.append("\n");
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
