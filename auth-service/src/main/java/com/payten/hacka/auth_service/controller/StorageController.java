package com.payten.hacka.auth_service.controller;

import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.StorageAddressDto;
import com.payten.hacka.auth_service.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
@AllArgsConstructor
public class StorageController {
    private StorageService storageService;

    @GetMapping("/address")
    public ResponseEntity<List<StorageAddressDto>> getAllStorageAddresses(){
        return new ResponseEntity<>(storageService.findAllStorageAddresses(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressDto> addStorageAddress(@RequestBody AddressDto addressDto){
        return new ResponseEntity<>(storageService.addNewStorageAddress(addressDto), HttpStatus.OK);
    }

}
