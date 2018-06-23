/**
 * 
 */
package com.walmart.ticket.utils;

import org.apache.commons.validator.routines.EmailValidator;

import com.walmart.ticket.model.Seat;
import com.walmart.ticket.model.SeatHold;

/**
 * @author sohrab
 *
 */
public class TicketUtils {

	public static String generateReservationId(SeatHold hold) {
		StringBuilder sb = new StringBuilder();
		sb.append("Congrats! Your seats have been reserved!\n");
		sb.append("seats: [ ");
		for (Seat st : hold.getSeatsHeld()) {
			sb.append(st.getSeatPosition()).append(" , ");
		}
		sb.append("]");
		return sb.toString();
	}

	public static boolean validateNumber(String number) {
		if (number == null || number.trim().length() == 0) {
			return false;
		}
		try {
			Integer.valueOf(number);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean validateCustomer(String currEmail, String actualEmail) {
		return currEmail.equalsIgnoreCase(actualEmail);
	}

	public static boolean validateEmail(String email) {
		if (email == null || email.trim().length() == 0) {
			return false;
		}
		email = email.trim();
		EmailValidator emailValidator = EmailValidator.getInstance();
		return emailValidator.isValid(email);
	}

}
