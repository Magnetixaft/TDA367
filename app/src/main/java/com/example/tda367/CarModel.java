package com.example.tda367;

public class CarModel {
    private String carName;
    private String carArea;
    private int carCost;

    public CarModel(String carName, String carArea, int carCost) {
        this.carName = carName;
        this.carArea = carArea;
        this.carCost = carCost;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarArea() {
        return carArea;
    }

    public void setCarArea(String carArea) {
        this.carArea = carArea;
    }

    public int getCarCost() {
        return carCost;
    }

    public void setCarCost(int carCost) {
        this.carCost = carCost;
    }


}
