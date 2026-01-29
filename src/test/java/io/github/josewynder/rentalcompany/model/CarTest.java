package io.github.josewynder.rentalcompany.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {


    @Test
    @DisplayName("Must to calculate the correct rental amount")
    void mustCalculateRentalValue() {
        // 1. Scenario
        Car car = new Car("Sedan", 100.0);

        // 2. Execution
        double total = car.calculateRentalValue(3);

        // 3. Verification
        Assertions.assertEquals(300.0, total);
    }

    @Test
    @DisplayName("Must to calculate the rental value with the discount")
    void mustCalculateRentalValueWithDiscount() {
        // 1. Scenario
        Car car = new Car("Sedan", 100.0);
        int quantityDays = 10;

        // 2. Execution
        double total = car.calculateRentalValue(quantityDays);

        // 3. Verification
        Assertions.assertEquals(950.0, total);
    }
}
