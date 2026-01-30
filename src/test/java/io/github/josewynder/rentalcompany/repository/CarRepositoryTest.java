package io.github.josewynder.rentalcompany.repository;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    void shouldSave() {
        CarEntity carEntity = new CarEntity("Sedan", 100.0);

        carRepository.save(carEntity);

        assertNotNull(carEntity.getId());
    }
}
