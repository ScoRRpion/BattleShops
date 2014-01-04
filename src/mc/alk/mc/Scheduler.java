package mc.alk.mc;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class Scheduler {
	static int count = 0; // / count of current async timers

	/** Our current async timers */
	static Map<Integer, Timer> timers = new ConcurrentHashMap<Integer, Timer>();

	static class CompletedTask extends TimerTask {
		final Runnable r;
		final int id;

		public CompletedTask(Runnable r, int id) {
			this.r = r;
			this.id = id;
		}

		@Override
		public void run() {
			Scheduler.timers.remove(this.id);
			this.r.run();
		}
	}

	public static int scheduleAsynchrounousTask(Runnable task) {
		return Scheduler.scheduleAsynchrounousTask(task, 0);
	}

	public static int scheduleAsynchrounousTask(Runnable task, long millis) {
		int tid = Scheduler.count++;
		synchronized (Scheduler.timers) {
			Timer t = new Timer();
			t.schedule(new CompletedTask(task, tid), millis);
			Scheduler.timers.put(tid, t);
		}
		return tid;
	}

}
