package com.filipe.bessa.teste.simples.dental.contacts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.filipe.bessa.teste.simples.dental.contacts.Contact;

import java.time.LocalDateTime;

public record ContactDetailsDTO(
        Long id,
        Long professionalId,
        String name,
        String contact,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
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
