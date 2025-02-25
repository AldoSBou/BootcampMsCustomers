package com.bootcamp.btmscustomers.service;

import com.bootcamp.btmscustomers.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService extends IGenericService<Customer,String>{
    Flux<Customer> getByCustomerType(String customerType);
}
