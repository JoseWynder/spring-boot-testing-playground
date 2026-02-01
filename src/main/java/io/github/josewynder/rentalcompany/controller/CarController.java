package io.github.josewynder.rentalcompany.controller;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import io.github.josewynder.rentalcompany.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody CarEntity car) {
        try {
        CarEntity savedCar = carService.save(car);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedCar);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }
}
