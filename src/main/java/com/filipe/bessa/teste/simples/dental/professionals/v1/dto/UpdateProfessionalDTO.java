package com.filipe.bessa.teste.simples.dental.professionals.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.filipe.bessa.teste.simples.dental.contacts.v1.Contact;
import com.filipe.bessa.teste.simples.dental.professionals.v1.Position;
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
