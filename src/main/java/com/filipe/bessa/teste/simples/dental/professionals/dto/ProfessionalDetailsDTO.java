package com.filipe.bessa.teste.simples.dental.professionals.dto;

import com.filipe.bessa.teste.simples.dental.contacts.Contact;
import com.filipe.bessa.teste.simples.dental.professionals.Position;
import com.filipe.bessa.teste.simples.dental.professionals.Professional;

import java.time.LocalDate;
import java.util.List;

public record ProfessionalDetailsDTO(
        Long id,
        String name,
        Position position,
        LocalDate birthDate,
        List<Contact> contacts
) {

    public ProfessionalDetailsDTO(Professional professional) {
        this(
                professional.getId(),
                professional.getName(),
                professional.getPosition(),
                professional.getBirthDate(),
                professional.getContacts()
        );
    }
}
