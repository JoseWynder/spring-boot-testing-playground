package io.github.josewynder.rentalcompany.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarTest {


    @Test
    @DisplayName("Must to calculate the correct rental amount")
    void mustCalculateRentalValue() {
        Car car = new Car("Sedan", 100.0);
        double total = car.calculateRentalValue(3);
        Assertions.assertEquals(300.0, total);
    }
}
