package dev.java10x.MagicFridgeAI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Data
@Entity
@Table(name="food_item")
@NoArgsConstructor
@AllArgsConstructor
@ToString()
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    private Integer quantidade;

    private LocalDate validade;
}
