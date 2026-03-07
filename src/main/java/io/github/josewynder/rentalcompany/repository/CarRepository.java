package io.github.josewynder.rentalcompany.repository;

import io.github.josewynder.rentalcompany.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findByModel(String model);
}
