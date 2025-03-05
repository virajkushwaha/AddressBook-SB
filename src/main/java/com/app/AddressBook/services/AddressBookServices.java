package com.app.AddressBook.services;

import com.app.AddressBook.model.AddressBook;
import com.app.AddressBook.repository.AddressBookRepo;
import com.app.AddressBook.dto.AddressBookDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookServices {
    //Logger object for log statements
    private static final Logger log = LoggerFactory.getLogger(AddressBookServices.class);

    @Autowired
    private AddressBookRepo bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get all details from database
    public List<AddressBookDTO> getAllDetails() {
        log.info("Fetching all address book details.");
        List<AddressBookDTO> details = bookRepository.findAll()
                .stream()
                .map(add -> modelMapper.map(add, AddressBookDTO.class))
                .collect(Collectors.toList());
        log.info("Fetched {} records.", details.size());
        return details;
    }

    // Save details into the database
    public AddressBook saveDetails(AddressBookDTO addressBookDTO) {
        log.info("Saving new address book details: {}", addressBookDTO);
        AddressBook savedEntity = bookRepository.save(modelMapper.map(addressBookDTO, AddressBook.class));
        log.info("Details saved successfully with ID: {}", savedEntity.getId());
        return savedEntity;
    }

    // Get details by ID
    public AddressBookDTO getByID(Long id) {
        log.info("Fetching details for ID: {}", id);
        AddressBook entity = bookRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No details found for ID: {}", id);
                    return new RuntimeException("No details found for ID: " + id);
                });
        AddressBookDTO dto = modelMapper.map(entity, AddressBookDTO.class);
        log.info("Details found: {}", dto);
        return dto;
    }

    // Delete any data by ID
    public Boolean deleteByID(Long id) {
        log.info("Attempting to delete details for ID: {}", id);
        if (bookRepository.findById(id).isEmpty()) {
            log.warn("No details found to delete for ID: {}", id);
            return false;
        }
        bookRepository.deleteById(id);
        log.info("Details deleted successfully for ID: {}", id);
        return true;
    }

    // Update details of an existing data by ID
    public AddressBook updateDetails(Long id, AddressBookDTO addressBookDTO) {
        log.info("Updating details for ID: {}", id);
        AddressBook entity = bookRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No details found for ID: {}", id);
                    return new RuntimeException("No details found for ID: " + id);
                });

        entity.setName(addressBookDTO.getName());
        entity.setContact(addressBookDTO.getContact());
        entity.setEmail(addressBookDTO.getEmail());

        AddressBook updatedEntity = bookRepository.save(entity);
        log.info("Details updated successfully for ID: {}", id);
        return updatedEntity;
    }
}
