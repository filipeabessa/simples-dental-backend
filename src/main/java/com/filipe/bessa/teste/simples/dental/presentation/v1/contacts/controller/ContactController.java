package com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.controller;

import com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.dto.ContactDetailsDTO;
import com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.dto.CreateContactDTO;
import com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.dto.UpdateContactDTO;
import com.filipe.bessa.teste.simples.dental.application.v1.contact.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("v1/contacts")
public class ContactController implements SwaggerContactController {
    private ContactService contactService;

    @Override
    public ResponseEntity<ContactDetailsDTO> createContact(@RequestBody CreateContactDTO createContactDTO) {
        ContactDetailsDTO contactDetailsDTO = contactService.createContact(createContactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactDetailsDTO);
    }

    @Override
    public ResponseEntity<Page<ContactDetailsDTO>> getContacts(Pageable pagination) {
        return ResponseEntity.ok(contactService.getContacts(pagination));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ContactDetailsDTO> getContact(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @Override
    public ResponseEntity<ContactDetailsDTO> updateContact(@RequestBody UpdateContactDTO updateContactDTO) {
        var updatedContact = contactService.updateContact(updateContactDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedContact);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
