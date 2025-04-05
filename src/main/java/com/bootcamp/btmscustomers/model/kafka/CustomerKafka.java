package com.bootcamp.btmscustomers.model.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerKafka {

    private String username;
    private String password;
    private List<RolesKafka> roles;
    private String name;
    private String lastName;
    private String email;
    private String documentNumber;
    private CustomerTypeKafka costumerType;
    private String customerProfile;
}
