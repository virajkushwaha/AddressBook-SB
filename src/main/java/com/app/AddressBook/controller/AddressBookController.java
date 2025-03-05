package com.app.AddressBook.controller;

import com.app.AddressBook.dto.AddressBookDTO;
import com.app.AddressBook.model.AddressBook;
import com.app.AddressBook.services.AddressBookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/request") //Base URL path
public class AddressBookController {
    //Logger object for log statements
    private static final Logger log = LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    AddressBookServices addressBookServices;

    // Save details
    @PostMapping
    public ResponseEntity<AddressBook> saveDetails(@RequestBody AddressBookDTO addressBookDTO) {
        log.info("Received request to save details: {}", addressBookDTO);
        AddressBook savedDetails = addressBookServices.saveDetails(addressBookDTO);
        log.info("Details saved successfully with ID: {}", savedDetails.getId());
        return ResponseEntity.ok(savedDetails);
    }

    // Get all details from database
    @GetMapping
    public ResponseEntity<List<AddressBookDTO>> getAllDetails() {
        log.info("Fetching all address book details.");
        List<AddressBookDTO> allDetails = addressBookServices.getAllDetails();
        log.info("Fetched {} records.", allDetails.size());
        if(allDetails.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(allDetails);
    }

    // Get Details By ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressBookDTO> getByID(@PathVariable Long id) {
        log.info("Fetching details for ID: {}", id);
        AddressBookDTO details = addressBookServices.getByID(id);
        log.info("Details found: {}", details);
        if(details == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(details);
    }

    // Update details of existing data by id
    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> updateDetails(@PathVariable Long id, @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Received request to update details for ID: {}", id);
        AddressBook updatedDetails = addressBookServices.updateDetails(id, addressBookDTO);
        log.info("Details updated successfully for ID: {}", id);
        return ResponseEntity.ok(updatedDetails);
    }

    // Delete detail of specific data by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDetails(@PathVariable Long id) {
        log.info("Received request to delete details for ID: {}", id);
        boolean isDeleted = addressBookServices.deleteByID(id);
        if (isDeleted) {
            log.info("Details deleted successfully for ID: {}", id);
        } else {
            log.warn("No details found to delete for ID: {}", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(isDeleted);
    }
}