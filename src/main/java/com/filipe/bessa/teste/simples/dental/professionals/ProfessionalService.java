package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.ProfessionalDetailsDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.UpdateProfessionalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfessionalService {
    ProfessionalDetailsDTO createProfessional(CreateProfessionalDTO createProfessionalDTO);
    Page<ProfessionalDetailsDTO>getProfessionals(Pageable pagination);
    ProfessionalDetailsDTO getProfessional(Long id);
    ProfessionalDetailsDTO updateProfessional(UpdateProfessionalDTO updateProfessionalDTO);
    void deleteProfessional(Long id);
}
