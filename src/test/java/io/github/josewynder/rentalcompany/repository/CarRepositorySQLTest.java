package io.github.josewynder.rentalcompany.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import io.github.josewynder.rentalcompany.model.CarEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CarRepositorySQLTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    @Sql("/sql/fill-cars.sql")
    void shouldFindCarByModel() {
        List<CarEntity> suv = carRepository.findByModel("SUV");

        CarEntity car = suv.getFirst();

        assertEquals(1, suv.size());

        assertThat(car.getModel()).isEqualTo("SUV");
        assertThat(car.getDailyPrice()).isEqualTo(150.0);

    }
}
