package io.github.josewynder.rentalcompany.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "daily_price")
    private double dailyPrice;

    @Column(name = "release_year")
    private int releaseYear;

    @Deprecated
    public CarEntity() {}

    public CarEntity(String model, double dailyPrice, int releaseYear) {
        this.model = model;
        this.dailyPrice = dailyPrice;
        this.releaseYear = releaseYear;
    }

    public CarEntity(Long id, String model, double dailyPrice, int releaseYear) {
        this(model, dailyPrice, releaseYear);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
