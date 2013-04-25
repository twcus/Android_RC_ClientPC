import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	
	private static final String SERVER_IP = "128.83.198.77"; //fill in rasberry pis hostname
	private static final int PI_PORT = 8000; //fill in with correct pi port
	//private static in 	
	private Socket clientSock;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private static String logtag = "CLIENTLOG";
	
	
	public Client() {
		try {
			
			clientSock = new Socket(SERVER_IP, PI_PORT);
			//clientSock.connect(new InetSocketAddress(SERVER_IP, PI_PORT), 5000);
			
			out = new ObjectOutputStream(clientSock.getOutputStream());
			in = new ObjectInputStream(clientSock.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeMessage (String msg) {
		try {
			out.writeChars(msg.trim() + "\n");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection () {
		try {
			in.close();
			out.close();
			clientSock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.writeMessage("TEST");
		client.closeConnection();
	}
}
