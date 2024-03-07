package com.filipe.bessa.teste.simples.dental.presentation.v1.contacts.dto;

import jakarta.validation.constraints.NotNull;

public record CreateContactDTO(
        @NotNull
        Long professionalId,
        @NotNull
        String name,
        @NotNull
        String contact
) {
}
