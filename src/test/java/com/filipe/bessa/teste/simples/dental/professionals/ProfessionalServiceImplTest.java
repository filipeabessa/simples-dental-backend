package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.professionals.dto.CreateProfessionalDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import com.filipe.bessa.teste.simples.dental.professionals.dto.ProfessionalDetailsDTO;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProfessionalServiceImplTest {

    @Mock
    private ProfessionalRepository professionalRepository;

    @InjectMocks
    private ProfessionalServiceImpl professionalService;

    @BeforeEach
    void setUp() {
        professionalService = new ProfessionalServiceImpl(professionalRepository);
    }

    @Test
    void createProfessionalShouldCreateProfessional() {
        CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO(
                "Filipe",
                Position.DEVELOPER,
                LocalDate.of(1997,7,21)
        );

        var professional = new Professional(createProfessionalDTO);
        var savedProfessional = new Professional(createProfessionalDTO);
        savedProfessional.setId(1L);

        when(professionalRepository.save(any())).thenReturn(savedProfessional);

        ProfessionalDetailsDTO professionalDetails = professionalService.createProfessional(createProfessionalDTO);

        assertNotNull(professionalDetails);
        assertEquals(1L, professionalDetails.id());
        assertEquals("Filipe", professionalDetails.name());
        assertEquals(Position.DEVELOPER, professionalDetails.position());
        assertEquals(LocalDate.of(1997,7,21), professionalDetails.birthDate());
        verify(professionalRepository, times(1)).save(professional);
    }
}
