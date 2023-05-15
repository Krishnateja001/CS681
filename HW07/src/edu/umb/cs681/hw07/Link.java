package edu.umb.cs681.hw07;
import java.time.LocalDateTime;

public class Link extends FSElement {

	private FSElement target;

	public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target,
			String ownerName, LocalDateTime lastModifiedTime) {
		super(parent, name, size, creationTime);
		this.target = target;
	}

	public FSElement getTarget() {
		return this.target;
	}

	public int getTargetSize() {
		return target.getSize();
	}
	
	public boolean targetisDirectory() {
		return target.isDirectory();
	}

	public boolean targetisFile() {
		return target.isFile();
	}

	public boolean targetisLink() {
		return target.isLink();
	}

	public void setTarget(FSElement target) {
		this.target = target;
	}

	public boolean isDirectory() {
		return false;
	}

	public boolean isFile() {
		return false;
	}

	public void appendChild(FSElement child) {
		//this.children.add(child);
		child.setParent(this);
	}
	public boolean isLink() {
		return true;
	}
}
