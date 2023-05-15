import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class Checker implements Runnable{
	
	private ReentrantLock lock = new ReentrantLock();

	public FileSystem handle = null;

	public void run() {
			
		handle = FileSystem.getFileSystem();
		System.out.println("The address of file system object I got hold of is : "+handle);
	}	

	public static void main(String ... args) {
	
		Checker child_object = new Checker();

		Thread child_thread1 = new Thread(child_object);
		Thread child_thread2 = new Thread(child_object);
		
		child_thread1.start();
		child_thread2.start();
		
		try {
                        child_thread1.join();
                        child_thread2.join();
                }
                catch(Exception e) {
                        System.out.println(e);
                }

			
	
	}



}

