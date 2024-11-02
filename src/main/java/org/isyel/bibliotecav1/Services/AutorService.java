package org.isyel.bibliotecav1.Services;

import org.isyel.bibliotecav1.Entities.Autor;
import org.isyel.bibliotecav1.Entities.Dto.AutorDTO;
import org.isyel.bibliotecav1.Entities.Dto.EditorialDTO;
import org.isyel.bibliotecav1.Excepciones.ValidationException;
import org.isyel.bibliotecav1.Repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void createAutor(AutorDTO autorDTO) throws ValidationException {
        validarNombre(autorDTO);
        Autor autor = new Autor();
        autor.setNombre(autorDTO.getNombre());
        autorRepository.save(autor);
    }

    @Transactional(readOnly = true)

    public List<AutorDTO> listarAutores(){
        return autorRepository.findAll()
                .stream()
                .map(autor -> new AutorDTO(autor.getNombre()))
                .collect(Collectors.toList());
    }


    @Transactional
    public void UpdateAutor(UUID id, AutorDTO autorDTO) throws ValidationException {
        validarNombre(autorDTO);
        Autor autor = validarId(id);
        autor.setNombre(autor.getNombre());
        autorRepository.save(autor);
    }

    @Transactional
    public void deleteEditorial(UUID id) throws ValidationException {
        Autor autor = validarId(id);
        autorRepository.delete(autor);
    }

    private void validarNombre(AutorDTO autorDTO) throws ValidationException {
        if (autorDTO.getNombre() == null || autorDTO.getNombre().isEmpty()) {
            throw new ValidationException("El nombre es obligatorio");
        }
    }


    public Autor validarId(UUID id) throws ValidationException {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isEmpty()) {
            throw new ValidationException("No se encontro el id de la Autor");
        }
        return autor.get();
    }

}
