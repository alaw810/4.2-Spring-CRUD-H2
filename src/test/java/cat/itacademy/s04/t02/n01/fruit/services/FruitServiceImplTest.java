package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.repository.FruitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FruitServiceImplTest {

    @Mock
    private FruitRepository fruitRepository;

    @InjectMocks
    private FruitServiceImpl fruitService;

    private Fruit fruit;

    @BeforeEach
    void setUp() {
        fruit = new Fruit(1L, "Apple", 2);
    }

    @Test
    void addFruit_ShouldReturnFruitResponseDTO() {
        // Arrange
        FruitRequestDTO request = new FruitRequestDTO("Banana", 3);
        Fruit savedFruit = new Fruit(1L, "Banana", 3);
        when(fruitRepository.save(any(Fruit.class))).thenReturn(savedFruit);

        // Act
        FruitResponseDTO result = fruitService.addFruit(request);

        // Assert
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("Banana");
        assertThat(result.weightInKilos()).isEqualTo(3);
        verify(fruitRepository, times(1)).save(any(Fruit.class));
    }

    @Test
    void getAllFruits_ShouldReturnListOfFruitResponseDTO() {
        when(fruitRepository.findAll()).thenReturn(List.of(fruit));

        List<FruitResponseDTO> result = fruitService.getAllFruits();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).name()).isEqualTo("Apple");
        verify(fruitRepository, times(1)).findAll();
    }

    @Test
    void getFruitById_WhenExists_ShouldReturnFruitResponseDTO() {
        when(fruitRepository.findById(1L)).thenReturn(Optional.of(fruit));

        Optional<FruitResponseDTO> result = fruitService.getFruitById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().name()).isEqualTo("Apple");
        verify(fruitRepository).findById(1L);
    }

    @Test
    void getFruitById_WhenNotExists_ShouldThrowException() {
        when(fruitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fruitService.getFruitById(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Fruit with ID " + 1L + " not found");
    }

    @Test
    void updateFruit_WhenExists_ShouldUpdateAndReturnDTO() {
        FruitRequestDTO update = new FruitRequestDTO("Orange", 5);
        when(fruitRepository.findById(1L)).thenReturn(Optional.of(fruit));
        when(fruitRepository.save(any(Fruit.class))).thenReturn(new Fruit(1L, "Orange", 5));

        FruitResponseDTO result = fruitService.updateFruit(1L, update);

        assertThat(result.name()).isEqualTo("Orange");
        assertThat(result.weightInKilos()).isEqualTo(5);
        verify(fruitRepository).save(any(Fruit.class));
    }

    @Test
    void updateFruit_WhenNotExists_ShouldThrowException() {
        when(fruitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fruitService.updateFruit(1L, new FruitRequestDTO("Orange", 5)))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Fruit with ID " + 1L + " not found");
    }

    @Test
    void deleteFruit_WhenExists_ShouldCallRepositoryDelete() {
        when(fruitRepository.findById(1L)).thenReturn(Optional.of(fruit));

        fruitService.deleteFruit(1L);

        verify(fruitRepository).delete(fruit);
        verify(fruitRepository, never()).deleteById(anyLong());
    }

    @Test
    void deleteFruit_WhenNotExists_ShouldThrowException() {
        when(fruitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fruitService.deleteFruit(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Fruit with ID " + 1L + " not found");
    }
}
