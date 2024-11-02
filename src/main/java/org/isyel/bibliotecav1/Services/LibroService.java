package org.isyel.bibliotecav1.Services;

import org.isyel.bibliotecav1.Entities.Autor;
import org.isyel.bibliotecav1.Entities.Editorial;
import org.isyel.bibliotecav1.Entities.Libro;
import org.isyel.bibliotecav1.Entities.Dto.LibroDTO;
import org.isyel.bibliotecav1.Excepciones.ValidationException;
import org.isyel.bibliotecav1.Repository.AutorRepository;
import org.isyel.bibliotecav1.Repository.EditorialRepository;
import org.isyel.bibliotecav1.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public void createLibro(LibroDTO libroDTO) throws ValidationException {
        verificarDatos(libroDTO);
        Autor autor = validarAutor(libroDTO.getId_autor());
        Editorial editorial = validarEditorial(libroDTO.getId_editorial());
        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitulo());
        libro.setEjemplares(libro.getEjemplares());
        libro.setAlta(LocalDate.now());
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepository.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> mostrarLibros() {
        return libroRepository.findAll();
    }

    @Transactional
    public void updateLibro(Long isbn, LibroDTO libroDTO) throws ValidationException {
        verificarDatos(libroDTO);
        Libro libro = validarLibro(isbn);
        Autor autor = validarAutor(libroDTO.getId_autor());
        Editorial editorial = validarEditorial(libroDTO.getId_editorial());
        libro.setTitulo(libro.getTitulo());
        libro.setEjemplares(libro.getEjemplares());
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepository.save(libro);
    }

    @Transactional
    public void deleteLibro(Long isbn) throws ValidationException {
        Libro libro = validarLibro(isbn);
        libroRepository.delete(libro);
    }

    private void verificarDatos(LibroDTO libroDTO) throws ValidationException {
        if (libroDTO.getTitulo() == null || libroDTO.getTitulo().isEmpty()) {
            throw new ValidationException("El título no puede estar vacío.");
        }
        if (libroDTO.getEjemplares() == null || libroDTO.getEjemplares() < 0) {
            throw new ValidationException("La cantidad de ejemplares no puede ser negativa.");
        }
        if (libroDTO.getId_autor() == null) {
            throw new ValidationException("El ID del autor no puede estar vacío.");
        }
        if (libroDTO.getId_editorial() == null || libroDTO.getId_editorial().isEmpty()) {
            throw new ValidationException("El ID de la editorial no puede estar vacío.");
        }
    }

    private Libro validarLibro(Long id_libro) throws ValidationException {
        Optional<Libro> libro = libroRepository.findById(id_libro);
        if (libro.isEmpty()) {
            throw new ValidationException("El libro no existe.");
        }
        return libro.get();
    }

    private Editorial validarEditorial(String id_editorial) throws ValidationException {
        Optional<Editorial> editorial = editorialRepository.findById(id_editorial);
        if (editorial.isEmpty()) {
            throw new ValidationException("La editorial no existe.");
        }
        return editorial.get();
    }

    private Autor validarAutor(UUID id_autor) throws ValidationException {
        return autorRepository.findById(id_autor).orElseThrow(() -> new ValidationException("El autor no existe."));
    }
}
