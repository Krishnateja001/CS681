package edu.umb.cs681.hw07;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.time.LocalDateTime;

public class FileSystem {
	
	private static ReentrantLock lock = new ReentrantLock();
	protected static String name;
	protected static int capacity;
	protected static int id;
	protected static Directory root;

        private static LocalDateTime date = LocalDateTime.now();

	private LinkedList<FSElement> rootDirs = new LinkedList<FSElement>();;

	public FSElement initFileSystem(String name, int capacity) {
		
		FileSystem.name = name;
		FileSystem.capacity = capacity;

		FSElement root = createDefaultRoot();
		
		if (root.isDirectory() && capacity >= root.getSize()) {
			
			setRoot(root);
			FileSystem.id = root.hashCode();
			return root;
		} else {
			return null;
		}
	}


	private FileSystem(){
	
		initFileSystem("Admin", 10);
	}
	
	private static FileSystem instance = null;
	
	public static FileSystem getFileSystem() {

		lock.lock();
		try{
			if (instance == null) {
				instance = new FileSystem();
			}
			return instance;
		}
		finally {
			lock.unlock();
		}
	}
	
	public Directory getRootDir() {
		return root;
	}

	protected FSElement createDefaultRoot() {

		root = new Directory(null, name, 0, date, "Admin", date);
                return root;

	}

	protected void setRoot(FSElement root) {
		rootDirs.add(root);
	}

	public static String getName() {
		return name;
	}

	public static int getCapacity() {
		return capacity;
	}

	public static int getId() {
		return id;
	}
}
