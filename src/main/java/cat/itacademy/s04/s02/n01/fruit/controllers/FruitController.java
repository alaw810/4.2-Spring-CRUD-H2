package cat.itacademy.s04.s02.n01.fruit.controllers;

import cat.itacademy.s04.s02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.services.FruitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits")
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping
    public ResponseEntity<FruitResponseDTO> createFruit(@Valid @RequestBody FruitRequestDTO request) {
        FruitResponseDTO created = fruitService.addFruit(request);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<FruitResponseDTO>> getAllFruits() {
        List<FruitResponseDTO> fruits = fruitService.getAllFruits();
        return ResponseEntity.ok(fruits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FruitResponseDTO> getFruitById(@PathVariable Long id) {
        return fruitService.getFruitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
