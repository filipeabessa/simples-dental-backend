package com.filipe.bessa.teste.simples.dental.contacts;

import com.filipe.bessa.teste.simples.dental.contacts.dto.CreateContactDTO;
import com.filipe.bessa.teste.simples.dental.professionals.Professional;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    private String name;

    private String contact;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Contact(CreateContactDTO createContactDTO) {
        this.name = createContactDTO.name();
        this.contact = createContactDTO.contact();
        this.createdAt = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact contactObj)) return false;
        if (contactObj.getId() != null && getId() != null) {
            return getId().equals(contactObj.getId());
        }

        return name.equals(contactObj.name)
                && contact.equals(contactObj.contact)
                && professional.equals(contactObj.professional);
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public void setId(Long id) {
        this.id = id;
    }
}