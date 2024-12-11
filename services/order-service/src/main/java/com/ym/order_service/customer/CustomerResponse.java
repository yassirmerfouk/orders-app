package com.ym.order_service.customer;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerResponse {
    @EqualsAndHashCode.Include
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
