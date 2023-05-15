package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement{	
	private String name;
	private int size;
	private LocalDateTime creationTime;
	private FSElement parent;

	public ReentrantLock lock = new ReentrantLock();
	
	public FSElement(FSElement parent, String name, int size, LocalDateTime creationTime) {
		if (parent != null) {
			parent.appendChild(this);
		} else {
			this.parent = null;
		}
		this.setName(name);
		this.setSize(size);
		this.setCreationTime(creationTime);
	}
	
	public FSElement getParent() {
		this.lock.lock();
		try {
			return this.parent;
		}
		finally{
			this.lock.unlock();
		}
	}

	public int getSize() {
		this.lock.lock();
		try {
			return this.size;
		}
		finally{
			this.lock.unlock();
		}

	}
	
	public String getName() {
		this.lock.lock();
		try {
			return this.name;
		}
		finally{
			this.lock.unlock();
		}

	}
	
	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setParent(FSElement parent) {
		this.lock.lock();
		try {
			this.parent = parent;
		}
		finally{
			this.lock.unlock();
		}


	}

	public void setSize(int size) {
		this.lock.lock();
		try {
			if (isDirectory()) {
				this.size = 0;
			} else {
				this.size = size;
			}
		}
		finally {
			this.lock.unlock();
		}
	}

	public void setName(String name) {
		this.lock.lock();
		try {
			this.name = name;
		}
		finally{
			this.lock.unlock();
		}	
	}
	
	public void setCreationTime(LocalDateTime creationTime) {
		
		this.lock.lock();
		try {
			this.creationTime = creationTime;
		}
		finally{
			this.lock.unlock();
		}

	}
	protected abstract void appendChild(FSElement parent);
	abstract public boolean isDirectory();
	abstract public boolean isFile();
	abstract public boolean isLink();
}
