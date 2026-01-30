package io.github.josewynder.rentalcompany.service;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import io.github.josewynder.rentalcompany.model.exceptions.EntityNotFoundException;
import io.github.josewynder.rentalcompany.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarEntity save(CarEntity car) {
        if(car.getDailyPrice() <= 0) {
            throw new IllegalArgumentException("Price must be grater than 0");
        }
        return carRepository.save(car);
    }

    public CarEntity update(Long id, CarEntity car) {
        CarEntity existingCar = carRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("There is no car with that ID"));

        existingCar.setModel(car.getModel());
        existingCar.setDailyPrice(car.getDailyPrice());
        existingCar.setReleaseYear(car.getReleaseYear());

        return carRepository.save(existingCar);
    }

    public void deleteById(Long id) {
        CarEntity existingCar = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no car with that ID"));

        carRepository.deleteById(id);
    }

    public CarEntity findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no car with that ID"));
    }

    public List<CarEntity> findAll() {
        return carRepository.findAll();
    }
}
