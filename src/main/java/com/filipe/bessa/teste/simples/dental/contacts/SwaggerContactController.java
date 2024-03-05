package com.filipe.bessa.teste.simples.dental.contacts;

import com.filipe.bessa.teste.simples.dental.contacts.v1.dto.ContactDetailsDTO;
import com.filipe.bessa.teste.simples.dental.contacts.v1.dto.CreateContactDTO;
import com.filipe.bessa.teste.simples.dental.contacts.v1.dto.UpdateContactDTO;
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

@Tag(name = "ContactsController", description = "API de contatos de profissionais")
public interface SwaggerContactController {

    @Operation(summary = "Cria um contato para um profissional")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contato criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    ResponseEntity<ContactDetailsDTO> createContact(CreateContactDTO createContactDTO);

    @Operation(summary = "Busca um contato de um profissional")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contato encontrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "404", description = "Profissional ou contato não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    ResponseEntity<ContactDetailsDTO> getContact(@PathVariable Long id);

    @Operation(summary = "Busca todos os contatos de um profissional")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contatos encontrados com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    ResponseEntity<Page<ContactDetailsDTO>> getContacts(Pageable pagination);

    @Operation(summary = "Atualiza um contato de um profissional")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "404", description = "Profissional ou contato não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping
    ResponseEntity<ContactDetailsDTO> updateContact(UpdateContactDTO updateContactDTO);

    @Operation(summary = "Deleta um contato de um profissional")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contato deletado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição"),
        @ApiResponse(responseCode = "404", description = "Profissional ou contato não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteContact(@PathVariable Long id);
}
