package org.isyel.bibliotecav1.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "editorial")
public class Editorial {
    @Id
    private String id;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;

    @PrePersist
    public void generarID() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @OneToMany(mappedBy = "editorial", cascade = CascadeType.ALL)
    private List<Libro> libros;
}
