import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Treasure {
	private int owner = -1;
	private final Lock mutex = new ReentrantLock(true);
	
	public boolean setOwner(int owner){
		mutex.lock();
		if (this.owner > -1 ) {
			mutex.unlock();
			return false;
		}
		this.owner = owner;
		mutex.unlock();
		return true;
	}
	
	public boolean releaseOwner(int owner){
		if (this.owner == owner) {
			this.owner = -1;
			return true;
		} else {
			return false;
		}
		
	}
	
	
	@Override 
	public String toString() {
		if (owner > -1) 
			return "YES ".concat(Integer.toString(owner)).concat("\n");
		else
			return "NO 0\n";
		
	}
	
}
