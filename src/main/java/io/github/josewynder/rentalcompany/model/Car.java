package io.github.josewynder.rentalcompany.model;

public class Car {
    private String model;
    private Double dailyValue;

    public Car(String model, Double dailyValue) {
        this.model = model;
        this.dailyValue = dailyValue;
    }

    public Double calculateRentalValue(int rentedDays) {
        double rentalValue = rentedDays * dailyValue;
        int discount = 50;
        if(rentedDays >= 5) {
            rentalValue -= discount;
        }
        return rentalValue;
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
