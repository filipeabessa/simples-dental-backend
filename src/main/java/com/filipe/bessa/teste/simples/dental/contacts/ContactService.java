package com.filipe.bessa.teste.simples.dental.contacts;

import com.filipe.bessa.teste.simples.dental.contacts.dto.ContactDetailsDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.CreateContactDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.UpdateContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {
    ContactDetailsDTO createContact(CreateContactDTO createContactDTO);

    Page<ContactDetailsDTO> getContacts(Pageable pagination);

    ContactDetailsDTO getContact(Long id);

    ContactDetailsDTO updateContact(UpdateContactDTO updateContactDTO);

    void deleteContact(Long id);
}
