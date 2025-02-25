package com.bootcamp.btmscustomers.controller;

import com.bootcamp.btmscustomers.dto.CustomerDTO;
import com.bootcamp.btmscustomers.mapper.MapperCustomer;
import com.bootcamp.btmscustomers.service.ICustomerService;
import com.bootcamp.btmscustomers.service.ICustomerTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final ICustomerService customerService;
    private final ICustomerTypeService customerTypeService;
    private final MapperCustomer mapperCustomer;

    @GetMapping
    public Mono<ResponseEntity<Flux<CustomerDTO>>>  getAllCustomers() {

        Flux<CustomerDTO> list = customerService.findAll()
                .map(mapperCustomer::customerToCustomerDTO);
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerDTO>> findById(@PathVariable("id") String id) {
        return customerService.findById(id)
                .map(mapperCustomer::customerToCustomerDTO)
                .map(e ->
                        ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<CustomerDTO>> createCustomer(@RequestBody CustomerDTO customer, final ServerHttpRequest request) {
        return customerService.save(mapperCustomer.convertToDocument(customer))
                .map(mapperCustomer::customerToCustomerDTO)
                .map(result -> ResponseEntity.created(URI.create(request.getURI().toString().concat("/").concat(result.getId())))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(result)
                ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<CustomerDTO>> updateCustomer(@RequestBody CustomerDTO customer, @PathVariable("id") String id) {
         return Mono.just(customer)
                .map(e -> {
                    e.setId(id);
                    return e;
                })
                .flatMap(e -> customerService.update(id,mapperCustomer.convertToDocument(customer)))
                 .map(mapperCustomer::customerToCustomerDTO)
                .map(e -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Boolean>> deleteCustomer(@PathVariable String id) {
        return customerService.delete(id)
                .map(e -> ResponseEntity.noContent().build());
    }

    @GetMapping("/type/{id}")
    public Mono<ResponseEntity<Flux<CustomerDTO>>> getByCustomerType(@PathVariable("id") String customerTypeId) {
        Flux<CustomerDTO> list = customerService.getByCustomerType(customerTypeId)
                .map(mapperCustomer::customerToCustomerDTO);
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
