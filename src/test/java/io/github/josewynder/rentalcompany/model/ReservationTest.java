package io.github.josewynder.rentalcompany.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import io.github.josewynder.rentalcompany.model.exceptions.InvalidReservationException;
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
        assertThat(reservation)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void shouldThrowExceptionWhenCreatingReservationWithInvalidDays() {
        int days = 0;

        // JUnit
        assertThrows(InvalidReservationException.class,
                () -> new Reservation(client, car, days));

        assertDoesNotThrow(() -> new Reservation(client, car, 1));

        // AssertJ
        var error = catchThrowable(() ->
                new Reservation(client, car, days));

        assertThat(error).
                isInstanceOf(InvalidReservationException.class)
                .hasMessage("Days must be greater than zero");

    }

    @Test
    void shouldCalculateReservationAmount() {
        int days = 3;

        Reservation reservation = new Reservation(client, car, days);

        double reservationCost = reservation.calculateTotalCost();

        assertEquals(150, reservationCost);
    }
}
