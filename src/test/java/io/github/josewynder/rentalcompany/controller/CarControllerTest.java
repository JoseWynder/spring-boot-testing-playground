package io.github.josewynder.rentalcompany.controller;

import io.github.josewynder.rentalcompany.entity.CarEntity;
import io.github.josewynder.rentalcompany.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    CarService carService;

    @Test
    void shouldSave() throws Exception {
        // Scenario
        CarEntity car = new CarEntity(
                1L, "Honda Civic", 150, 2027);

        when(carService.save(Mockito.any(CarEntity.class)))
                .thenReturn(car);

        String json = """
                {
                    "model": "Honda Civic",
                    "dailyPrice": 150,
                    "releaseYear": 2027
                }
                """;

        // Execution
        ResultActions perform = mvc.perform(
                MockMvcRequestBuilders
                        .post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        //Verification
        perform.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.model").value("Honda Civic"))
                .andExpect(jsonPath("$.dailyPrice").value(150))
                .andExpect(jsonPath("$.releaseYear").value(2027));

    }
}
