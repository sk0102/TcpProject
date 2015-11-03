import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread extends Thread  {
	private Socket fd;
	private Treasure ta,tb,tc;
	private int id;
	
	public ServerThread(Socket fd,Treasure ta,Treasure tb,Treasure tc,int id) {
		this.fd = fd;
	    this.ta = ta;
	    this.tb = tb;
	    this.tc = tc;
	    this.id = id;
	    //start();
	}
	
	@Override
	public void run() {
		PrintWriter out;
		BufferedReader in;
		String inputLine;

		try {
			out = new PrintWriter(fd.getOutputStream(), true); 
			in = new BufferedReader( new InputStreamReader( fd.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("GET A")) { 
					if (ta.setOwner(id)) {
						out.println("YES A");
					}else {
						out.println("NO A");
					}
					
				} else if(inputLine.equals("GET B")) {
					if (tb.setOwner(id)) {
						out.println("YES B");
					}else {
						out.println("NO B");
					}
					
				}  else if(inputLine.equals("GET C")) {
					if (tc.setOwner(id)) {
						out.println("YES C");
					}else {
						out.println("NO C");
					}
					
				} else if (inputLine.equals("RELEASE A")) { 
					ta.releaseOwner(id);
				} else if(inputLine.equals("RELEASE B")) {
					tb.releaseOwner(id);
				}  else if(inputLine.equals("RELEASE C")) {
					tc.releaseOwner(id);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
	}
}
