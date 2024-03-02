package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.ProfessionalDetailsDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.UpdateProfessionalDTO;
import org.springframework.data.domain.Page;

public interface ProfessionalService {
    ProfessionalDetailsDTO createProfessional(CreateProfessionalDTO createProfessionalDTO);
    Page<ProfessionalDetailsDTO>getProfessionals();
    ProfessionalDetailsDTO getProfessional(Long id);
    void updateProfessional(UpdateProfessionalDTO updateProfessionalDTO);
    void deleteProfessional(Long id);
}
