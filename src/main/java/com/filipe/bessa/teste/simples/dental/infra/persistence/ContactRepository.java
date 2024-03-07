package com.filipe.bessa.teste.simples.dental.infra.persistence;

import com.filipe.bessa.teste.simples.dental.domain.model.contacts.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Override
    @NonNull
    Page<Contact> findAll(@NonNull Pageable pagination);
}
