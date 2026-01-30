package io.github.josewynder.rentalcompany.repository;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import io.github.josewynder.rentalcompany.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<Car> findByModel(String model);
}
