/**
 * 
 */
package com.walmart.ticket.model;

/**
 * @author sohrab
 *
 */
public class SeatPosition {
	private String rowLetter;
	private int seatNumber;

	public SeatPosition(String rowLetter, int seatNumber) {
		super();
		this.rowLetter = rowLetter;
		this.seatNumber = seatNumber;
	}

	public String getRowLetter() {
		return rowLetter;
	}

	public void setRowLetter(String rowLetter) {
		this.rowLetter = rowLetter;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	@Override
	public String toString() {
		return rowLetter + seatNumber;
	}

}
