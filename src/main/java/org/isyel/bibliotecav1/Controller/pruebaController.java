package org.isyel.bibliotecav1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/egg")
public class pruebaController {
@GetMapping("/prueba")
    public String prueba() {
    return "prueba";
}
}
