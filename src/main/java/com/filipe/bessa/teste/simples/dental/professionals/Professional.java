package com.filipe.bessa.teste.simples.dental.professionals;

import com.filipe.bessa.teste.simples.dental.contacts.Contact;
import com.filipe.bessa.teste.simples.dental.professionals.dto.CreateProfessionalDTO;
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

    public Professional(CreateProfessionalDTO createProfessionalDTO) {
        this.name = createProfessionalDTO.name();
        this.position = createProfessionalDTO.position();
        this.birthDate = createProfessionalDTO.birthDate();
        this.contacts = createProfessionalDTO.contacts();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Custom setter for contacts to maintain encapsulation
    public void setContacts(List<Contact> contacts) {
        if (this.contacts.isEmpty() && contacts != null) {
            this.contacts.addAll(contacts);
        }
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Contact> getContacts() {
        if (this.contacts == null) {
            return new ArrayList<>();
        }
        return List.copyOf(this.contacts);
    }
    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professional professional)) return false;

        if (professional.getId() != null && getId() != null) {
            return getId().equals(professional.getId());
        }

        return professional.getName().equals(getName()) &&
                professional.getPosition().equals(getPosition()) &&
                professional.getBirthDate().equals(getBirthDate());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
