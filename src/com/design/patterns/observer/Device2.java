package com.design.patterns.observer;

public class Device2 implements Observer {
	
	private double temperature; 
	
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
		this.temperature = weatherDataVO.getTemp();
		System.out.println("Temperature today is " + this.temperature);
	}

}
