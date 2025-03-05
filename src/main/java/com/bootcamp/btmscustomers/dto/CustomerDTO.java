package com.bootcamp.btmscustomers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private String documentNumber;
    private CustomerTypeDTO customerType;
    private String customerProfile;
}
