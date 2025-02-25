package com.bootcamp.btmscustomers.mapper;

import com.bootcamp.btmscustomers.dto.CustomerDTO;
import com.bootcamp.btmscustomers.model.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperCustomer {

    @Qualifier("customerMapper")
    private final ModelMapper customerMapper;

    public CustomerDTO customerToCustomerDTO(Customer customer) {
        return customerMapper.map(customer, CustomerDTO.class);
    }

    public Customer convertToDocument(CustomerDTO customerDTO) {
        return customerMapper.map(customerDTO, Customer.class);
    }
}
