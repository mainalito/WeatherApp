package com.weatherapp.models;


public class Current {
	private double temp_c;
	private double temp_f;
	private int humidity;
	private Condition condition;
	public double getTemp_c() {
		return temp_c;
	}
	public void setTemp_c(double temp_c) {
		this.temp_c = temp_c;
	}
	public double getTemp_f() {
		return temp_f;
	}
	public void setTemp_f(double temp_f) {
		this.temp_f = temp_f;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	
}
