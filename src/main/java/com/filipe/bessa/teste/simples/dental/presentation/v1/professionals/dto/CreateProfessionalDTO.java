package com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto;

import com.filipe.bessa.teste.simples.dental.domain.model.professionals.Position;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record CreateProfessionalDTO(
        @Schema(description = "Professional name", example = "John Doe")
        String name,
        @Schema(description = "Professional position", example = "DEVELOPER")
        Position position,

        @Schema(description = "Professional birth date", example = "01/01/2000")
        LocalDate birthDate
) {
}
