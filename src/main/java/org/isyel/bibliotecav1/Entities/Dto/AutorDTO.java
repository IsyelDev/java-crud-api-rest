package org.isyel.bibliotecav1.Entities.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AutorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
}
