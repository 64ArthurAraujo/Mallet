package mallet.cache.thread.object;

public class ProductThread {
	int id;
	Thread thread;
	
	public ProductThread(int id, Thread thread) {
		this.id     = id;
		this.thread = thread;
		thread.start();
	}
	
	public int getId() {
		return id;
	}
	public Thread getThread() {
		return thread;
	}
	
}
