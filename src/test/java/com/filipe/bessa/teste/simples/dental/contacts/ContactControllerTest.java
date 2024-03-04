package com.filipe.bessa.teste.simples.dental.contacts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipe.bessa.teste.simples.dental.contacts.dto.ContactDetailsDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.CreateContactDTO;
import com.filipe.bessa.teste.simples.dental.contacts.dto.UpdateContactDTO;
import domain.exception.ContactNotFoundException;
import domain.exception.ProfessionalNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(value = ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String ENDPOINT_URL = "/contacts";

    @Test
    void createContactWithSuccess() throws Exception {
        var createContactDTO = new CreateContactDTO(
                1L,
                "Fixo",
                "11 1234-5678"
        );


        String json = objectMapper.writeValueAsString(createContactDTO);

        LocalDateTime now = LocalDateTime.now();
        String nowString = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        when(contactService.createContact(createContactDTO)).thenReturn(new ContactDetailsDTO(
                1L,
                1L,
                "Fixo",
                "11 1234-5678",
                now,
                now
        ));

        mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.professionalId").value(1))
                .andExpect(jsonPath("$.name").value("Fixo"))
                .andExpect(jsonPath("$.contact").value("11 1234-5678"))
                .andExpect(jsonPath("$.createdAt").value(nowString))
                .andExpect(jsonPath("$.updatedAt").value(nowString));
    }

    @Test
    void createContactWithInvalidProfessionalId() throws Exception {
        var createContactDTO = new CreateContactDTO(
                0L,
                "Fixo",
                "11 1234-5678"
        );

        String json = objectMapper.writeValueAsString(createContactDTO);

        when(contactService.createContact(createContactDTO)).thenThrow(new ProfessionalNotFoundException());

        mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void getContactWithSuccess() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String nowString = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        when(contactService.getContact(1L)).thenReturn(new ContactDetailsDTO(
                1L,
                1L,
                "Fixo",
                "11 1234-5678",
                now,
                now
        ));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.professionalId").value(1))
                .andExpect(jsonPath("$.name").value("Fixo"))
                .andExpect(jsonPath("$.contact").value("11 1234-5678"))
                .andExpect(jsonPath("$.createdAt").value(nowString))
                .andExpect(jsonPath("$.updatedAt").value(nowString));
    }

    @Test
    void getContactWithNonExistingContact() throws Exception {
        when(contactService.getContact(1L)).thenThrow(new ContactNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ContactNotFoundException))
                .andExpect(jsonPath("$.apiErrorResponse.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.apiErrorResponse.codigoErro").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.apiErrorResponse.mensagem").value("Contact not found"))
                .andExpect(jsonPath("$.apiErrorResponse.mensagemDetalhada").value("Contact not found"))
                .andExpect(jsonPath("$.apiErrorResponse.timestamp").exists());
    }

    @Test
    void getContactsWithSucess() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String nowString = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        when(contactService.getContacts(any())).thenReturn(new PageImpl<>(List.of(
                new ContactDetailsDTO(
                        1L,
                        1L,
                        "Fixo",
                        "11 1234-5678",
                        now,
                        now
                )
        )));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].professionalId").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Fixo"))
                .andExpect(jsonPath("$.content[0].contact").value("11 1234-5678"))
                .andExpect(jsonPath("$.content[0].createdAt").value(nowString))
                .andExpect(jsonPath("$.content[0].updatedAt").value(nowString));
    }

    @Test
    void updateContactWithSuccess() throws Exception {
        var updateContactDTO = new UpdateContactDTO(
                1L,
                "Fixo",
                "11 1234-5678"
        );

        String json = objectMapper.writeValueAsString(updateContactDTO);

        when(contactService.updateContact(updateContactDTO)).thenReturn(new ContactDetailsDTO(
                1L,
                1L,
                "Fixo",
                "11 1234-5678",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        ));

        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.professionalId").value(1))
                .andExpect(jsonPath("$.name").value("Fixo"))
                .andExpect(jsonPath("$.contact").value("11 1234-5678"));
    }

    @Test
    void updateContactWithNonExistingContact() throws Exception {
        var updateContactDTO = new UpdateContactDTO(
                1L,
                "Fixo",
                "11 1234-5678"
        );

        String json = objectMapper.writeValueAsString(updateContactDTO);

        when(contactService.updateContact(updateContactDTO)).thenThrow(new ContactNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ContactNotFoundException))
                .andExpect(jsonPath("$.apiErrorResponse.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.apiErrorResponse.codigoErro").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.apiErrorResponse.mensagem").value("Contact not found"))
                .andExpect(jsonPath("$.apiErrorResponse.mensagemDetalhada").value("Contact not found"))
                .andExpect(jsonPath("$.apiErrorResponse.timestamp").exists());
    }

    @Test
    void deleteContactWithSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteContactWithNonExistingContact() throws Exception {
        doThrow(new ContactNotFoundException()).when(contactService).deleteContact(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ContactNotFoundException))
                .andExpect(jsonPath("$.apiErrorResponse.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.apiErrorResponse.codigoErro").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.apiErrorResponse.mensagem").value("Contact not found"))
                .andExpect(jsonPath("$.apiErrorResponse.mensagemDetalhada").value("Contact not found"))
                .andExpect(jsonPath("$.apiErrorResponse.timestamp").exists());
    }
}