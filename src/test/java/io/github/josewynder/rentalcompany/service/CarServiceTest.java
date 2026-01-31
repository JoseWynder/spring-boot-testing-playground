package io.github.josewynder.rentalcompany.service;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import io.github.josewynder.rentalcompany.model.exceptions.EntityNotFoundException;
import io.github.josewynder.rentalcompany.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    CarService carService;

    @Mock
    CarRepository carRepository;

    @Test
    void shouldSaveCar() {
        CarEntity carToSave = new CarEntity("Sedan", 10.0, 2027);

        CarEntity carToReturn = new CarEntity(1L, "Sedan", 100.0, 2027);

        Mockito.when( carRepository.save(Mockito.any()) ).thenReturn(carToReturn);

        CarEntity savedCar = carService.save(carToSave);

        assertNotNull(savedCar);
        assertEquals("Sedan", savedCar.getModel());

        verify(carRepository).save(Mockito.any());
    }

    @Test
    void shouldThrowExceptionWhenSaveCarWithInvalidDailyPrice() {
        CarEntity car = new CarEntity("Sedan", 0, 2027);

        Throwable throwable = catchThrowable(() -> carService.save(car));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);

        verify(carRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldUpdateCarWithSuccess() {
        Long carId = 1L;

        CarEntity existingCar = new CarEntity(
                carId,
                "Gol",
                80.0,
                2026);

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

        verify(carRepository).findById(carId);
        verify(carRepository).save(Mockito.any(CarEntity.class));
    }

    @Test
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

        verify(carRepository, Mockito.never()).save(Mockito.any());
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    void shouldDeleteWithSuccess() {
        Long carId = 1L;
        CarEntity car = new CarEntity(
                carId,
                "Sedan",
                10.0,
                2027);

        Mockito.when(carRepository.findById(carId))
                .thenReturn(Optional.of(car));

        carService.delete(carId);

        verify(carRepository).delete(car);
    }

    @Test
    void shouldThrowExceptionWhenDeleteNonexistingCar() {
        Long carId = 1L;

        Mockito.when(carRepository.findById(carId))
                .thenReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> carService.delete(carId));
        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);

        verify(carRepository, Mockito.never()).delete(Mockito.any());
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    void shouldFindByIdWithSuccess() {
        Long carId = 1L;
        CarEntity car = new CarEntity(
                carId,
                "Sedan",
                10.0,
                2027);

        Mockito.when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        CarEntity carFound = carService.findById(carId);

        verify(carRepository).findById(carId);
        assertEquals("Sedan", carFound.getModel());
        assertEquals(10.0, carFound.getDailyPrice());
        assertEquals(2027, carFound.getReleaseYear());
    }

    @Test
    void shouldThrowExceptionWhenFindNonexistingCar() {
        Long carId = 1L;

        Mockito.when(carRepository.findById(carId)).thenReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> carService.findById(carId));
        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);

        verify(carRepository).findById(carId);
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    void shouldFindAll() {
        CarEntity car1 = new CarEntity(
                1L,
                "Sedan",
                10.0,
                2027);

        CarEntity car2 = new CarEntity(
                2L,
                "Gol",
                80.0,
                2026);

        Mockito.when(carRepository.findAll())
                .thenReturn(List.of(car1, car2));

        List<CarEntity> allFound = carService.findAll();

        verify(carRepository).findAll();
        assertEquals(2, allFound.size());
        assertEquals("Sedan", allFound.get(0).getModel());
        assertEquals("Gol", allFound.get(1).getModel());
    }
}