package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.professionals.v1.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.v1.dto.ProfessionalDetailsDTO;
import com.filipe.bessa.teste.simples.dental.professionals.v1.dto.UpdateProfessionalDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "ProfessionalsController", description = "API de profissionais")
public interface SwaggerProfessionalController {

    @Operation(summary = "Cria um profissional")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Profissional criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    ResponseEntity<ProfessionalDetailsDTO> createProfessional(CreateProfessionalDTO createProfessionalDTO);

    @Operation(summary = "Busca um profissional")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profissional encontrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    ResponseEntity<ProfessionalDetailsDTO> getProfessional(@PathVariable Long id);

    @Operation(summary = "Busca todos os profissionais")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profissionais encontrados com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    ResponseEntity<Page<ProfessionalDetailsDTO>> getProfessionals(Pageable pagination);

    @Operation(summary = "Atualiza um profissional")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profissional atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping
    ResponseEntity<ProfessionalDetailsDTO> updateProfessional(UpdateProfessionalDTO updateProfessionalDTO);

    @Operation(summary = "Deleta um profissional")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Profissional deletado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProfessional(@PathVariable Long id);
}
