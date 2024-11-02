package org.isyel.bibliotecav1.Services;

import org.isyel.bibliotecav1.Entities.Dto.EditorialDTO;
import org.isyel.bibliotecav1.Entities.Editorial;
import org.isyel.bibliotecav1.Excepciones.ValidationException;
import org.isyel.bibliotecav1.Repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public void createEditorial(EditorialDTO editorialDTO) throws ValidationException {
        validarNombre(editorialDTO);
        Editorial editorial = new Editorial();
        editorial.setNombre(editorialDTO.getNombre());
        editorialRepository.save(editorial);
    }
    @Transactional(readOnly = true)
    public List<EditorialDTO> listarEditorial() {
        return editorialRepository.findAll() // Asegúrate de que este método devuelve una lista de DTOs.
                .stream()
                .map(editorial -> new EditorialDTO(editorial.getNombre())) // Asumiendo que tienes un constructor adecuado.
                .collect(Collectors.toList());
    }


    @Transactional
    public void updateEditorial(String id,EditorialDTO editorialDTO) throws ValidationException {
        Editorial editorial = validarId(id);
        validarNombre(editorialDTO);
        editorial.setNombre(editorialDTO.getNombre());
        editorialRepository.save(editorial);
    } ;


    @Transactional
    public void deleteEditorial(String id) throws ValidationException{
        Editorial editorial = validarId(id);
        editorialRepository.delete(editorial);
    } ;



    @Transactional
    public void validarNombre(EditorialDTO editorialDTO) throws ValidationException {
        if(editorialDTO.getNombre() == null || editorialDTO.getNombre().isEmpty()){
            throw new ValidationException("El nombre no puede estar vacio");
        }
    }

    @Transactional
    public Editorial validarId(String id) throws ValidationException {
        Optional<Editorial> editorial = editorialRepository.findById(id);
        if (editorial.isEmpty()) {
            throw new ValidationException("El editorial no existe");
        }
        return editorial.get();
    }
}
