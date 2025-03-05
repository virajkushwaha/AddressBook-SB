package com.app.AddressBook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "addressBook")
public class AddressBook {
    //Make id primary key and generate a sequential id for data
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Instance variables
    private Long id;
    private String name;
    private String email;
    private String contact;
}
