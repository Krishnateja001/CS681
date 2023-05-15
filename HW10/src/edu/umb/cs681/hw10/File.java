package edu.umb.cs681.hw10;

import java.time.LocalDateTime;

public class File extends FSElement {

	public File(Directory parent, String name, int size, LocalDateTime creationTime, String ownerName,
			LocalDateTime lastModifiedTime) {
		super(parent, name, size, creationTime);
	}
	
	public void appendChild(FSElement child) {
		//this.children.add(child);
		child.setParent(this);
	}
	
	public boolean isDirectory() {
		return false;
	}

	public boolean isFile() {
		return true;
	}

	public boolean isLink() {
		return false;
	}
}