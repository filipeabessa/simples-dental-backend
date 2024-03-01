package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.GetProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.UpdateProfessionalDTO;
import org.springframework.data.domain.Page;

public interface ProfessionalService {
    void createProfessional(CreateProfessionalDTO createProfessionalDTO);
    Page<GetProfessionalDTO>getProfessionals();
    GetProfessionalDTO getProfessional(Long id);
    void updateProfessional(UpdateProfessionalDTO updateProfessionalDTO);
    void deleteProfessional(Long id);
}
