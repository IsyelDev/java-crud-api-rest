package org.isyel.bibliotecav1.Entities.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class EditorialDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
}
