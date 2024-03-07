package com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.filipe.bessa.teste.simples.dental.domain.model.contacts.Contact;
import com.filipe.bessa.teste.simples.dental.domain.model.professionals.Position;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record CreateProfessionalDTO(
        String name,
        Position position,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Schema(description = "dd/MM/yyyy", example = "01/01/2000")
        LocalDate birthDate,
        List<Contact> contacts
) {

    public CreateProfessionalDTO(String name, Position position, LocalDate birthDate) {
        this(name, position, birthDate, null);
    }
}
