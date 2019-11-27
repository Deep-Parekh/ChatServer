import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue implements Runnable {
	
	private Queue<String> messageQueue; 
	
	public MessageQueue() {
		this.messageQueue = new LinkedList<String>();
	}
	
	public boolean isEmpty() {
		return this.messageQueue.isEmpty();
	}
	
	public void addMessage(String msg) {
		this.messageQueue.add(msg);
	}
	
	public String removeMessage() {
		return this.messageQueue.remove();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
