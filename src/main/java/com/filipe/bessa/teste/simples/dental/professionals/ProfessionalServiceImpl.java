package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.GetProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.UpdateProfessionalDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {
    @Override
    public void createProfessional(CreateProfessionalDTO createProfessionalDTO) {
        System.out.println("Creating professional");
    }

    @Override
    public Page<GetProfessionalDTO> getProfessionals() {
        System.out.println("Getting professionals");
        return null;
    }

    @Override
    public GetProfessionalDTO getProfessional(Long id) {
        System.out.println("Getting professional");
        return null;
    }

    @Override
    public void updateProfessional(UpdateProfessionalDTO updateProfessionalDTO) {
        System.out.println("Updating professional");
    }

    @Override
    public void deleteProfessional(Long id) {
        System.out.println("Deleting professional");
    }
}
