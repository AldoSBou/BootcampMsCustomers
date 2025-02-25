package com.bootcamp.btmscustomers.repository;

import com.bootcamp.btmscustomers.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ICustomerRepository extends IGenericRepository<Customer, String> {
}
