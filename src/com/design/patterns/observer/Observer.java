package com.design.patterns.observer;

public interface Observer {
	void update(final WeatherDataVO weatherDataVO);
	void subscribe(MyObservable ob);
	void unsubscribe(MyObservable ob);
}