package com.filipe.bessa.teste.simples.dental.contacts.dto;

import java.time.LocalDateTime;

public record ContactDetailsDTO(
        Long id,
        String name,
        String contact,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
