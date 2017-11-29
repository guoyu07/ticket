package com.ticket.report;

import java.util.List;

public class Project {

	protected String name;
	protected double average;
	protected double average2;
	protected List<Target> target;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public List<Target> getTarget() {
		return target;
	}
	public void setTarget(List<Target> target) {
		this.target = target;
	}
	public double getAverage2() {
		return average2;
	}
	public void setAverage2(double average2) {
		this.average2 = average2;
	}
}
