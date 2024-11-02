package org.isyel.bibliotecav1.Repository;

import org.isyel.bibliotecav1.Entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String> {
    // Puedes definir métodos adicionales aquí si es necesario
}
