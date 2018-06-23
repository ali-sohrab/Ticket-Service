/**
 * 
 */
package com.walmart.ticket.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.walmart.ticket.Application;
import com.walmart.ticket.model.Customer;
import com.walmart.ticket.model.Seat;
import com.walmart.ticket.model.SeatHold;
import com.walmart.ticket.model.SeatStatus;
import com.walmart.ticket.model.Venue;
import com.walmart.ticket.service.TicketService;
import com.walmart.ticket.utils.TicketUtils;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

/**
 * @author sohrab
 *
 */
public class TicketServiceImpl implements TicketService {
	private int available;
	private Venue venue;
	private ExpiringMap<Integer, SeatHold> seatHoldMapper;

	public TicketServiceImpl(Venue venue) {
		super();
		this.venue = venue;
		available = venue.getCapacity();
		seatHoldMapper = ExpiringMap.builder().variableExpiration().asyncExpirationListener((seatHoldId, seatHold) -> {
			((SeatHold) seatHold).getSeatsHeld().forEach((seat) -> {
				seat.setSeatStatus(SeatStatus.AVAILABLE);
				this.available++;
			});
		}).build();
	}

	@Override
	public int numSeatsAvailable() {
		// randomExpiryCheck();
		return available;
	}

	/*
	 * private void randomExpiryCheck() { for (int seatHoldId :
	 * seatHoldMapper.keySet()) { SeatHold seatHold =
	 * seatHoldMapper.get(seatHoldId); if (!seatHold.isValid()) {
	 * updateSeatStatus(seatHold.getSeatsHeld(), SeatStatus.AVAILABLE);
	 * this.available += seatHold.getSeatsHeld().size();
	 * seatHoldMapper.remove(seatHoldId); } } }
	 */

	private void updateSeatStatus(List<Seat> seatsHeld, SeatStatus seatStatus) {
		for (Seat seat : seatsHeld) {
			seat.setSeatStatus(seatStatus);
		}
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		// randomExpiryCheck();
		List<Seat> holdSeats = findBestAvailableSeats(numSeats);
		if (holdSeats.isEmpty())
			return null;
		updateSeatStatus(holdSeats, SeatStatus.HOLD);
		this.available -= holdSeats.size();
		SeatHold seatHold = getSeatHold(holdSeats, customerEmail);
		if (seatHold != null) {
			seatHoldMapper.put(seatHold.getId(), seatHold, ExpirationPolicy.CREATED, Application.expirePeriod,
					TimeUnit.SECONDS);
		}
		return seatHold;
	}

	private List<Seat> findBestAvailableSeats(int numSeats) {
		if (available < numSeats) {
			System.out.println("Number of seats available now are " + available);
			return Collections.emptyList();
		}
		Seat[][] seats = venue.getSeatMatrix();
		List<Seat> bestSeats = new ArrayList<Seat>();
		for (int i = venue.getRows() - 1; i >= 0; i--) {
			for (int j = 0; j < venue.getSeatsPerRow(); j++) {
				Seat st = seats[i][j];
				if (SeatStatus.AVAILABLE == st.getSeatStatus()) {
					bestSeats.add(st);
					if (--numSeats == 0) {
						return bestSeats;
					}
				}
			}
		}
		return bestSeats;
	}

	private SeatHold getSeatHold(List<Seat> holdSeats, String customerEmail) {
		if (holdSeats.size() < 1) {
			return null;
		}
		SeatHold hold = new SeatHold(Application.expirePeriod);
		hold.setCustomer(new Customer(customerEmail));
		hold.setSeatsHeld(holdSeats);
		return hold;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {
		SeatHold seatHold = seatHoldMapper.get(seatHoldId);
		if (seatHold == null) {
			return "SeatHoldId is invalid OR expired";
		}
		boolean isValidCustomer = TicketUtils.validateCustomer(customerEmail, seatHold.getCustomer().getEmail());
		if (!isValidCustomer) {
			return "Customer is not verified, please enter correct email";
		}
		updateSeatStatus(seatHold.getSeatsHeld(), SeatStatus.RESERVED);
		String result = TicketUtils.generateReservationId(seatHold);
		seatHoldMapper.remove(seatHoldId);
		return result;
	}
}
