package com.filipe.bessa.teste.simples.dental.contacts;

import com.filipe.bessa.teste.simples.dental.contacts.dto.ContactDetailsDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.CreateContactDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.UpdateContactDTO;
import com.filipe.bessa.teste.simples.dental.professionals.Position;
import com.filipe.bessa.teste.simples.dental.professionals.Professional;
import com.filipe.bessa.teste.simples.dental.professionals.ProfessionalRepository;
import domain.exception.ContactNotFoundException;
import domain.exception.ProfessionalNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ContactServiceImplTest {
    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ProfessionalRepository professionalRepository;

    private ContactService contactService;

    Professional professional = new Professional(
            1L,
            "Filipe",
            Position.DEVELOPER,
            LocalDate.of(1997, 7, 21),
            new ArrayList<>(),
            LocalDateTime.of(2021, 7, 21, 0, 0),
            LocalDateTime.of(2021, 7, 21, 0, 0)
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        contactService = new ContactServiceImpl(contactRepository, professionalRepository);
    }

    @Test
    void createContactShouldCreateContact() {
        // arrange
        CreateContactDTO createContactDTO = new CreateContactDTO(
                1L,
                "Fixo",
                "11999999999"
        );

        Contact contact = new Contact(createContactDTO);
        contact.setId(1L);
        contact.setProfessional(professional);

        when(professionalRepository.findById(1L)).thenReturn(Optional.of(professional));
        when(contactRepository.save(contact)).thenReturn(contact);

        // act
        var contactDetailsDTO = contactService.createContact(createContactDTO);

        // assert
        assertNotNull(contactDetailsDTO);
        assertEquals(contact.getName(), contactDetailsDTO.name());
        assertEquals(contact.getContact(), contactDetailsDTO.contact());
        assertEquals(professional.getId(), contactDetailsDTO.professionalId());
    }

    @Test
    void createContactShouldThrowExceptionWhenProfessionalNotFound() {
        // arrange
        CreateContactDTO createContactDTO = new CreateContactDTO(
                1L,
                "Fixo",
                "11999999999"
        );

        when(professionalRepository.findById(1L)).thenReturn(Optional.empty());

        // act and assert
        assertThrows(ProfessionalNotFoundException.class, () -> {
            contactService.createContact(createContactDTO);
        }, "Professional not found");
    }

    @Test
    void getProfessionalContactsShouldReturnContactsPage() {
        // arrange
        Contact contact = new Contact(
                1L,
                professional,
                "Fixo",
                "11999999999",
                LocalDateTime.of(2021, 7, 21, 0, 0),
                LocalDateTime.of(2021, 7, 21, 0, 0)
        );
        List<Contact> contacts = List.of(contact);

        Pageable pageable = PageRequest.of(0, 5);
        Page<Contact> contactsPage = new PageImpl<>(contacts, pageable, contacts.size());

        when(contactRepository.findAll(pageable)).thenReturn(contactsPage);


        // act
        Page<ContactDetailsDTO> contactDetailsDTOPage = contactService.getContacts(pageable);

        // assert
        assertNotNull(contactDetailsDTOPage);
        assertEquals(1, contactDetailsDTOPage.getTotalElements());
        assertEquals(contact.getId(), contactDetailsDTOPage.getContent().get(0).id());
        assertEquals(contact.getName(), contactDetailsDTOPage.getContent().get(0).name());
        assertEquals(contact.getContact(), contactDetailsDTOPage.getContent().get(0).contact());
        assertEquals(contact.getProfessional().getId(), contactDetailsDTOPage.getContent().get(0).professionalId());
    }

    @Test
    void getContactShouldReturnContact() {
        // arrange
        Contact contact = new Contact(
                1L,
                professional,
                "Fixo",
                "11999999999",
                LocalDateTime.of(2021, 7, 21, 0, 0),
                LocalDateTime.of(2021, 7, 21, 0, 0)
        );

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        // act
        ContactDetailsDTO contactDetailsDTO = contactService.getContact(1L);

        // assert
        assertNotNull(contactDetailsDTO);
        assertEquals(contact.getId(), contactDetailsDTO.id());
        assertEquals(contact.getName(), contactDetailsDTO.name());
        assertEquals(contact.getContact(), contactDetailsDTO.contact());
        assertEquals(contact.getProfessional().getId(), contactDetailsDTO.professionalId());
    }

    @Test
    void getContactShouldThrowExceptionWhenContactNotFound() {
        // arrange
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        // act and assert
        assertThrows(ContactNotFoundException.class, () -> {
            contactService.getContact(1L);
        }, "Contact not found");
    }

    @Test
    void updateContactShouldUpdateContact() {
        // arrange
        Contact contact = new Contact(
                1L,
                professional,
                "Fixo",
                "11999999999",
                LocalDateTime.of(2021, 7, 21, 0, 0),
                LocalDateTime.of(2021, 7, 21, 0, 0)
        );

        UpdateContactDTO updateContactDTO = new UpdateContactDTO(
                1L,
                "Celular",
                "11999999999"
        );

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        when(contactRepository.save(contact)).thenReturn(contact);

        // act
        contactService.updateContact(updateContactDTO);

        // assert
        assertEquals(updateContactDTO.name(), contact.getName());
        assertEquals(updateContactDTO.contact(), contact.getContact());
    }

    @Test
    void updateContactShouldThrowExceptionWhenContactNotFound() {
        // arrange
        UpdateContactDTO updateContactDTO = new UpdateContactDTO(
                1L,
                "Celular",
                "11999999999"
        );

        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        // act and assert
        assertThrows(ContactNotFoundException.class, () -> {
            contactService.updateContact(updateContactDTO);
        }, "Contact not found");
    }

    @Test
    void deleteContactShouldDeleteContact() {
        // arrange
        Contact contact = new Contact(
                1L,
                professional,
                "Fixo",
                "11999999999",
                LocalDateTime.of(2021, 7, 21, 0, 0),
                LocalDateTime.of(2021, 7, 21, 0, 0)
        );

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        // act
        contactService.deleteContact(1L);

        // assert
        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteContactShouldThrowExceptionWhenContactNotFound() {
        // arrange
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        // act and assert
        assertThrows(ContactNotFoundException.class, () -> {
            contactService.deleteContact(1L);
        }, "Contact not found");
    }
}