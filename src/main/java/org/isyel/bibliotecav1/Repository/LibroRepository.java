package org.isyel.bibliotecav1.Repository;

import org.isyel.bibliotecav1.Entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.titulo =:titulo")
    Libro findByTitulo(@Param("titulo") String titulo);

    @Query("SELECT l FROM Libro l WHERE l.autor.id =:id_autor")
    Libro findByAutor(@Param("id_autor") UUID id_autor);
}
