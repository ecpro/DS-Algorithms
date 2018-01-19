package com.design.patterns.observer;

public class Device1 implements Observer {
	
	private double humidity;
	
	@Override
	public void subscribe(MyObservable myOb) {
		myOb.addObserver(this);
	}
	
	@Override
	public void unsubscribe(MyObservable myOb) {
		myOb.removeObserver(this);
	}

	@Override
	public void update(WeatherDataVO weatherDataVO) {
		this.humidity = weatherDataVO.getHumidity();
		System.out.println("Humidity today is" + humidity);
	}

}
