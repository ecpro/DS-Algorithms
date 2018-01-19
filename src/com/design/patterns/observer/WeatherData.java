package com.design.patterns.observer;

import java.util.HashSet;
import java.util.Set;

public class WeatherData implements MyObservable {
	
	private Set<Observer> observers;
	private WeatherDataVO weatherDataVO;
	
	
	public WeatherData(double temperature, double humidity, double pressure) {
		this.weatherDataVO = new WeatherDataVO();
		observers = new HashSet<Observer>();
		this.weatherDataVO.setHumidity(humidity);
		this.weatherDataVO.setPressure(pressure);
		this.weatherDataVO.setTemp(temperature);
	}
	
	public WeatherData() {
		this.observers = new HashSet<Observer>();
	}
	
	public double getTemperature(){
		return 0;
	}
	
	public double getHumidity() {
		return 0;
	}
	
	public double getPressure() {
		return 0;
	}
	
	public void measurementChanged() {
		this.weatherDataVO.setHumidity(34);
		this.weatherDataVO.setPressure(54);
		this.weatherDataVO.setTemp(11);
		System.out.println("measurement changed");
		this.notifyObserver();
	}

	@Override
	public void addObserver(Observer ob) {
		this.observers.add(ob);
	}

	@Override
	public void removeObserver(Observer ob) {
		if(this.observers.contains(ob)) this.observers.remove(ob);
	}

	@Override
	public void notifyObserver() {
		for(Observer ob : this.observers) {
			ob.update(this.weatherDataVO);
		}
	} 
	
	public static void main(String[] args) throws InterruptedException {
		MyObservable myObservable = new WeatherData(23.12, 12.35, 32);
		
		Observer ob1 = new Device1();
		Observer ob2 = new Device2();
		
		ob1.subscribe(myObservable);
		ob2.subscribe(myObservable);
		myObservable.notifyObserver();
		
		ob2.unsubscribe(myObservable);
		Thread.sleep(2000);
		((WeatherData) myObservable).measurementChanged();
	}
}
