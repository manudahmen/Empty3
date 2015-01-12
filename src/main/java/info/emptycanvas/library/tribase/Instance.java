/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.tribase;

public class Instance {
	private class ThreadInstance extends Thread {
		private boolean started = false;
		private boolean stopped = false;
		private boolean paused = false;
		private boolean killed = false;

		public ThreadInstance(Params params) {
			instance.setParams(params);
		}

		public void run() {
			while (!killed) {

				while (!stopped & started & !paused) {
					instance.initFrame();
					instance.computeFrame();
					instance.showFrame();
					instance.computeFrame();
				}
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void setKilled(boolean killed) {
			this.killed = killed;
		}

		public void setPaused(boolean paused) {
			this.paused = paused;
		}

		public void setStarted(boolean started) {
			this.started = started;
		}

		public void setStopped(boolean stopped) {
			this.stopped = stopped;
		}

	}
	private BaseGenerator instance;

	private ThreadInstance thread;

	public Instance(BaseGenerator bg, Params params) {
		instance = bg;
		thread = new ThreadInstance(params);
		thread.start();
	}

	public void kill() {
		thread.setStopped(true);
		thread.setKilled(true);
	}

	public void pauseInstance() {
		thread.setPaused(true);
	}

	public void restartInstance() {
		thread.setStopped(true);
		thread.setStarted(true);
	}

	public void startInstance() {
		thread.setStarted(true);
	}

	public void stopInstance() {
		thread.setStopped(true);
	}
}
