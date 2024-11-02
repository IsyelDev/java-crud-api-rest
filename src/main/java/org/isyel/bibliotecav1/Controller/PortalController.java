package org.isyel.bibliotecav1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortalController {
    @RequestMapping("/")

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

}
