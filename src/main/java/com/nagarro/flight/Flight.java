package com.nagarro.flight;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * 
 * @author mayankgangwar
 * class to store flight data
 */
public class Flight {

	private String flightNo;
	private String depLoc;
	private String arrLoc;
	private Date validTill;
	private String flightTime;
	private double flightDur;
	private double fare;
	private char seatAva;
	private String flightClass;
	
	public Flight() {}
	
	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getDepLoc() {
		return depLoc;
	}
	public void setDepLoc(String depLoc) {
		this.depLoc = depLoc;
	}
	public String getArrLoc() {
		return arrLoc;
	}
	public void setArrLoc(String arrLoc) {
		this.arrLoc = arrLoc;
	}
	public Date getValidTill() {
		return validTill;
	}
	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}
	public String getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}
	public double getFlightDur() {
		return flightDur;
	}
	public void setFlightDur(double flightDur) {
		this.flightDur = flightDur;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public char getSeatAva() {
		return seatAva;
	}
	public void setSeatAva(char seatAva) {
		this.seatAva = seatAva;
	}
	public String getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
	
	@Override
	public String toString() {
		DecimalFormat dec = new DecimalFormat("#0.00");
		
		return "flightNo=" + flightNo + ", depLoc=" + depLoc + ", arrLoc=" + arrLoc + ", validTill="
				+ validTill + ", flightTime=" + flightTime + ", flightDur=" + flightDur + ", fare=" + dec.format(fare)
				+ ", seatAva=" + seatAva + ", flightClass=" + flightClass;
	}
}
