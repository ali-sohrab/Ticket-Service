/**
 * 
 */
package com.walmart.ticket.model;

/**
 * @author sohrab
 *
 */
public class Customer {
	private String email;

	public Customer(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return email;
	}
}
