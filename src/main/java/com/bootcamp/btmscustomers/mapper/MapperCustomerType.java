package com.bootcamp.btmscustomers.mapper;

import com.bootcamp.btmscustomers.dto.CustomerTypeDTO;
import com.bootcamp.btmscustomers.model.CustomerType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperCustomerType {

    @Qualifier("customerTypeMapper")
    private final ModelMapper customerTypeMapper;

    public CustomerTypeDTO customerToCustomerTypeDTO(CustomerType customerType) {
        return customerTypeMapper.map(customerType, CustomerTypeDTO.class);
    }

    public CustomerType convertToDocument(CustomerTypeDTO customerTypeDTO) {
        return customerTypeMapper.map(customerTypeDTO, CustomerType.class);
    }
}
