package cat.itacademy.s04.s02.n01.fruit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record FruitRequestDTO(
    @NotBlank(message = "Fruit cannot be blank")
    String name,
    @Positive(message = "Weight must be positive")
    int weightInKilos
) { }
