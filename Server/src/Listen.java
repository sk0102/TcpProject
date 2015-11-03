import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;

public class Listen extends Thread {
	private int port;
	private ServerSocket listenFd;
	private List <ServerThread> clientHandler;
	private Treasure ta,tb,tc;
	private int connectNum = 1;
	
	public Listen(int port) throws IOException { 
	    this.port = port; 
	    this.listenFd = new ServerSocket(port);
	    this.clientHandler = new LinkedList();
	    this.ta = new Treasure();
	    this.tb = new Treasure();
	    this.tc = new Treasure();
	    start();
	}

	@Override
	public void run() {
		
		while(connectNum < 3) {
			try {
				Socket acceptFd = listenFd.accept();
				System.out.println(acceptFd.getRemoteSocketAddress()+" connect to server");
				clientHandler.add(new ServerThread(acceptFd,ta,tb,tc,connectNum)); 
				connectNum++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for(ServerThread it:clientHandler) {
			it.start();
		}
		
		while(true) {
			System.out.println("A "+ta.toString()+"B "+tb.toString()+"C "+tc.toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	} 
}

