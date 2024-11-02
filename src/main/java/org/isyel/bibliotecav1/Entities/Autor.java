package org.isyel.bibliotecav1.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="nombre", unique = true, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "autor" , cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();
}
