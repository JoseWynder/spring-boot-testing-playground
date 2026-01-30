package io.github.josewynder.rentalcompany.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import io.github.josewynder.rentalcompany.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    CarService carService;

    @Mock
    CarRepository carRepository;

    @Test
    void shouldSaveCar() {
        Mockito
                .when(carRepository.findById(1L))
                .thenReturn(Optional.of(new CarEntity("Mock test", 10.0, 2026)));

        Optional<CarEntity> carFound = carRepository.findById(1L);
        System.out.println(carFound.get().getModel());

    }
}