package io.github.josewynder.rentalcompany.model;

import io.github.josewynder.rentalcompany.exceptions.InvalidReservationException;

public class Reservation {

    private Client client;
    private Car car;
    private int days;

    public Reservation(Client client, Car car, int days) {
        if(days <= 0) {
            throw new InvalidReservationException("Days must be greater than zero");
        }
        this.client = client;
        this.car = car;
        this.days = days;
    }

    public double calculateTotalCost() {
        return this.car.calculateRentalValue(this.days);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
