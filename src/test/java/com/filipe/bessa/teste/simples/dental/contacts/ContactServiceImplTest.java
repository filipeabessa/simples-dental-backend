package com.filipe.bessa.teste.simples.dental.contacts;

import com.filipe.bessa.teste.simples.dental.contacts.dto.ContactDetailsDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.CreateContactDTO;
import com.filipe.bessa.teste.simples.dental.professionals.Position;
import com.filipe.bessa.teste.simples.dental.professionals.Professional;
import com.filipe.bessa.teste.simples.dental.professionals.ProfessionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

        Contact contactWithProfessional = new Contact(createContactDTO);
        contactWithProfessional.setProfessional(professional);

        when(professionalRepository.findById(1L)).thenReturn(Optional.of(professional));
        when(contactRepository.save(any(Contact.class))).thenReturn(contactWithProfessional);

        // act
        var contactDetailsDTO = contactService.createContact(createContactDTO);

        // assert
        assertNotNull(contactDetailsDTO);
        assertEquals(contact.getName(), contactDetailsDTO.name());
        assertEquals(contact.getContact(), contactDetailsDTO.contact());
        assertEquals(professional.getId(), contactDetailsDTO.professionalId());
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

        when(contactRepository.findAll(any(Pageable.class))).thenReturn(contactsPage);


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
}