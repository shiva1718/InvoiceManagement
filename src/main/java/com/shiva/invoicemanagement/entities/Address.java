package com.shiva.invoicemanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import com.shiva.invoicemanagement.dto.AddressDTO;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "id")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String pincode;

    public Address(AddressDTO address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.pincode = address.getPincode();
    }

}
