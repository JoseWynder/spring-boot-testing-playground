package io.github.josewynder.rentalcompany.model;

public class Car {
    private String model;
    private Double dailyPrice;

    public Car(String model, Double dailyPrice) {
        this.model = model;
        this.dailyPrice = dailyPrice;
    }

    public Double calculateRentalValue(int rentedDays) {
        double rentalValue = rentedDays * dailyPrice;
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

    public Double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }
}
