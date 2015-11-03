package client;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Treasure {
	public final int  handleTime = 5;
	private String name;
	private boolean isowner;
	private int timer;
	private final Lock mutex = new ReentrantLock(true);
	
	public Treasure(String name) {
		this.name = name;
		isowner = false;
	}
	
	public int getRemainTime() {
		int t  = handleTime - timer;
		return  t;
	}
	
	public int getTimer() {
		return  timer;
	}
	
	public void setTimer(int t) {
		mutex.lock();
		this.timer = t;
		mutex.unlock();
	}
	
	public boolean isOwner(){
		return isowner;
	}
	
	public String getName() {
		return name;
	}
	
	public String getState() {
		if (isowner) 
			return "YES";
		else
			return "NO";
	}
	
	public void get() {
		isowner = true;
	}
	
	public void release() {
		mutex.lock();
		isowner = false;
		timer = 0;
		mutex.unlock();
	}
}
