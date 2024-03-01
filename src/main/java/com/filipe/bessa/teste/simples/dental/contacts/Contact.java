package com.filipe.bessa.teste.simples.dental.contacts;

import com.filipe.bessa.teste.simples.dental.professionals.Professional;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

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
        return getId().equals(contactObj.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}