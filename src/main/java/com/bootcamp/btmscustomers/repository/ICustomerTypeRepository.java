package com.bootcamp.btmscustomers.repository;

import com.bootcamp.btmscustomers.model.CustomerType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ICustomerTypeRepository extends IGenericRepository<CustomerType, String> {
}
