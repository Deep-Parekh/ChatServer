import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket socket;
		ChatRoom chat = new ChatRoom();
		
		try {

			socket = new ServerSocket(5000);
			
			while(true) {
				Socket client = socket.accept();
				User user = new User(client, chat);
				chat.addUser(user);
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
