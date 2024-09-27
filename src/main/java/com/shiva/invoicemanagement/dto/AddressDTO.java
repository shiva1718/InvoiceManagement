package com.shiva.invoicemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.shiva.invoicemanagement.entities.Address;

@Getter
@NoArgsConstructor
public class AddressDTO {
	private Long id;

	private String street;
	private String city;
	private String state;
	private String pincode;

	public AddressDTO(Address address) {
		this.id = address.getId();
		this.street = address.getStreet();
		this.city = address.getCity();
		this.state = address.getState();
		this.pincode = address.getPincode();
	}


}
