package com.app.AddressBook.repository;

import com.app.AddressBook.model.AddressBook;


import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository for using SQL Queries
public interface AddressBookRepo extends JpaRepository<AddressBook,Long> {
}

