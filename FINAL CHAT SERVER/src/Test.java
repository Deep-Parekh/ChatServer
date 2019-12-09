import java.io.IOException;
import java.net.Socket;
import java.util.Queue;

public class Test {

	private static Queue<String> msg = null;
public static void main(String[] args) {
		
		ChatRoom chat = new ChatRoom(5000);
		
		try {

			System.out.println("Server has started...");
			while(true) {
				System.out.println("Accepting clients...");
				Socket client = chat.accept();
				User user = new User(client, chat);
				chat.addUser(user);
				UserHandler handler = new UserHandler(user);
 				Thread t = new Thread(handler);
 				t.start();
				System.out.println("Client accepted.\nOnline Users: " + chat.getNumberOfUsers()); 
				
				
				//user.send(chat);
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
