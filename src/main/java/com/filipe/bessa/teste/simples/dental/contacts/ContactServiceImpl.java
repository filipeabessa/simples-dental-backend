package com.filipe.bessa.teste.simples.dental.contacts;

import com.filipe.bessa.teste.simples.dental.contacts.dto.ContactDetailsDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.CreateContactDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.UpdateContactDTO;
import com.filipe.bessa.teste.simples.dental.professionals.Professional;
import com.filipe.bessa.teste.simples.dental.professionals.ProfessionalRepository;
import domain.exception.ContactNotFoundException;
import domain.exception.ProfessionalNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;
    private ProfessionalRepository professionalRepository;

    @Override
    public ContactDetailsDTO createContact(CreateContactDTO createContactDTO) {
        Professional professional = findProfessional(createContactDTO.professionalId());

        Contact contact = new Contact(createContactDTO);
        contact.setProfessional(professional);

        var createdContact = contactRepository.save(contact);

        return new ContactDetailsDTO(createdContact);
    }

    @Override
    public Page<ContactDetailsDTO> getContacts(Pageable pagination) {
        return contactRepository.findAll(pagination).map(ContactDetailsDTO::new);
    }

    @Override
    public ContactDetailsDTO getContact(Long id) {
        Contact contact = findContact(id);
        return new ContactDetailsDTO(contact);
    }

    @Override
    public ContactDetailsDTO updateContact(UpdateContactDTO updateContactDTO) {
        Contact contact = findContact(updateContactDTO.id());

        contact.setName(updateContactDTO.name() != null ? updateContactDTO.name() : contact.getName());
        contact.setContact(updateContactDTO.contact() != null ? updateContactDTO.contact() : contact.getContact());
        contact.setUpdatedAt(LocalDateTime.now());

        return new ContactDetailsDTO(contactRepository.save(contact));
    }

    @Override
    public void deleteContact(Long id) {
        findContact(id);

        contactRepository.deleteById(id);
    }

    private Contact findContact(Long id) {
        return contactRepository.findById(id).orElseThrow(
                ContactNotFoundException::new
        );
    }

    private Professional findProfessional(Long id) {
        return professionalRepository.findById(id).orElseThrow(
                ProfessionalNotFoundException::new
        );
    }
}
