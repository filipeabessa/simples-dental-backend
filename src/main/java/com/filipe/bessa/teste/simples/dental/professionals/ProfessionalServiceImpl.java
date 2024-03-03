package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.contacts.Contact;
import com.filipe.bessa.teste.simples.dental.professionals.dto.CreateProfessionalDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.ProfessionalDetailsDTO;
import com.filipe.bessa.teste.simples.dental.professionals.dto.UpdateProfessionalDTO;
import domain.exception.ProfessionalNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
        Professional professional = verifyProfessionalExists(id);

        return new ProfessionalDetailsDTO(professional);
    }

    @Override
    public void updateProfessional(UpdateProfessionalDTO updateProfessionalDTO) {
        Professional professional = verifyProfessionalExists(updateProfessionalDTO.id());

        String name = updateProfessionalDTO.name();
        Position position = updateProfessionalDTO.position();
        LocalDate birthDate = updateProfessionalDTO.birthDate();
        List<Contact> contacts = updateProfessionalDTO.contacts();

        professional.setName(name != null ? name : professional.getName());
        professional.setPosition(position != null ? position : professional.getPosition());
        professional.setBirthDate(birthDate != null ? birthDate : professional.getBirthDate());
        professional.setContacts(contacts != null ? contacts : professional.getContacts());
        professional.setUpdatedAt(LocalDateTime.now());

        professionalRepository.save(professional);
    }

    @Override
    public void deleteProfessional(Long id) {
        Professional professional = verifyProfessionalExists(id);
        professionalRepository.delete(professional);

    }

    private Professional verifyProfessionalExists(Long id) {
        return professionalRepository
                .findById(id)
                .orElseThrow(ProfessionalNotFoundException::new);
    }
}
