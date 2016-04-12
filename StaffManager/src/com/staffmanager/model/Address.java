package com.staffmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private int addressId;
	private String city;
	private String addressLine;
	
	public Address() {
    }
	
	public Address(int addressId, String city, String addressLine) {
	    this.addressId = addressId;
	    this.city = city;
	    this.addressLine = addressLine;
    }

	public int getAddressId() {
		return addressId;
	}
	
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getAddressLine() {
		return addressLine;
	}
	
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", city=" + city
				+ ", addressLine=" + addressLine + "]";
	}
}
