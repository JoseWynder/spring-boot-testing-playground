package io.github.josewynder.rentalcompany.service;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import io.github.josewynder.rentalcompany.model.exceptions.EntityNotFoundException;
import io.github.josewynder.rentalcompany.repository.CarRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.Optional;

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

    @Test
    void shouldUpdateCarWithSuccess() {
        Long carId = 1L;

        CarEntity existingCar = new CarEntity(
                "Gol",
                80.0,
                2026);
        existingCar.setId(carId);

        CarEntity newCar = new CarEntity(
                "Sedan",
                10.0,
                2027);

        Mockito.when(carRepository.findById(carId))
                .thenReturn(Optional.of(existingCar));

        Mockito.when(carRepository.save(Mockito.any(CarEntity.class)))
                .thenReturn(newCar);

        CarEntity updatedCar = carService.update(carId, newCar);

        assertEquals("Sedan", updatedCar.getModel());
        assertEquals(10.0, updatedCar.getDailyPrice());
        assertEquals(2027, updatedCar.getReleaseYear());

        Mockito.verify(carRepository).findById(carId);
        Mockito.verify(carRepository).save(Mockito.any(CarEntity.class));
        Mockito.verifyNoMoreInteractions(carRepository);
    }

    @Test
    @Disabled
    void shouldThrowExceptionWhenUpdateNonexistingCar() {
        CarEntity newCar = new CarEntity(
                "Sedan",
                10.0,
                2027);
        Long nonexistentId = 1L;

        Mockito.when(carRepository.findById(nonexistentId))
                .thenReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> carService.update(nonexistentId, newCar));

        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);

        Mockito.verify(carRepository, Mockito.never()).save(Mockito.any());
        Mockito.verifyNoMoreInteractions(carRepository);
    }
}