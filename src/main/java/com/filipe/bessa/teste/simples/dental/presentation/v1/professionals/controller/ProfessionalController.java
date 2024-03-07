package com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.controller;

import com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto.ProfessionalDetailsDTO;
import com.filipe.bessa.teste.simples.dental.presentation.v1.professionals.dto.UpdateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.application.v1.professional.ProfessionalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("v1/professionals")
public class ProfessionalController implements SwaggerProfessionalController {
    private final ProfessionalService professionalService;

    @Override
    public ResponseEntity<ProfessionalDetailsDTO> createProfessional(@RequestBody @Valid CreateProfessionalDTO createProfessionalDTO) {
        var professionalDetails = professionalService.createProfessional(createProfessionalDTO);
        return ResponseEntity.ok(professionalDetails);
    }

    @Override
    public ResponseEntity<Page<ProfessionalDetailsDTO>> getProfessionals(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pagination
    ) {
        var professionals = professionalService.getProfessionals(pagination);
        return ResponseEntity.ok(professionals);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDetailsDTO> getProfessional(@PathVariable Long id) {

        return ResponseEntity.ok(professionalService.getProfessional(id));
    }

    @Override
    public ResponseEntity<ProfessionalDetailsDTO> updateProfessional(@RequestBody @Valid UpdateProfessionalDTO updateProfessionalDTO) {
        return ResponseEntity.ok(professionalService.updateProfessional(updateProfessionalDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
        return ResponseEntity.noContent().build();
    }
}
