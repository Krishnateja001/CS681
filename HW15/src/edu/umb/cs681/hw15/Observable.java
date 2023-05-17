package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T> {
    private LinkedList<Observer<T>> observers = new LinkedList<>();

    private static ReentrantLock lockObs = new ReentrantLock();

    public void addObserver(Observer<T> o) {
	lockObs.lock();
        observers.add(o);
	lockObs.unlock();
    }

    public void clearObservers() {
	lockObs.lock();
        observers.clear();
	lockObs.unlock();

    }
    public List<Observer<T>> getObservers(){
        return observers;
    }

    public int countObservers() {
        return observers.size();

    }
    public void removeObserver(Observer<T> o) {
	lockObs.lock();
        observers.remove(o);
	lockObs.unlock();
    }

    public void notifyObservers(T event) {
	
	LinkedList<Observer<T>> observerslocal = new LinkedList<>();
	lockObs.lock();
	try {
		observerslocal = observers;
	}
	finally {
		lockObs.unlock();
	}
        observerslocal.forEach( (observerlocal)->{observerlocal.update(this, event);} );
    }

}
