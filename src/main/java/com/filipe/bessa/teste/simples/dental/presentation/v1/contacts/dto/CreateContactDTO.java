package com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.dto;

public record CreateContactDTO(
        Long professionalId,
        String name,
        String contact
) {
}
