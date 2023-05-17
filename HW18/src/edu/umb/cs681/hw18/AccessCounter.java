package edu.umb.cs681.hw18;

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
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
public class AccessCounter {

	private AccessCounter() {
		

	}

	private static AccessCounter sin = null;

	private ConcurrentHashMap<Path, AtomicInteger> map = new ConcurrentHashMap<>();
        private ReentrantLock map_lock = new ReentrantLock();


        private static ReentrantLock inst_lock = new ReentrantLock();

	public static synchronized AccessCounter getInstance() {
	
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
	
	public void increment(Path path) {

		map.computeIfPresent(path, (key,val)-> {
		    val.incrementAndGet();
		    return val;
		});
		map.computeIfAbsent(path,(key)->{return new AtomicInteger(1);});	

	}
	
	public int getCount(Path path) {
		
		AtomicInteger count = map.getOrDefault(path, new AtomicInteger(0));
		return count.get();	
	}	

}


