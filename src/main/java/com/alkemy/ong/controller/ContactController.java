package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Object> createContact(@RequestBody @Valid ContactDTO dto) {
        try {
            contactService.addContact(dto);
        }catch (Exception e) {
            return new ResponseEntity<>("Contact already exist", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

}