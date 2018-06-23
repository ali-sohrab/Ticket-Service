/**
 * 
 */
package com.walmart.ticket.model;

/**
 * @author sohrab
 *
 */
public class Venue {
	private int rows;
	private int seatsPerRow;
	private Seat[][] seatMatrix;
	private int capacity;

	public Venue(int rows, int seatsPerRow) {
		super();
		this.rows = rows;
		this.seatsPerRow = seatsPerRow;
		this.capacity = (this.rows * this.seatsPerRow);
		seatMatrix = new Seat[rows][seatsPerRow];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < seatsPerRow; j++) {
				seatMatrix[i][j] = new Seat(new SeatPosition(getRowLetter(i), j), SeatStatus.AVAILABLE);
			}
		}
	}

	private String getRowLetter(int n) {
		StringBuilder result = new StringBuilder();

		while (n > 0) {
			n--;
			result.insert(0, (char) ('A' + n % 26));
			n /= 26;
		}

		return result.toString();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	public void setSeatsPerRow(int seatsPerRow) {
		this.seatsPerRow = seatsPerRow;
	}

	public Seat[][] getSeatMatrix() {
		return seatMatrix;
	}

	public void setSeatMatrix(Seat[][] seatMatrix) {
		this.seatMatrix = seatMatrix;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
