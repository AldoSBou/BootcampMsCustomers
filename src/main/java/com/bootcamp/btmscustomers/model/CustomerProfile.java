package com.bootcamp.btmscustomers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer_profiles")
public class CustomerProfile {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    @Field
    private String description;
}
