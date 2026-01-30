package io.github.josewynder.rentalcompany.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    CarEntity car;

    @BeforeEach
    void setUp() {
        car = new CarEntity("Honda Civic", 200.0, 2027);
    }

    @Test
    void shouldSave() {
        CarEntity carEntity = new CarEntity("Sedan", 100.0, 2027);

        carRepository.save(carEntity);

        assertNotNull(carEntity.getId());
    }

    @Test
    void shouldFindById() {
        CarEntity savedCar = carRepository.save(car);

        Optional<CarEntity> carFound = carRepository.findById(savedCar.getId());

        assertThat(carFound).isPresent();
        assertThat(carFound.get().getModel()).isEqualTo("Honda Civic");
        assertThat(carFound.get()).isEqualTo(car);
    }

    @Test
    void shouldUpdate() {
        CarEntity savedCar = carRepository.save(car);

        savedCar.setReleaseYear(2028);

        savedCar = carRepository.save(savedCar);

        assertThat(savedCar.getReleaseYear()).isEqualTo(2028);
    }

    @Test
    void shouldDeleteById() {
        CarEntity savedCar = carRepository.save(car);

        carRepository.deleteById(savedCar.getId());

        Optional<CarEntity> carFound = carRepository.findById(savedCar.getId());

        assertThat(carFound).isNotPresent();
    }
}
