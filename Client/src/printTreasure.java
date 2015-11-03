package client;

public class printTreasure extends Thread{
	Treasure treasure[];
	public printTreasure(Treasure treasure[]) {
		this.treasure = treasure;
		start();
	}
	
	@Override
	public void run() {
		while(true) {
			for(int i = 0;i<treasure.length;i++) {
				if (treasure[i].getState().equals("YES")) {
					System.out.println(treasure[i].getName()+" "+treasure[i].getState()+" "+treasure[i].getRemainTime());
				} else {
					System.out.println(treasure[i].getName()+" "+treasure[i].getState());
					//System.out.println(treasure[i].getName()+" "+treasure[i].getState()+" "+treasure[i].getRemainTime());
				}
				
			}
			System.out.println("==================================");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}