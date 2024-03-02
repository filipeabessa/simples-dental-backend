package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.ProfessionalDetailsDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.UpdateProfessionalDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProfessionalServiceImpl implements ProfessionalService {
    ProfessionalRepository professionalRepository;
    @Override
    public ProfessionalDetailsDTO createProfessional(CreateProfessionalDTO createProfessionalDTO) {
        Professional savedProfessional = professionalRepository.save(new Professional(createProfessionalDTO));

        return new ProfessionalDetailsDTO(savedProfessional);
    }

    @Override
    public Page<ProfessionalDetailsDTO> getProfessionals(Pageable pagination) {
        return professionalRepository.findAll(pagination).map(ProfessionalDetailsDTO::new);
    }

    @Override
    public ProfessionalDetailsDTO getProfessional(Long id) {
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
