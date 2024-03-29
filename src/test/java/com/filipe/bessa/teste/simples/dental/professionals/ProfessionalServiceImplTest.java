package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto.UpdateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.domain.model.professionals.Position;
import com.filipe.bessa.teste.simples.dental.domain.model.professionals.Professional;
import com.filipe.bessa.teste.simples.dental.infra.persistence.ProfessionalRepository;
import com.filipe.bessa.teste.simples.dental.application.v1.professional.ProfessionalServiceImpl;
import com.filipe.bessa.teste.simples.dental.domain.exception.ProfessionalNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


import com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto.ProfessionalDetailsDTO;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProfessionalServiceImplTest {

    @Mock
    private ProfessionalRepository professionalRepository;

    private ProfessionalServiceImpl professionalService;

    Professional professional = new Professional(
            1L,
            "Filipe",
            Position.DEVELOPER,
            LocalDate.of(1997,7,21),
            new ArrayList<>(),
            LocalDateTime.of(2021, 7, 21, 0, 0),
            LocalDateTime.of(2021, 7, 21, 0, 0)
    );

    @BeforeEach
    void setUp() {
        professionalService = new ProfessionalServiceImpl(professionalRepository);
    }

    @Test
    void createProfessionalShouldCreateProfessional() {
        // arrange
        CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO(
                "Filipe",
                Position.DEVELOPER,
                LocalDate.of(1997,7,21)
        );

        var professional = new Professional(createProfessionalDTO);
        var savedProfessional = new Professional(createProfessionalDTO);
        savedProfessional.setId(1L);

        when(professionalRepository.save(any())).thenReturn(savedProfessional);

        // act
        ProfessionalDetailsDTO professionalDetails = professionalService.createProfessional(createProfessionalDTO);

        // assert
        assertNotNull(professionalDetails);
        assertEquals(1L, professionalDetails.id());
        assertEquals("Filipe", professionalDetails.name());
        assertEquals(Position.DEVELOPER, professionalDetails.position());
        assertEquals(LocalDate.of(1997,7,21), professionalDetails.birthDate());
        verify(professionalRepository, times(1)).save(professional);
    }

    @Test
    void getProfessionalsShouldReturnProfessionalsPage() {
        // arrange
        List<Professional> professionals = new ArrayList<>();
        professionals.add(professional);

        Pageable pageable = PageRequest.of(0, 5);
        Page<Professional> professionalsPage = new PageImpl<>(professionals, pageable, professionals.size());

        when(professionalRepository.findAll(any(Pageable.class))).thenReturn(professionalsPage);

        // act
        Page<ProfessionalDetailsDTO> professionalsDetailsPage = professionalService.getProfessionals(pageable);

        // assert
        assertNotNull(professionalsDetailsPage);
        assertEquals(1, professionalsDetailsPage.getTotalElements());
        assertEquals(1, professionalsDetailsPage.getTotalPages());
        assertEquals(1, professionalsDetailsPage.getContent().size());
        assertEquals(new ProfessionalDetailsDTO(professional), professionalsDetailsPage.getContent().get(0));
    }

    @Test
    void getProfessionalShouldReturnProfessional() {
        // arrange
        when(professionalRepository.findById(1L)).thenReturn(Optional.of(professional));

        // act
        ProfessionalDetailsDTO professionalDetails = professionalService.getProfessional(1L);

        // assert
        assertNotNull(professionalDetails);
        assertEquals(new ProfessionalDetailsDTO(professional), professionalDetails);
        verify(professionalRepository, times(1)).findById(1L);
    }

    @Test
    void getProfessionalShouldThrowExceptionWhenProfessionalNotFound() {
        when(professionalRepository.findById(1L)).thenReturn(Optional.empty());

       assertThrows(ProfessionalNotFoundException.class, () -> {
           professionalService.getProfessional(1L);
       }, "Professional not found");
    }

    @Test
    void updateProfessionalShouldUpdateProfessional() {
        // arrange
        UpdateProfessionalDTO updateProfessionalDTO = new UpdateProfessionalDTO(
                1L,
                "Gabriel",
                Position.DESIGNER,
                LocalDate.of(1998,7,21)
        );
        LocalDateTime updatedAt = LocalDateTime.now();

        Professional updatedProfessional = new Professional(
                1L,
                "Gabriel",
                Position.DESIGNER,
                LocalDate.of(1998,7,21),
                new ArrayList<>(),
                professional.getCreatedAt(),
                updatedAt
        );

        when(professionalRepository.findById(1L)).thenReturn(Optional.of(professional));
        when(professionalRepository.save(any(Professional.class))).thenReturn(updatedProfessional);


        // act
        professionalService.updateProfessional(updateProfessionalDTO);

        // assert
        verify(professionalRepository, times(1)).save(updatedProfessional);
        assertEquals("Gabriel", updatedProfessional.getName());
        assertEquals(Position.DESIGNER, updatedProfessional.getPosition());
        assertEquals(LocalDate.of(1998,7,21), updatedProfessional.getBirthDate());
        assertEquals(new ArrayList<>(), updatedProfessional.getContacts());
        assertEquals(professional.getCreatedAt(), updatedProfessional.getCreatedAt());
        assertEquals(updatedAt, updatedProfessional.getUpdatedAt());
    }

    @Test
    void updateProfessionalShouldThrowExceptionWhenProfessionalNotFound() {
        UpdateProfessionalDTO updateProfessionalDTO = new UpdateProfessionalDTO(
                1L,
                "Gabriel",
                Position.DESIGNER,
                LocalDate.of(1998,7,21)
        );

        when(professionalRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProfessionalNotFoundException.class, () -> {
            professionalService.updateProfessional(updateProfessionalDTO);
        }, "Professional not found");

        verify(professionalRepository, times(0)).save(any(Professional.class));
    }

    @Test
    void deleteProfessionalShouldDeleteProfessional() {
        // arrange
        when(professionalRepository.findById(1L)).thenReturn(Optional.of(professional));

        // act
        professionalService.deleteProfessional(1L);

        // assert
        verify(professionalRepository, times(1)).delete(professional);
    }

    @Test
    void deleteProfessionalShouldThrowBusinessException() {
        when(professionalRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProfessionalNotFoundException.class, () -> {
            professionalService.deleteProfessional(1L);
        }, "Professional not found");

        verify(professionalRepository, times(0)).delete(any(Professional.class));
    }
}
