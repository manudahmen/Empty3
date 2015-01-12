public class GCOP extends Thread {

	private int timeMillis;
	private boolean running  =true;

	public GCOP(int timeMillis) {
		super(); 
		this.timeMillis = timeMillis;
	}

	public void pauseGC()
	{
		running = false;
	}

	@Override
	public synchronized void run() {
		while(true && running)
		{
			try {
				wait(timeMillis);
			} catch (InterruptedException e) {
				System.err.println("GCOP Exit");
				e.printStackTrace();
				return;
			}
			System.gc();
		}
	}
	
	
}
