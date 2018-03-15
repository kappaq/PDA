package test1;

import java.util.LinkedList;


class Producer implements Runnable {

	private LinkedList<Integer> sharedQueue;
	private int maxSize = 4;
	private int product;

	public Producer(LinkedList<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		product = 1; // create product
		synchronized (sharedQueue) {

			while (sharedQueue.size() == maxSize) {
				System.out.println("Queue is full " + "");
				try {
					sharedQueue.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sharedQueue.push(product);
			sharedQueue.notify();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("PRODUCED: "+ product);

	}
}