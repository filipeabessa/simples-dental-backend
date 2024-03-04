package com.filipe.bessa.teste.simples.dental.professionals.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.filipe.bessa.teste.simples.dental.contacts.Contact;
import com.filipe.bessa.teste.simples.dental.professionals.Position;
import com.filipe.bessa.teste.simples.dental.professionals.Professional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProfessionalDetailsDTO(
        Long id,
        String name,
        Position position,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate,
        List<Contact> contacts,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime updatedAt
) {

    public ProfessionalDetailsDTO(Professional professional) {
        this(
                professional.getId(),
                professional.getName(),
                professional.getPosition(),
                professional.getBirthDate(),
                professional.getContacts(),
                professional.getCreatedAt(),
                professional.getUpdatedAt()
        );
    }
}
