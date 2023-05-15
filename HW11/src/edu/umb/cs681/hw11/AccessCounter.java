package edu.umb.cs681.hw11;

import java.util.*;
import java.nio.*;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {



	protected AccessCounter() {
		

	}

	private static AccessCounter sin = null;

	HashMap<Path, Integer> map = new HashMap<>();
        private ReentrantLock map_lock = new ReentrantLock();


        private static ReentrantLock inst_lock = new ReentrantLock();

	public static AccessCounter getInstance() {
	
		inst_lock.lock();
		try{
			
			if(sin == null) {
				
				sin = new AccessCounter();
				return sin;
			}
			else 
				return sin;
		}
		finally {
		
			inst_lock.unlock();
		
		}
	
	}
	
	void increment(Path path) {
	
		map_lock.lock();
		try {
			map.merge(path, 1, Integer::sum);
		}
		finally {
			
			map_lock.unlock();
		}
	}
	
	int getCount(Path path) {
		
		int count = 0;
		map_lock.lock();
		try {
			if(map.containsKey(path)) {
				count = map.get(path);
				return count;
			}
			else
				return 0;
		}
		finally{
			
			map_lock.unlock();

		}
	
	}	

}



