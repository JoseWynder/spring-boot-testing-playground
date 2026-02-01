package io.github.josewynder.rentalcompany.controller;

import com.jayway.jsonpath.JsonPath;
import io.github.josewynder.rentalcompany.entity.CarEntity;
import io.github.josewynder.rentalcompany.model.exceptions.EntityNotFoundException;
import io.github.josewynder.rentalcompany.service.CarService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

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

    @Test
    void shouldGetById() throws Exception {
        CarEntity car = new CarEntity(
                2L, "Fiat Uno", 200, 2028);

        when(carService.findById(car.getId())).thenReturn(car);

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/cars/" + car.getId())
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(car.getId()))
                .andExpect(jsonPath("$.model").value(car.getModel()))
                .andExpect(jsonPath("$.dailyPrice").value(car.getDailyPrice()))
                .andExpect(jsonPath("$.releaseYear").value(car.getReleaseYear()));
    }

    @Test
    void shouldThrowExceptionWhenGetNonexistingCar() throws Exception {
        CarEntity car = new CarEntity(
                2L, "Fiat Uno", 200, 2028);

        when(carService.findById(car.getId()))
                .thenThrow(EntityNotFoundException.class);

        mvc.perform(
                MockMvcRequestBuilders.get("/cars/" + car.getId())
        ).andExpect(status().isNotFound());
    }

    @Test
    @Disabled
    void shouldGetAll() throws Exception {
        CarEntity car1 = new CarEntity(
                1L,
                "Argo",
                100.0,
                2025);

        CarEntity car2 = new CarEntity(
                2L,
                "Gol",
                80.0,
                2026);

        when(carService.findAll())
                .thenReturn(Arrays.asList(car1, car2));

        mvc.perform(
                MockMvcRequestBuilders.get("/cars")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].model").value("Argo"))
                .andExpect(jsonPath("$[1].model").value("Gol"))
                .andExpect(jsonPath("$.size()").value(2));

    }
}
