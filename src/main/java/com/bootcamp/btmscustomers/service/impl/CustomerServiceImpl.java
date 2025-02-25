package com.bootcamp.btmscustomers.service.impl;

import com.bootcamp.btmscustomers.model.Customer;
import com.bootcamp.btmscustomers.model.CustomerType;
import com.bootcamp.btmscustomers.repository.ICustomerRepository;
import com.bootcamp.btmscustomers.repository.ICustomerTypeRepository;
import com.bootcamp.btmscustomers.repository.IGenericRepository;
import com.bootcamp.btmscustomers.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl extends GenericServiceImpl<Customer,String> implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final ICustomerTypeRepository customerTypeRepository;
    private final WebClient.Builder clientBuilder;
    private final ReactiveDiscoveryClient discoveryClient;

    private Mono<Map<String, CustomerType>> customerTypeMap = null;

    @Override
    protected IGenericRepository<Customer, String> getRepository() {
        return customerRepository;
    }

    private Mono<Map<String, CustomerType>> loadCustomerTypeMap() {
        if(customerTypeMap == null) {
            customerTypeMap = customerTypeRepository.findAll()
                    .collect(Collectors.toMap(CustomerType::getId, Function.identity()))
                    .cache();
        }
        return customerTypeMap;
    }

   @Override
    public Flux<Customer> findAll() {
        return loadCustomerTypeMap()
                .flatMapMany(typeMap -> customerRepository.findAll()
                        .map(customer -> {
                            CustomerType type = typeMap.get(customer.getCustomerType().getId());
                            if(type != null) {
                                CustomerType newType = new CustomerType();
                                newType.setId(type.getId());
                                newType.setDescription(type.getDescription());
                                customer.setCustomerType(newType);
                            }
                            return customer;
                        }));
    }

    @Override
    public Flux<Customer> getByCustomerType(String customerType) {
        return customerRepository.findAll()
                .filter(e -> e.getCustomerType().getId().equals(customerType));
    }

    @Override
    public Mono<Customer> findById(String id) {
        return customerRepository.findById(id)
                .flatMap(result -> customerTypeRepository.findById(result.getCustomerType().getId())
                        .map(customerType -> {
                            result.setCustomerType(customerType);
                            return result;
                }));
    }
}
