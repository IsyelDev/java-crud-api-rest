package org.isyel.bibliotecav1.Controller;

import org.isyel.bibliotecav1.Entities.Dto.AutorDTO;
import org.isyel.bibliotecav1.Excepciones.ValidationException;
import org.isyel.bibliotecav1.Services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/autor")


public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/registrar")
    public String registrarAutor() {
        return "autor_form.html";
    }

    @GetMapping("/lista")
    public String listarAutor(Model model) {
        List<AutorDTO> autor = autorService.listarAutores();
        model.addAttribute("autor", autor);
        return "Listar/ListaAutor.html";
    }


    @PostMapping("/registro")
    public String registro(@ModelAttribute AutorDTO autorDTO , ModelMap modelMap) {
     try {
         autorService.createAutor(autorDTO);
         modelMap.put("exito", "¡Editorial registrado exitosamente!");
     }catch (ValidationException ex){
         modelMap.put("error", ex.getMessage());
        return "autor_form.html";
     }
     return "index.html";
    }

}
