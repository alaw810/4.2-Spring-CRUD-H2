package cat.itacademy.s04.s02.n01.fruit.services;

import cat.itacademy.s04.s02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.model.Fruit;
import cat.itacademy.s04.s02.n01.fruit.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImpl implements FruitService{

    private final FruitRepository fruitRepository;

    public FruitServiceImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public FruitResponseDTO addFruit(FruitRequestDTO request) {
        Fruit fruit = new Fruit(null, request.name(), request.weightInKilos());
        Fruit saved = fruitRepository.save(fruit);
        return mapToDto(saved);
    }

    @Override
    public List<FruitResponseDTO> getAllFruits() {
        List<Fruit> fruits = fruitRepository.findAll();
        return mapToDtoList(fruits);
    }

    private FruitResponseDTO mapToDto(Fruit fruit) {
        return new FruitResponseDTO(
                fruit.getId(),
                fruit.getName(),
                fruit.getWeightInKilos());
    }

    private List<FruitResponseDTO> mapToDtoList(List<Fruit> fruits) {
        return fruits.stream()
                .map(this::mapToDto)
                .toList();
    }

}
