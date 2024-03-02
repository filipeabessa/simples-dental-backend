package com.filipe.bessa.teste.simples.dental.contacts.dto;

public record CreateContactDTO(
        Long professionalId,
        String name,
        String contact
) {
}
