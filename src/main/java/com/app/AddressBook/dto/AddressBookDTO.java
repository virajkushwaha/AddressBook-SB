package com.app.AddressBook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AddressBookDTO {
    //Instance variables
    private String name;
    private String email;
    private String contact;
}