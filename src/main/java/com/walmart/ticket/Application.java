/**
 * 
 */
package com.walmart.ticket;

import java.util.Scanner;

import com.walmart.ticket.model.SeatHold;
import com.walmart.ticket.model.Venue;
import com.walmart.ticket.service.TicketService;
import com.walmart.ticket.service.impl.TicketServiceImpl;
import com.walmart.ticket.utils.TicketUtils;

/**
 * @author sohrab
 *
 */
public class Application {
	public static long expirePeriod = 300;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ticket Service Management\n\n");
		String options = "\nSelect any option to proceed:\n1. Initialize Venue \t2. Set seat expiration period \t3. Available Seats \t4. Request to Hold Seats \t5. Reserve Seats \t6. Exit.";
		boolean flag = true;
		int rows = 0, seatsPerRow = 0;
		TicketService service = null;
		Venue venue = null;
		while (flag) {
			System.out.println(options);
			String optionStr = scanner.next();
			boolean isValidOption = TicketUtils.validateNumber(optionStr);
			if (!isValidOption) {
				System.out.println("Select numeric value for options");
				continue;
			}
			int option = Integer.parseInt(optionStr);
			switch (option) {
			case 1:
				System.out.println("Please enter number of rows");
				String rowStr = scanner.next();
				boolean isvalidRow = TicketUtils.validateNumber(rowStr);
				if (!isvalidRow) {
					System.out.println("Enter numeric value for rows");
					continue;
				}
				rows = Integer.valueOf(rowStr);

				System.out.println("Please enter number of seats per rows");
				String seatsPerRowStr = scanner.next();
				boolean isValidSeatsPerRow = TicketUtils.validateNumber(seatsPerRowStr);
				if (!isValidSeatsPerRow) {
					System.out.println("Enter numeric value for number of seats per row");
					continue;
				}
				seatsPerRow = Integer.valueOf(seatsPerRowStr);

				venue = new Venue(rows, seatsPerRow);
				service = new TicketServiceImpl(venue);
				break;
			case 2:
				System.out.println("Please enter seat expiration period in seconds");
				String expirePeriodStr = scanner.next();
				boolean isvalidExpirePeriod = TicketUtils.validateNumber(expirePeriodStr);
				if (!isvalidExpirePeriod) {
					System.out.println("Enter numeric value for expire period");
					continue;
				}
				expirePeriod = Integer.valueOf(expirePeriodStr);
				break;
			case 3:
				System.out.println("\nNumber of available seats =  " + service.numSeatsAvailable());
				break;
			case 4:
				System.out.println("Please enter number of seats to hold");
				String seatsHoldStr = scanner.next();
				boolean isValidSeatsHold = TicketUtils.validateNumber(seatsHoldStr);
				if (!isValidSeatsHold) {
					System.out.println("Enter numeric value for number of seats to hold");
					continue;
				}
				int seatsHold = Integer.valueOf(seatsHoldStr);

				System.out.println("Please enter customer email");
				String email = scanner.next();
				boolean isValidEmail = TicketUtils.validateEmail(email);
				if (!isValidEmail) {
					System.out.println("Enter valid email");
					continue;
				}
				SeatHold seatHold = service.findAndHoldSeats(seatsHold, email);
				if (seatHold != null) {
					System.out.println("\nNumber of Seats hold = " + seatsHold + "\n" + seatHold);
				} else {
					System.out.println("\nYour request is rejected");
				}
				break;
			case 5:
				System.out.println("Please enter seat hold id");
				String seatsHoldIdStr = scanner.next();
				boolean isValidSeatsHoldId = TicketUtils.validateNumber(seatsHoldIdStr);
				if (!isValidSeatsHoldId) {
					System.out.println("Enter numeric value for seat hold id");
					continue;
				}
				int seatsHoldId = Integer.valueOf(seatsHoldIdStr);

				System.out.println("Please enter associated customer email");
				String associatedEmail = scanner.next();
				boolean isValidAssociatedEmail = TicketUtils.validateEmail(associatedEmail);
				if (!isValidAssociatedEmail) {
					System.out.println("Enter valid associated email");
					continue;
				}
				System.out.println("\n" + service.reserveSeats(seatsHoldId, associatedEmail));
				break;
			case 6:
				flag = false;
				System.out.println("\nExit !!");
				break;
			default:
				System.out.println("Enter valid option");
			}
		}
		scanner.close();
	}
}
