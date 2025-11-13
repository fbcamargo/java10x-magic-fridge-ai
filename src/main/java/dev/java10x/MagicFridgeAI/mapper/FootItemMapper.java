package dev.java10x.MagicFridgeAI.mapper;

import dev.java10x.MagicFridgeAI.dto.FoodItemDto;
import dev.java10x.MagicFridgeAI.model.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class FootItemMapper {
    public FoodItemDto map(FoodItem foodItem) {
        FoodItemDto foodItemDto = new FoodItemDto();
        foodItemDto.setId(foodItem.getId());
        foodItemDto.setNome(foodItem.getNome());
        foodItemDto.setQuantidade(foodItem.getQuantidade());
        foodItemDto.setCategoria(foodItem.getCategoria());
        foodItemDto.setValidade(foodItem.getValidade());
        return foodItemDto;
    }

    public FoodItem map(FoodItemDto foodItemDto) {
        FoodItem foodItem = new FoodItem();
        foodItem.setId(foodItemDto.getId());
        foodItem.setNome(foodItemDto.getNome());
        foodItem.setQuantidade(foodItemDto.getQuantidade());
        foodItem.setCategoria(foodItemDto.getCategoria());
        foodItem.setValidade(foodItemDto.getValidade());
        foodItem.setValidade(foodItemDto.getValidade());
        return foodItem;
    }
}
