/**
 * 
 */
package com.walmart.ticket.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.walmart.ticket.Application;
import com.walmart.ticket.model.SeatHold;
import com.walmart.ticket.model.Venue;

/**
 * @author sohrab
 *
 */

public class TicketServiceImplTest {
	private TicketServiceImpl service;
	private int wait = 6000;

	@Before
	public void setUp() throws Exception {
		service = new TicketServiceImpl(new Venue(5, 5));
		Application.expirePeriod = 5;
	}

	@Test
	public void numSeatsAvailable() throws InterruptedException {
		int available = service.numSeatsAvailable();
		assert (available == 5 * 5);

		service.findAndHoldSeats(2, "ali@venue.com");
		available = service.numSeatsAvailable();
		assert (available == ((5 * 5) - 2));

		Thread.sleep(wait);
		System.out.println("After waiting: " + service.numSeatsAvailable());
		assert ((5 * 5) == service.numSeatsAvailable());

		SeatHold sh = service.findAndHoldSeats((5 * 5), "ali@venue.com");
		available = service.numSeatsAvailable();
		service.reserveSeats(sh.getId(), "ali@venue.com");
		assert (available == service.numSeatsAvailable());
	}

	@Test
	public void findAndHoldSeats() throws InterruptedException {
		SeatHold s1 = service.findAndHoldSeats(1, "ali@venue.com");
		assertNotNull(s1);
		assert (1 == s1.getSeatsHeld().size());
		int available = service.numSeatsAvailable();
		assert (available == ((5 * 5) - 1));
		Thread.sleep(wait);
		available = service.numSeatsAvailable();

		s1 = service.findAndHoldSeats(1, "ali@venue.com");
		assertNotNull(s1);
		assert (1 == s1.getSeatsHeld().size());
		Thread.sleep(wait);
		s1 = service.findAndHoldSeats(2, "ali@venue.com");
		assert (2 == s1.getSeatsHeld().size());
		available = service.numSeatsAvailable();
		assert (available == ((5 * 5) - 2));
	}

	@Test
	public void reserveSeats() throws InterruptedException {
		SeatHold s1 = service.findAndHoldSeats(25, "ali@venue.com");
		assertNotNull(s1);
		SeatHold s2 = service.findAndHoldSeats(1, "ali@venue.com");
		assertNull(s2);
		String reservationId = service.reserveSeats(s1.getId(), "ali@venue.com");
		assertNotNull(reservationId);
		assertTrue(reservationId.contains("Congrats!"));
		reservationId = service.reserveSeats(0, "ali@venue.com");
		assert (reservationId.equals("SeatHoldId is invalid OR expired"));
	}
}
