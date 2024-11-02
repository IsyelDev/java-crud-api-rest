package org.isyel.bibliotecav1.Controller;

import org.isyel.bibliotecav1.Entities.Autor;
import org.isyel.bibliotecav1.Entities.Dto.AutorDTO;
import org.isyel.bibliotecav1.Excepciones.ValidationException;
import org.isyel.bibliotecav1.Repository.AutorRepository;
import org.isyel.bibliotecav1.Services.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController

@RequestMapping("/autor")
public class AutorControllerRest {

    private final AutorRepository autorRepository;
    private final AutorService autorService;

    public AutorControllerRest(AutorRepository autorRepository, AutorService autorService) {
        this.autorRepository = autorRepository;
        this.autorService = autorService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> registro(@RequestBody AutorDTO autorDTO) {
        try {
            autorService.createAutor(autorDTO);
            return ResponseEntity.status(HttpStatus.CREATED)  // Devuelve 201 Created
                    .body("Registro registrado exitosamente!"); // Mensaje de éxito
        } catch (ValidationException ex) {
            System.out.println("Error de coordinación: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY) // o HttpStatus.BAD_REQUEST
                    .body("Error de validación: " + ex.getMessage()); // Mensaje de error
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Autor>> obtenerTodosLosAutores() {
        List<Autor> autores = autorRepository.findAll();
        return ResponseEntity.ok(autores); // Devuelve 200 OK con la lista de autores
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable UUID id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el autor con el ID: " + id));
        return ResponseEntity.ok(autor); // Devuelve 200 OK con el autor encontrado
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizarAutor(@PathVariable UUID id, @RequestBody Autor detallesAutor) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el autor con el ID: " + id));

        // Actualiza los detalles del autor
        autor.setNombre(detallesAutor.getNombre());
        // Guarda los cambios y devuelve el autor actualizado
        return ResponseEntity.ok(autorRepository.save(autor)); // Devuelve 200 OK con el autor actualizado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarAutor(@PathVariable UUID id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el autor con el ID: " + id));
        autorRepository.delete(autor);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("El autor con el ID: " + id + " fue eliminado correctamente"); // Devuelve 204 No Content
    }
}
