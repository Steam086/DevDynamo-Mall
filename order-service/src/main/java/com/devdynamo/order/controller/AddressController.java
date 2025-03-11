package com.devdynamo.order.controller;

import com.devdynamo.entity.order.Address;
import com.devdynamo.order.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressRepository addressRepository;

    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody Address address) {
        addressRepository.save(address);
        return ResponseEntity.ok().build();
    }
}
