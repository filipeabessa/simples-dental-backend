package com.filipe.bessa.teste.simples.dental.professionals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto.ProfessionalDetailsDTO;
import com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto.UpdateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.domain.model.professionals.Position;
import com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.controller.ProfessionalController;
import com.filipe.bessa.teste.simples.dental.application.v1.professional.ProfessionalService;
import com.filipe.bessa.teste.simples.dental.domain.exception.ProfessionalNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(ProfessionalController.class)
class ProfessionalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessionalService professionalService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String ENDPOINT_URL = "/v1/professionals";

    @Test
    void createProfessionalWithSuccess() throws Exception {
        CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO(
                "Filipe",
                Position.DEVELOPER,
                LocalDate.of(1997,7,21)
        );

        LocalDateTime now = LocalDateTime.now();

        var professionalDetailsDTO = new ProfessionalDetailsDTO(
                1L,
                "Filipe",
                Position.DEVELOPER,
                LocalDate.of(1997,7,21),
                new ArrayList<>(),
                now,
                now
        );

        when(professionalService.createProfessional(createProfessionalDTO)).thenReturn(professionalDetailsDTO);

        var json = objectMapper.writeValueAsString(createProfessionalDTO);
        mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk()
        );
    }

    @Test
    void getProfessionalWithSuccess() throws Exception {
        var professionalDetailsDTO = new ProfessionalDetailsDTO(
                1L,
                "Filipe",
                Position.DEVELOPER,
                LocalDate.of(1997,7,21),
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(professionalService.getProfessional(1L)).thenReturn(professionalDetailsDTO);

        mockMvc.perform(get(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Filipe"))
                .andExpect(jsonPath("$.position").value("DEVELOPER"))
                .andExpect(jsonPath("$.birthDate").value("21/07/1997")
        );
    }

    @Test
    void getNonExistentProfessional() throws Exception {
        when(professionalService.getProfessional(1L)).thenThrow(new ProfessionalNotFoundException());

        mockMvc.perform(get(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProfessionalNotFoundException))
                .andExpect(jsonPath("$.apiErrorResponse.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.apiErrorResponse.codigoErro").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.apiErrorResponse.mensagem").value("Professional not found"))
                .andExpect(jsonPath("$.apiErrorResponse.mensagemDetalhada").value("Professional not found"))
                .andExpect(jsonPath("$.apiErrorResponse.timestamp").exists());
    }

    @Test
    void getProfessionalsWithSuccess() throws Exception {
        var professionalDetailsDTO = new ProfessionalDetailsDTO(
                1L,
                "Filipe",
                Position.DEVELOPER,
                LocalDate.of(1997,7,21),
                new ArrayList<>(),
                LocalDateTime.of(2021, 7, 21, 10, 0),
                LocalDateTime.of(2021, 7, 21, 10, 0)
        );

        when(professionalService.getProfessionals(any())).thenReturn(new PageImpl<>(List.of(professionalDetailsDTO)));

        mockMvc.perform(get(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Filipe"))
                .andExpect(jsonPath("$.content[0].position").value("DEVELOPER"))
                .andExpect(jsonPath("$.content[0].birthDate").value("21/07/1997")
        );
    }

    @Test
    void updateProfessionalWithSuccess() throws Exception {
        var updateProfessionalDTO = new UpdateProfessionalDTO(
                1L,
                "Filipe",
                Position.DEVELOPER,
                LocalDate.of(1997,7,21),
                new ArrayList<>()
        );

        var professionalDetailsDTO = new ProfessionalDetailsDTO(
                1L,
                "Filipe",
                Position.DEVELOPER,
                LocalDate.of(1997,7,21),
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(professionalService.updateProfessional(updateProfessionalDTO)).thenReturn(professionalDetailsDTO);

        mockMvc.perform(put(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateProfessionalDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Filipe"))
                .andExpect(jsonPath("$.position").value("DEVELOPER"))
                .andExpect(jsonPath("$.birthDate").value("21/07/1997")
        );
    }

    @Test
    void deleteProfessionalWithSuccess() throws Exception {
        mockMvc.perform(delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteNonExistentProfessional() throws Exception {

        doThrow(new ProfessionalNotFoundException()).when(professionalService).deleteProfessional(1L);
        mockMvc.perform(delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProfessionalNotFoundException))
                .andExpect(jsonPath("$.apiErrorResponse.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.apiErrorResponse.codigoErro").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.apiErrorResponse.mensagem").value("Professional not found"))
                .andExpect(jsonPath("$.apiErrorResponse.mensagemDetalhada").value("Professional not found"))
                .andExpect(jsonPath("$.apiErrorResponse.timestamp").exists());
    }


}