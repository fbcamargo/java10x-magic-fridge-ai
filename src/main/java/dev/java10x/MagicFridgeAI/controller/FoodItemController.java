package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.dto.FoodItemDto;
import dev.java10x.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    // POST
    @PostMapping
    public ResponseEntity<FoodItemDto> criar(@RequestBody FoodItemDto foodItemDto) {
        FoodItemDto createdFoodItemDto = this.foodItemService.salvar(foodItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFoodItemDto);
    }

    // GET
    @GetMapping
    public ResponseEntity<List<FoodItemDto>> listar() {
        List<FoodItemDto> foodItems = this.foodItemService.listar();
        return ResponseEntity.ok(foodItems);
    }

    // GET ID
    @GetMapping("/{id}")
    public ResponseEntity<FoodItemDto> detalhes(@PathVariable Long id) {
        FoodItemDto foodItem = this.foodItemService.detalhes(id);
        if(foodItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foodItem);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> alterar(@PathVariable Long id, @RequestBody FoodItemDto foodItemDto) {
        FoodItemDto findedFoodItemDto = this.foodItemService.detalhes(id);
        if(findedFoodItemDto != null) {
            this.foodItemService.alterar(id, foodItemDto);
            return ResponseEntity.ok("Alimento alterado com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id){
        FoodItemDto foodItemDto = this.foodItemService.detalhes(id);
        if(foodItemDto != null) {
            this.foodItemService.remover(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
