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

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    CarService carService;

    @Mock
    CarRepository carRepository;

    @Test
    void shouldSaveCar() {
        CarEntity carToSave = new CarEntity("Sedan", 10.0, 2027);

        CarEntity carToReturn = new CarEntity("Sedan", 100.0, 2027);
        carToReturn.setId(1L);

        Mockito.when( carRepository.save(Mockito.any()) ).thenReturn(carToReturn);

        CarEntity savedCar = carService.save(carToSave);

        assertNotNull(savedCar);
        assertEquals("Sedan", savedCar.getModel());

        Mockito.verify(carRepository).save(Mockito.any());
    }
}