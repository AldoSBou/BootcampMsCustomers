package com.bootcamp.btmscustomers.config;

import com.bootcamp.btmscustomers.dto.CustomerDTO;
import com.bootcamp.btmscustomers.dto.CustomerTypeDTO;
import com.bootcamp.btmscustomers.model.Customer;
import com.bootcamp.btmscustomers.model.CustomerType;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("defaultMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean("customerMapper")
    public ModelMapper customerMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }

    @Bean("customerTypeMapper")
    public ModelMapper customerTypeMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(CustomerType.class, CustomerTypeDTO.class)
                .addMapping(CustomerType::getId, (dest, v) -> dest.setId((String) v))
                .addMapping(CustomerType::getDescription, (dest, v) -> dest.setDescription((String) v));

        modelMapper.createTypeMap(CustomerTypeDTO.class, CustomerType.class)
                .addMapping(CustomerTypeDTO::getId, (dest, v) -> dest.setId((String) v))
                .addMapping(CustomerTypeDTO::getDescription, (dest, v) -> dest.setDescription((String) v));

        return modelMapper;
    }
}
