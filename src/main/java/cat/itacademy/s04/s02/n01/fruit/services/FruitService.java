package cat.itacademy.s04.s02.n01.fruit.services;

import cat.itacademy.s04.s02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitResponseDTO;

import java.util.List;

public interface FruitService {
    FruitResponseDTO addFruit(FruitRequestDTO request);
    List<FruitResponseDTO> getAllFruits();
}
