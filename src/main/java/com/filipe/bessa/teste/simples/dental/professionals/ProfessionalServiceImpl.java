package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.exception.BusinessException;
import com.filipe.bessa.teste.simples.dental.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.ProfessionalDetailsDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.UpdateProfessionalDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        var professional = professionalRepository.findById(id).orElse(null);

        if (professional == null) {
            throw new BusinessException("Professional not found");
        }
        return new ProfessionalDetailsDTO(professional);
    }

    @Override
    public void updateProfessional(UpdateProfessionalDTO updateProfessionalDTO) {
        Professional professional = professionalRepository.findById(updateProfessionalDTO.id()).orElse(null);

        if (professional == null) {
            throw new BusinessException("Professional not found");
        }
        professional.setUpdatedAt(LocalDateTime.now());

        professionalRepository.save(professional);
    }

    @Override
    public void deleteProfessional(Long id) {
        Professional professional = professionalRepository.findById(id).orElse(null);

        if (professional == null) {
            throw new BusinessException("Professional not found");
        }
        professionalRepository.delete(professional);

    }
}
