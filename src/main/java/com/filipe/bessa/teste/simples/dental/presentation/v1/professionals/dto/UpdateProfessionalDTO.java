package com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.filipe.bessa.teste.simples.dental.domain.model.contacts.Contact;
import com.filipe.bessa.teste.simples.dental.domain.model.professionals.Position;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record UpdateProfessionalDTO(
        Long id,
        String name,
        Position position,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Schema(type = "string", format = "dd/MM/yyyy")
        LocalDate birthDate,
        List<Contact> contacts
) {
}
