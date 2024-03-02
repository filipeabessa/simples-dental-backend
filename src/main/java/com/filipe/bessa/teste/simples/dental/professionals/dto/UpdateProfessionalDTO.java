package com.filipe.bessa.teste.simples.dental.professionals.dto;

import com.filipe.bessa.teste.simples.dental.contacts.Contact;
import com.filipe.bessa.teste.simples.dental.professionals.Position;

import java.time.LocalDate;
import java.util.List;

public record UpdateProfessionalDTO(
        Long id,
        String name,
        Position position,
        LocalDate birthDate,
        List<Contact> contacts
) {
}
