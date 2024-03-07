package com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.filipe.bessa.teste.simples.dental.domain.model.contacts.Contact;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ContactDetailsDTO(
        Long id,
        Long professionalId,
        String name,
        String contact,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        @Schema(type = "string", format = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        @Schema(type = "string", format = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime updatedAt
) {
    public ContactDetailsDTO(Contact contactObj) {
        this(
                contactObj.getId(),
                contactObj.getProfessional().getId(),
                contactObj.getName(),
                contactObj.getContact(),
                contactObj.getCreatedAt(),
                contactObj.getUpdatedAt());
    }
}
