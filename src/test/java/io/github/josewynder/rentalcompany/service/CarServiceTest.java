package io.github.josewynder.rentalcompany.service;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import io.github.josewynder.rentalcompany.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

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

    @Test
    void shouldThrowExceptionWhenSaveCarWithInvalidDailyPrice() {
        CarEntity car = new CarEntity("Sedan", 0, 2027);

        Throwable throwable = catchThrowable(() -> carService.save(car));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(carRepository, Mockito.never()).save(Mockito.any());
    }
}