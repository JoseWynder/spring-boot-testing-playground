package io.github.josewynder.rentalcompany.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void shouldCreateClientWithName() {
        // 1. Scenario
        Client client = new Client("Maria");

        // Execution
        String name = client.getName();

        // Verification
        assertNotNull(name);

        assertThat(name).isEqualTo("Maria");
        assertThat(name).isLessThan("Maria Francisca");

        assertThat(name).contains("Ma");

        assertFalse(name.isBlank());
        assertThat(name).isNotBlank(); // BETTER
    }

    @Test
    void shouldCreateClientWithoutName() {
        Client client = new Client(null);

        String name = client.getName();

        assertNull(name);
    }
}
