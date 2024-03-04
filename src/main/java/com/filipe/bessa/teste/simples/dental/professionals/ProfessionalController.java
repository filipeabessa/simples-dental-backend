package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.ProfessionalDetailsDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.UpdateProfessionalDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/professionals")
public class ProfessionalController {
    private final ProfessionalService professionalService;

    @PostMapping()
    public ResponseEntity<ProfessionalDetailsDTO> createProfessional(@RequestBody @Valid CreateProfessionalDTO createProfessionalDTO) {
        var professionalDetails = professionalService.createProfessional(createProfessionalDTO);
        return ResponseEntity.ok(professionalDetails);
    }

    @GetMapping()
    public ResponseEntity<Page<ProfessionalDetailsDTO>> getProfessionals(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pagination
    ) {
        var professionals = professionalService.getProfessionals(pagination);
        return ResponseEntity.ok(professionals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDetailsDTO> getProfessional(@PathVariable Long id) {

        return ResponseEntity.ok(professionalService.getProfessional(id));
    }

    @PutMapping()
    public ResponseEntity<ProfessionalDetailsDTO> updateProfessional(@RequestBody @Valid UpdateProfessionalDTO updateProfessionalDTO) {
        return ResponseEntity.ok(professionalService.updateProfessional(updateProfessionalDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
    }
}
