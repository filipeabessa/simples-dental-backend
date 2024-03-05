package com.filipe.bessa.teste.simples.dental.contacts.v1.dto;

public record CreateContactDTO(
        Long professionalId,
        String name,
        String contact
) {
}
