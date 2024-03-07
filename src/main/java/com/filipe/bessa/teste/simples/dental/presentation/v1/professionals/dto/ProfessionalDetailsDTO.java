package com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.filipe.bessa.teste.simples.dental.domain.model.professionals.Professional;
import com.filipe.bessa.teste.simples.dental.domain.model.professionals.Position;
import com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.dto.ContactDetailsDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProfessionalDetailsDTO(
        Long id,
        String name,
        Position position,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Schema(type = "string", format = "dd/MM/yyyy")
        LocalDate birthDate,
        List<ContactDetailsDTO> contacts,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        @Schema(type = "string", format = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        @Schema(type = "string", format = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime updatedAt
) {

    public ProfessionalDetailsDTO(Professional professional) {
        this(
                professional.getId(),
                professional.getName(),
                professional.getPosition(),
                professional.getBirthDate(),
                professional.getContacts().stream().map(ContactDetailsDTO::new).toList(),
                professional.getCreatedAt(),
                professional.getUpdatedAt()
        );
    }
}
