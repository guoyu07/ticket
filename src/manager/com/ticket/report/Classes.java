package com.ticket.report;

import java.util.List;

public class Classes {

	protected String name;
	protected double average;
	protected double average2;
	protected List<Project> project;
	
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
	public List<Project> getProject() {
		return project;
	}
	public void setProject(List<Project> project) {
		this.project = project;
	}
	public double getAverage2() {
		return average2;
	}
	public void setAverage2(double average2) {
		this.average2 = average2;
	}
}
