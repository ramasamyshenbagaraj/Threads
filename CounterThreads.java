package com.example.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Program to display even number using one thread and odd numbers by other thread using Semaphore.
 * 
 * @author ramasamy
 *
 */
public class CounterThreads {
	
	public static void main(String args[]){

		SharedResource r = new SharedResource();
		Thread thread1 = new Thread(new OddThread(r, 10));
		Thread thread2 = new Thread(new EvenThread(r, 10));
		thread1.start();
		thread2.start();
		
	}

	
}

class SharedResource {
	Semaphore evenSem = new Semaphore(0);
	Semaphore oddSem = new Semaphore(1);
	
	public SharedResource() {
	}
	
	public void printOddNumber(int number ) {
		try {
			oddSem.acquire();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("OddThrad is printing -- " + number );
		
		evenSem.release();
		
		
	}
	
	public void printEvenNumber(int number ) {
		try {
			evenSem.acquire();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("EvenThrad is printing -- " + number );
		
		oddSem.release();
		
		
	}
}


class OddThread implements Runnable {
	
	SharedResource r = null;
	
	int count = 0;
	
	public OddThread(SharedResource r, int count ) {
		this.r = r;
		this.count = count;
	}
	
	@Override
	public void run(){
		for(int i=1; i<= count ; i=i+2) {
			r.printOddNumber(i);
		}
	}
		
}


class EvenThread implements Runnable {
	
	SharedResource r = null;
	
	int count = 0;
	
	public EvenThread(SharedResource r, int count ) {
		this.r = r;
		this.count = count;
	}
	
	@Override
	public void run(){
		
		for(int i=2; i<= count ; i=i+2) {
			r.printEvenNumber(i);
		}
		
	}
}
