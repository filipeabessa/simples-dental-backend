package com.filipe.bessa.teste.simples.dental.contacts.dto;

import com.filipe.bessa.teste.simples.dental.contacts.Contact;

import java.time.LocalDateTime;

public record ContactDetailsDTO(
        Long id,
        String name,
        String contact,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public ContactDetailsDTO(Contact contactObj) {
        this(
                contactObj.getId(),
                contactObj.getName(),
                contactObj.getContact(),
                contactObj.getCreatedAt(),
                contactObj.getUpdatedAt());
    }
}
