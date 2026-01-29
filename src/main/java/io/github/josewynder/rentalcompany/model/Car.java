package io.github.josewynder.rentalcompany.model;

public class Car {
    private String model;
    private Double dailyValue;

    public Car(String model, Double dailyValue) {
        this.model = model;
        this.dailyValue = dailyValue;
    }

    public Double calculateRentalValue(int rentedDays) {
        return rentedDays * dailyValue;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(Double dailyValue) {
        this.dailyValue = dailyValue;
    }
}
