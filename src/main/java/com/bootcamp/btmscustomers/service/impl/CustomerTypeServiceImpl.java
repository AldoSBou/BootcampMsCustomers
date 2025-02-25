package com.bootcamp.btmscustomers.service.impl;

import com.bootcamp.btmscustomers.model.CustomerType;
import com.bootcamp.btmscustomers.repository.ICustomerTypeRepository;
import com.bootcamp.btmscustomers.repository.IGenericRepository;
import com.bootcamp.btmscustomers.service.ICustomerTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CustomerTypeServiceImpl extends GenericServiceImpl<CustomerType,String> implements ICustomerTypeService {

    private final ICustomerTypeRepository customerTypeRepository;

    @Override
    protected IGenericRepository<CustomerType, String> getRepository() {
        return customerTypeRepository;
    }
}
