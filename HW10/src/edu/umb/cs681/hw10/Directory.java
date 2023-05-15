package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {

	private LinkedList<FSElement> children = new LinkedList<FSElement>();
	private LinkedList<File> files = new LinkedList<File>();
	private LinkedList<Directory> subdir = new LinkedList<Directory>();

	public Directory(Directory parent, String name, int size, LocalDateTime creationTime, String ownerName,
			LocalDateTime lastModifiedTime) {
		super(parent, name, 0, creationTime);
	}

	public LinkedList<FSElement> getChildren() {
		this.lock.lock();
		try{
		return this.children;
		}
		finally{
			this.lock.unlock();
		}
	}

	public void appendChild(FSElement child) {
		this.lock.lock();
		try{
			this.children.add(child);
			child.setParent(this);
		}
		finally{
			this.lock.unlock();
		}
	}

	public int countChildren() {
		this.lock.lock();
		try{
			return this.children.size();
		}
		finally{
			this.lock.unlock();
		}
	}

	public LinkedList<Directory> getSubDirectories() {
		this.lock.lock();
		try{
		for (FSElement element : children) {
			if (element.isDirectory()) {
				subdir.add((Directory) element);
			}
		}
		return subdir;
		}
		finally{
			this.lock.unlock();
		}
	}

	public LinkedList<File> getFiles() {
		this.lock.lock();
		try{
		for (FSElement element : children) {
			if (element.isFile()) {
				files.add((File) element);
			}
		}
		return files;
		}
		finally{
			this.lock.unlock();
		}
	}

	public int getTotalSize() {
		this.lock.lock();
		try{
		int totalSize = 0;
		for (FSElement element : children) {
			if (element.isDirectory()) {
				totalSize += ((Directory) element).getTotalSize();
			} else {
				totalSize += element.getSize();
			}
		}
		return totalSize;
		}
		finally{
			this.lock.unlock();
		}
	}

	public boolean isDirectory() {
		return true;
	}

	public boolean isFile() {
		return false;
	}

	public boolean isLink() {
		return false;
	}
}
