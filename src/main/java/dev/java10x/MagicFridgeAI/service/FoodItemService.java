package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.dto.FoodItemDto;
import dev.java10x.MagicFridgeAI.mapper.FootItemMapper;
import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;
    private final FootItemMapper footItemMapper;

    public FoodItemService(
            FoodItemRepository foodItemRepository,
            FootItemMapper footItemMapper
    ) {
        this.foodItemRepository = foodItemRepository;
        this.footItemMapper = footItemMapper;
    }

    public FoodItemDto salvar(FoodItemDto foodItemDto) {
        FoodItem foodItem = this.footItemMapper.map(foodItemDto);
        return this.footItemMapper.map(this.foodItemRepository.save(foodItem));
    }

    public FoodItemDto alterar(Long id, FoodItemDto foodItemDto) {
        Optional<FoodItem> ninjaExistente = this.foodItemRepository.findById(id);
        if(ninjaExistente.isPresent()) {
            foodItemDto.setId(id);
            FoodItem foodItem = this.footItemMapper.map(foodItemDto);
            FoodItem ninjaSalvo = this.foodItemRepository.save(foodItem);
            return this.footItemMapper.map(ninjaSalvo);
        }
        return  null;
    }

    public List<FoodItemDto> listar() {
        List<FoodItem> foodItems = this.foodItemRepository.findAll();
        return foodItems.stream()
                .map(this.footItemMapper::map)
                .collect(Collectors.toList());
    }

    public FoodItemDto detalhes(Long id) {
        Optional<FoodItem> footItem = this.foodItemRepository.findById(id);
        return footItem.map(this.footItemMapper::map).orElse(null);
    }

    public void remover(Long id) {
        this.foodItemRepository.deleteById(id);
    }
}
