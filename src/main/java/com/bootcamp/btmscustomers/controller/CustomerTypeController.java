package com.bootcamp.btmscustomers.controller;

import com.bootcamp.btmscustomers.dto.CustomerTypeDTO;
import com.bootcamp.btmscustomers.mapper.MapperCustomerType;
import com.bootcamp.btmscustomers.model.CustomerType;
import com.bootcamp.btmscustomers.service.ICustomerTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/customers/description")
@RequiredArgsConstructor
public class CustomerTypeController {

    public final ICustomerTypeService customerTypeService;
    private final MapperCustomerType mapperCustomerType;

    @GetMapping
    public Mono<ResponseEntity<Flux<CustomerTypeDTO>>> getCustomerDescriptions() {

        Flux<CustomerTypeDTO> list = customerTypeService.findAll()
                .map(mapperCustomerType::customerToCustomerTypeDTO);

        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerTypeDTO>> getCustomerById(@PathVariable("id") String id) {

        return customerTypeService.findById(id)
                .map(mapperCustomerType::customerToCustomerTypeDTO)
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
