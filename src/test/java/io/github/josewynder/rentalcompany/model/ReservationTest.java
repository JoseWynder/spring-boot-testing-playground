package io.github.josewynder.rentalcompany.model;

import static org.junit.jupiter.api.Assertions.*;

import io.github.josewynder.rentalcompany.exceptions.InvalidReservationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservationTest {

    Client client;
    Car car;

    @BeforeEach
    void setUp() {
        client = new Client("José");
        car = new Car("Hatch", 50.0);
    }

    @Test
    void shouldCreateReservation() {
        // Scenario
        int days = 5;

        // Execution
        Reservation reservation = new Reservation(client, car, days);

        // Verification
        Assertions.assertThat(reservation)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void shouldThrowExceptionWhenCreatingReservationWithInvalidDays() {
        int days = 0;

        assertThrows(InvalidReservationException.class,
                () -> new Reservation(client, car, days));
    }

    @Test
    void shouldCalculateReservationAmount() {
        int days = 3;

        Reservation reservation = new Reservation(client, car, days);

        double reservationCost = reservation.calculateTotalCost();

        assertEquals(150, reservationCost);
    }
}
