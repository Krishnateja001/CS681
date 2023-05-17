package edu.umb.cs681.hw19;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.*;

public abstract class Observable<T> {
    private ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();

   // private static ReentrantLock lockObs = new ReentrantLock();

    public void addObserver(Observer<T> o) {
        observers.add(o);
    }

    public void clearObservers() {
        observers.clear();

    }
    public ConcurrentLinkedQueue<Observer<T>> getObservers(){
        return observers;
    }

    public int countObservers() {
        return observers.size();

    }
    public void removeObserver(Observer<T> o) {
        observers.remove(o);
    }

    public void notifyObservers(T event) {
	
	ConcurrentLinkedQueue<Observer<T>> observerslocal = new ConcurrentLinkedQueue<>();
	observerslocal = observers;
        observerslocal.forEach( (observerlocal)->{observerlocal.update(this, event);} );
    }

}
