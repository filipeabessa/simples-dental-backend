package com.filipe.bessa.teste.simples.dental.application.v1.contact;

import com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.dto.ContactDetailsDTO;
import com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.dto.CreateContactDTO;
import com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.dto.UpdateContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {
    ContactDetailsDTO createContact(CreateContactDTO createContactDTO);

    Page<ContactDetailsDTO> getContacts(Pageable pagination);

    ContactDetailsDTO getContact(Long id);

    ContactDetailsDTO updateContact(UpdateContactDTO updateContactDTO);

    void deleteContact(Long id);
}
