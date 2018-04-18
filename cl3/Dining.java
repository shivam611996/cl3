
package dining;

import com.mongodb.*; 		// to connect mongodb in java
import java.util.concurrent.locks.Lock;  // locks are used to lock the resources 
import java.util.concurrent.locks.ReentrantLock; // ReentrantLock are used to unlock the resources 

public class Dining {   // main class logic

	public static void main(String[] args) {  // main function.

		Lock forks[] = new ReentrantLock[5];    // objects are created for locking and unlocking the resources
		try {
			MongoClient mongoClient = new MongoClient("localhost");  // connect to mongoDB as localhost
			System.out.println("Connection to mongodb successful.");
			DB db = mongoClient.getDB( "mydb" );		 // created the data base named as "mydb"
			System.out.println("Database 'mydb' created.");
			DBCollection coll = db.createCollection("mycol", null); // created the colloction named as "mycol"
			System.out.println("Collection 'mycol' created.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  // to print any error and exceptions 
		}

		for(int i = 0; i<5; i++){
			forks[i] = new ReentrantLock();
		}
		// five threads are created for five philosophers given below.

		Thread p1 = new Thread(new Philosopher(forks[4], forks[0], "first"));  
		Thread p2 = new Thread(new Philosopher(forks[0], forks[1], "second"));
		Thread p3 = new Thread(new Philosopher(forks[1], forks[2], "third"));
		Thread p4 = new Thread(new Philosopher(forks[2], forks[3], "fourth"));
		Thread p5 = new Thread(new Philosopher(forks[3], forks[4], "fifth"));
		// all the threads are started 
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		}
}

class Philosopher implements Runnable {  // Philosopher class is created to perform main logic
	// two locks are created one for leftFork and other for rightFork
	Lock leftFork = new ReentrantLock();			
	Lock rightFork = new ReentrantLock();
	String name;
	public Philosopher(Lock leftFork, Lock rightFork, String name) { // Philosopher constructor 
		this.leftFork = leftFork;
		this.rightFork = rightFork;
		this.name = name;
	}
	@Override
	public void run() {  // run function from Runnable is override to perform this task
		try {
			think(name);
			eat(leftFork, rightFork, name); // eat function is called here
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void eat(Lock leftFork, Lock rightFork, String name) throws Exception{
		leftFork.lock(); //locking left and right fork
		rightFork.lock();
		try
		{
			// connecting to mongodb and started eating wala function called as "doc1"
			MongoClient mongoClient = new MongoClient("localhost");
			DB db = mongoClient.getDB( "mydb" );
			DBCollection coll = db.getCollection("mycol");
			System.out.println(name + " eating...");
			BasicDBObject doc1 = new BasicDBObject(name , " eating...");  // doc1 is created 
			coll.insert(doc1);  // inserting doc1 to database
			Thread.sleep(1000); // goint ot sleep for 1000ms
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  // to print any error and exceptions 
		}
		finally{
			// connecting to mongodb and done eating wala function called as "doc2"
			System.out.println(name + " done eating and now thinking...");
			MongoClient mongoClient = new MongoClient("localhost");
			DB db = mongoClient.getDB( "mydb" );
			DBCollection coll = db.getCollection("mycol");
			BasicDBObject doc2 = new BasicDBObject(name , " done eating and now thinking..."); // doc2 is created
			coll.insert(doc2);		// inserting doc2 to database
			leftFork.unlock();		//unlocking left and right fork
			rightFork.unlock();
		}
	}
	public void think(String name) throws Exception{
		try
		{
			// connecting to mongodb and thinking wala function called as "doc"
			MongoClient mongoClient = new MongoClient("localhost");
			DB db = mongoClient.getDB( "mydb" );
			DBCollection coll = db.getCollection("mycol");
			System.out.println(name + " thinking...");
			BasicDBObject doc = new BasicDBObject(name , " thinking...");		// doc is created
			coll.insert(doc);
			Thread.sleep(1000); // goint ot sleep for 1000ms
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();   // to print any error and exceptions 
		}
	}
}

/*
Server: C:\mongoDB\bin\mongod --dbpath C:\mongoDB\data\db 
Client: C:\mongoDB\bin\mongo
> show dbs
> use mydb
> show tables
> db.mycol.find().pretty()
*/