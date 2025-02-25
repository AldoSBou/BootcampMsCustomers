package com.bootcamp.btmscustomers.service;

import com.bootcamp.btmscustomers.model.CustomerType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerTypeService extends IGenericService<CustomerType,String> {
}
