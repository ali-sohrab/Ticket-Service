/**
 * 
 */
package com.walmart.ticket.model;

/**
 * @author sohrab
 *
 */
public class Seat {
	SeatPosition seatPosition;
	Customer reservedBy;
	SeatStatus seatStatus;

	public Seat(SeatPosition seatPosition, SeatStatus seatStatus) {
		this.seatPosition = seatPosition;
		this.seatStatus = seatStatus;
	}

	public SeatPosition getSeatPosition() {
		return seatPosition;
	}

	public void setSeatPosition(SeatPosition seatPosition) {
		this.seatPosition = seatPosition;
	}

	public Customer getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(Customer reservedBy) {
		this.reservedBy = reservedBy;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}
	
}
