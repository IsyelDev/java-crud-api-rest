package org.isyel.bibliotecav1.Repository;

import org.isyel.bibliotecav1.Entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {
    // Puedes definir métodos adicionales aquí si es necesario
}
