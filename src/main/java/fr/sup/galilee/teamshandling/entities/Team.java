package fr.sup.galilee.teamshandling.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Team {
    @Id
    private String id;
    private String name;
    @Column(unique = true)
    private String acronym;

    @PrePersist
    public void prePersist() {
        if (getId() == null) {
            setId(UUID.randomUUID().toString());
        }
    }
}
