package org.isyel.bibliotecav1.Controller;


import org.isyel.bibliotecav1.Entities.Dto.LibroDTO;
import org.isyel.bibliotecav1.Entities.Libro;
import org.isyel.bibliotecav1.Excepciones.ValidationException;
import org.isyel.bibliotecav1.Services.AutorService;
import org.isyel.bibliotecav1.Services.EditorialService;
import org.isyel.bibliotecav1.Services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/libro")
public class LibroController {
    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private EditorialService editorialService;

    @GetMapping("/registrar")
    public String libro() {
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute("libro") LibroDTO libro)  {
        try {
        libroService.createLibro(libro);
        }catch (ValidationException ex){
            Logger.getLogger(LibroController.class.getName()).log(Level.SEVERE, null, ex);
          return "libro_form.html";
        }
        return "index.html";
    }

}
