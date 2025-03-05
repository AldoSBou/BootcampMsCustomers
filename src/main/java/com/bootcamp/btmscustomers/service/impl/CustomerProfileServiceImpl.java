package com.bootcamp.btmscustomers.service.impl;

import com.bootcamp.btmscustomers.model.CustomerProfile;
import com.bootcamp.btmscustomers.repository.ICustomerProfileRepository;
import com.bootcamp.btmscustomers.repository.ICustomerRepository;
import com.bootcamp.btmscustomers.repository.IGenericRepository;
import com.bootcamp.btmscustomers.service.ICustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerProfileServiceImpl extends GenericServiceImpl<CustomerProfile,String> implements ICustomerProfileService {

    private final ICustomerProfileRepository repository;

    @Override
    protected IGenericRepository<CustomerProfile, String> getRepository() {
        return repository;
    }
}
