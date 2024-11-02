package org.isyel.bibliotecav1.Controller;

import org.isyel.bibliotecav1.Entities.Dto.AutorDTO;
import org.isyel.bibliotecav1.Entities.Dto.EditorialDTO;
import org.isyel.bibliotecav1.Entities.Editorial;
import org.isyel.bibliotecav1.Excepciones.ValidationException;
import org.isyel.bibliotecav1.Services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
@RequestMapping("editorial")

public class EditorialController {
    @Autowired
    private EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar() {
        return "editorial_form.html";
    }

    @GetMapping("/lista")
    public String listarEditorial(Model model) {
        List<EditorialDTO> editorial = editorialService.listarEditorial();
        model.addAttribute("editorial", editorial);
        return "Listar/ListaEditoriales.html";
    }


    @PostMapping("/registro")
    public String registrar(@ModelAttribute EditorialDTO editorialDTO , ModelMap model) {
        try {
            editorialService.createEditorial(editorialDTO);
            model.put("exito","editorial registrado exitosamente");
        } catch (ValidationException ex) {
            model.put("error",ex.getMessage());
            return "editorial_form.html";
        }
        return "index.html";
    }

}