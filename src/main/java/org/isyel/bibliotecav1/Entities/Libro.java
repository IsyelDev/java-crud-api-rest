package org.isyel.bibliotecav1.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Column(name="isbn", unique = true, nullable = false)
    private Long isbn;

    @Column(name="titulo", nullable = false)
    private String titulo;

    @Column(name="ejemplares", nullable = false)
    private Integer ejemplares;

    @Column(name="fecha_alta", nullable = false)
    private LocalDate alta;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_editorial", nullable = false)
    private Editorial editorial;
}
