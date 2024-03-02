package com.filipe.bessa.teste.simples.dental.contacts;

import com.filipe.bessa.teste.simples.dental.contacts.dto.ContactDetailsDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.CreateContactDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.UpdateContactDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ContactController {
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDetailsDTO> createContact(@RequestBody CreateContactDTO createContactDTO) {
        ContactDetailsDTO contactDetailsDTO = contactService.createContact(createContactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactDetailsDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ContactDetailsDTO>> getContacts(Pageable pagination) {
        return ResponseEntity.ok(contactService.getContacts(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDetailsDTO> getContact(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @PutMapping
    public ResponseEntity<Void> updateContact(@RequestBody UpdateContactDTO updateContactDTO) {
        contactService.updateContact(updateContactDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
