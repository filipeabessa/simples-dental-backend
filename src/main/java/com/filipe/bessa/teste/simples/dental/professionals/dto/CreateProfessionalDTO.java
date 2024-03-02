package com.filipe.bessa.teste.simples.dental.professionals.dto;

import com.filipe.bessa.teste.simples.dental.contacts.Contact;
import com.filipe.bessa.teste.simples.dental.professionals.Position;

import java.time.LocalDate;
import java.util.List;

public record CreateProfessionalDTO(
        String name,
        Position position,
        LocalDate birthDate,
        List<Contact> contacts
) {

    public CreateProfessionalDTO(String name, Position position, LocalDate birthDate) {
        this(name, position, birthDate, null);
    }
}
