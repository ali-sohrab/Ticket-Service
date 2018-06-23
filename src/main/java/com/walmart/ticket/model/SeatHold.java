/**
 * 
 */
package com.walmart.ticket.model;

import java.time.Instant;
import java.util.List;

/**
 * @author sohrab
 *
 */
public class SeatHold {
	private static int count = 0;
	private int id;
	private List<Seat> seatsHeld;
	private Customer customer;
	private Instant bookedAt;
	private Instant validTill;

	public SeatHold(long validity) {
		setId(++count);
		bookedAt = Instant.now();
		validTill = bookedAt.plusSeconds(validity);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public List<Seat> getSeatsHeld() {
		return seatsHeld;
	}

	public void setSeatsHeld(List<Seat> seatsHeld) {
		this.seatsHeld = seatsHeld;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Instant getBookedAt() {
		return bookedAt;
	}

	public Instant getValidTill() {
		return validTill;
	}

	public boolean isValid() {
		return Instant.now().isBefore(validTill);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SeatHold [").append("id : ").append(id).append(", ");
		if (customer != null)
			builder.append("email : ").append(customer).append(", ");
		if (bookedAt != null)
			builder.append("hold at : ").append(bookedAt).append(", ");
		if (validTill != null)
			builder.append("reserve till : ").append(validTill).append(", ");
		if (seatsHeld != null) {
			builder.append(seatsHeld.size() + " seats held: [");
			for (Seat st : seatsHeld) {
				builder.append(st.getSeatPosition());
				builder.append(" ");
			}
			builder.append("]");
		}
		builder.append("]");
		return builder.toString();
	}
}
