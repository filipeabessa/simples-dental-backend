package com.filipe.bessa.teste.simples.dental.professionals;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    @NonNull
    Page<Professional> findAll(@NonNull Pageable pagination);
}
