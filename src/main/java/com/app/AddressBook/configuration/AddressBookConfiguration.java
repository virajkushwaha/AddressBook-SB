package com.app.AddressBook.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressBookConfiguration {
    //Creating bean of ModelMapper
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}