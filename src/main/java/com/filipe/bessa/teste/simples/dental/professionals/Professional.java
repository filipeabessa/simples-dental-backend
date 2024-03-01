package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.contacts.Contact;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "professionals")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Position position;

    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Custom setter for contacts to maintain encapsulation
    public void setContacts(List<Contact> contacts) {
        if (this.contacts.isEmpty() && contacts != null) {
            this.contacts.addAll(contacts);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professional professional)) return false;
        return getId().equals(professional.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
