import java.io.IOException;
import java.net.Socket;

public class Test {

public static void main(String[] args) {
		
		ChatRoom chat = new ChatRoom(5000);
		
		try {

			System.out.println("Server has started...");
			
			while(true) {
				System.out.println("Accepting clients...");
				Socket client = chat.accept();
				User user = new User(client, chat);
				chat.addUser(user);
				System.out.println("Client accepted.\nOnline Users: " + chat.getNumberOfUsers()); 
				user.send(chat);
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
