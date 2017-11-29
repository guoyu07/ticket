package com.ticket.pojo;

import java.util.ArrayList;
import java.util.List;

public class Report {

	private List<ValidationReport> validationReport;
	
	private List<ValidationDetailReport> validationDetailReport;
	
	public List<ValidationReport> getValidationReport() {
		return validationReport;
	}

	public void setValidationReport(List<ValidationReport> validationReport) {
		this.validationReport = validationReport;
	}

	public List<ValidationDetailReport> getValidationDetailReport() {
		return validationDetailReport;
	}

	public void setValidationDetailReport(
			List<ValidationDetailReport> validationDetailReport) {
		this.validationDetailReport = validationDetailReport;
	}

	public static List<Report> get(){
		
		Report report = new Report();
		List<ValidationReport> list = new ArrayList<ValidationReport>();
		for(int i = 0; i < 10; i++){

			ValidationReport vr = new ValidationReport("" + i, "" + i, "" + i, "" + i, i, new Double(i), i + 10, i + 20);
			list.add(vr);
		}
		report.setValidationReport(list);
		List<ValidationDetailReport> list2 = new ArrayList<ValidationDetailReport>();
		for(int i = 0; i < 10; i++){
			
			ValidationDetailReport vr = new ValidationDetailReport("" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, i);
			list2.add(vr);
		}
		report.setValidationDetailReport(list2);
		
		ArrayList<Report> reports = new ArrayList<Report>();
		reports.add(report);
		return reports;
	}
}
