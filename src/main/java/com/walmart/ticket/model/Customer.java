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
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return email;
	}
}
