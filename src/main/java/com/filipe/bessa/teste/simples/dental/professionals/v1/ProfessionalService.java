package com.filipe.bessa.teste.simples.dental.professionals.v1;

import com.filipe.bessa.teste.simples.dental.professionals.v1.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.v1.dto.ProfessionalDetailsDTO;
import com.filipe.bessa.teste.simples.dental.professionals.v1.dto.UpdateProfessionalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfessionalService {
    ProfessionalDetailsDTO createProfessional(CreateProfessionalDTO createProfessionalDTO);
    Page<ProfessionalDetailsDTO>getProfessionals(Pageable pagination);
    ProfessionalDetailsDTO getProfessional(Long id);
    ProfessionalDetailsDTO updateProfessional(UpdateProfessionalDTO updateProfessionalDTO);
    void deleteProfessional(Long id);
}
