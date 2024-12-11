package com.ym.customer_service.customer;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor  @AllArgsConstructor @Setter @Getter @Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String houseNumber;
    private String zipCode;

}
