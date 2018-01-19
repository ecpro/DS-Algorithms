package com.design.patterns.observer;

public interface MyObservable {
	
	public void addObserver(Observer ob);
	public void removeObserver(Observer ob);
	public void notifyObserver();
}
