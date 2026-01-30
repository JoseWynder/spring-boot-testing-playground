package io.github.josewynder.rentalcompany.repository;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
