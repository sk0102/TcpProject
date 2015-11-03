package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.io.PrintWriter;

public class client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket client = connect2Server();
		releaseTreasureTimer timer;
		
		Treasure treasure[] = new Treasure[3];
		for(int i = 0;i< treasure.length;i++) {
			treasure[i] = new Treasure(String.valueOf((char)('A'+i)));
		}
		
		printTreasure p = new printTreasure(treasure);
		
		while(true) {
			for (int i = 0;i< treasure.length;i++) {
				if(treasure[i].isOwner()==false && getTreasureFromServer(client,treasure[i].getName())) {
					treasure[i].get();
					timer = new releaseTreasureTimer(client,treasure[i]);
				}
				sleep(1000);
				
			}
		}
		
		
	}
	
	public static void sleep(int s) {
		try {
			Thread.sleep(s);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean getTreasureFromServer(Socket client, String treasureName) {
		PrintWriter out;
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			out.println("GET "+treasureName);
			BufferedReader in = new BufferedReader( new InputStreamReader( client.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("YES "+treasureName)) { 
					return true;
				} else if (inputLine.equals("NO "+treasureName)) { 
					return false;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	public static Socket connect2Server() {
		Socket client = new Socket();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 1234);
		try {
			client.connect(isa, 10000);
			//out.println("RELEASE A");
			//client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}
	
}
