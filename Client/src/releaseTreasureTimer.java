package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;

import java.util.TimerTask;

import client.Treasure;


public class releaseTreasureTimer {
	int releaseTime = 5;
	Treasure treasure;
	Socket client;
    Timer timer;
    public releaseTreasureTimer(Socket socket,Treasure treasure) {
		this.client = socket;
		this.treasure = treasure;
        timer = new Timer();
        timer.schedule(new TimerTestTask(), 0, 1000);
    }
    class TimerTestTask extends TimerTask {
    	@Override
        public void run() {
        	PrintWriter out;
    		int time = treasure.getTimer();
    		time++;
    		if (time%releaseTime == 0) {
    			try {
    				out = new PrintWriter(client.getOutputStream(), true);
    				out.println("RELEASE "+treasure.getName());
    				out.flush();
    				treasure.release();
    				treasure.setTimer(0);
    				timer.cancel(); 
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		} else {
    			treasure.setTimer(time%5);
    		}
            
        }
    }
}