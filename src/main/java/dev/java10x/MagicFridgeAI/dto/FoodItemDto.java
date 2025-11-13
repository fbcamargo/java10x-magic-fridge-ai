package dev.java10x.MagicFridgeAI.dto;

import dev.java10x.MagicFridgeAI.model.CategoriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDto {
    private Long id;

    private String nome;

    private CategoriaEnum categoria;

    private Integer quantidade;

    private LocalDate validade;
}
