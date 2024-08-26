package com.shiva.invoicemanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "id")
public class Address {

    @Id
    private Long id;

    private String street;
    private String city;
    private String state;
    private String pincode;

}
