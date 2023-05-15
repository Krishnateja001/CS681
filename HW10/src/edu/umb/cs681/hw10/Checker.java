package edu.umb.cs681.hw10;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.time.LocalDateTime;

public class Checker implements Runnable{

        private ReentrantLock done_lock = new ReentrantLock();
        private boolean done = false;

        public void setDone() {

                done_lock.lock();
                try {
                        done = true;
                }
                finally {
                        done_lock.unlock();
                }


        }

	private ReentrantLock lock = new ReentrantLock();

	public FileSystem handle = null;

	public void run() {

	while(!done) {		
	done_lock.lock();	
	try{
		handle = FileSystem.getFileSystem();
		//System.out.println("The address of file system object I got hold of is : "+handle + " " + handle.getCapacity());
	
		Directory root = handle.getRootDir();
		System.out.println("start");
		LinkedList<FSElement> root_children = root.getChildren();
		
		for (FSElement element : root_children) {
			
			System.out.println(element.getName());
		}
		System.out.println("end");
	}
	finally {
		try{
			done_lock.unlock();
			Thread.sleep(1000);
		}
		catch(InterruptedException k){
				System.out.println(k + " = child threads done is set");
		}
	}
	}

	}	

	public static void main(String ... args) {
	
		Checker child_object = new Checker();
		
		child_object.handle = FileSystem.getFileSystem();

		Directory Apps = new Directory(child_object.handle.root, "Apps", 1, LocalDateTime.now(), "Admin", LocalDateTime.now());
		File x = new File(Apps, "x", 1, LocalDateTime.now(), "Admin", LocalDateTime.now());
		Apps.appendChild(x);
		child_object.handle.root.appendChild(Apps);
	
		Directory bin = new Directory(child_object.handle.root, "bin", 1, LocalDateTime.now(), "Admin", LocalDateTime.now());
		File y = new File(bin, "y", 1, LocalDateTime.now(), "Admin", LocalDateTime.now());
		Apps.appendChild(y);
		child_object.handle.root.appendChild(bin);

		Directory home = new Directory(child_object.handle.root, "home", 2, LocalDateTime.now(), "Admin", LocalDateTime.now());
		Directory pictures = new Directory(home, "pictures", 2, LocalDateTime.now(), "Admin", LocalDateTime.now());
		File c = new File(home, "c", 1, LocalDateTime.now(), "Admin", LocalDateTime.now());
		File a = new File(pictures, "a", 1, LocalDateTime.now(), "Admin", LocalDateTime.now());
		File b = new File(pictures, "b", 1, LocalDateTime.now(), "Admin", LocalDateTime.now());
		pictures.appendChild(a);
		pictures.appendChild(c);

		home.appendChild(pictures);
		home.appendChild(c);
		child_object.handle.root.appendChild(home);

		Link d = new Link(child_object.handle.root, "d", 1, LocalDateTime.now(), pictures, "Admin", LocalDateTime.now());
		Link e = new Link(child_object.handle.root, "e", 1, LocalDateTime.now(), x, "Admin", LocalDateTime.now());
	
		child_object.handle.root.appendChild(d);
		child_object.handle.root.appendChild(e);

//		Thread child_thread1 = new Thread(child_object);
//		Thread child_thread2 = new Thread(child_object);
		
//		child_thread1.start();
//		child_thread2.start();

               Thread rethreads[] = new Thread[12];

                for(int i = 0 ; i < 12 ; ++i) {

                        rethreads[i] = new Thread(child_object);
                        rethreads[i].start();
                }

		try {
		Thread.sleep(1100);}
		catch(InterruptedException ie){
			System.out.println(ie);
		}
		finally {
			System.out.println("Main thread wakes up");
		}

		child_object.setDone();

                for(int i = 0 ; i < 12 ; ++i) {

                        rethreads[i].interrupt();
                }

                for(int i = 0 ; i < 12 ; ++i) {

		try {	

                       rethreads[i].join();
    //                    child_thread2.join();
                }
                catch(Exception xe) {
                        System.out.println(xe);
                }
		}

			
	
	}



}

