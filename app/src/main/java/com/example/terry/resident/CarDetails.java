package com.example.terry.resident;

public class CarDetails {
    String vehicleType;
    String vehicleColor;
    String vehiclePlate;
    String vehicleModel;
    String vehicleSticker;
    String approvalStatus;
    double price=0;
    public CarDetails()
    {

    }

    public CarDetails(String vehicleType, String vehicleColor, String vehiclePlate, String vehicleModel, String vehicleSticker,double price, String approvalStatus) {
        this.vehicleType = vehicleType;
        this.vehicleColor = vehicleColor;
        this.vehiclePlate = vehiclePlate;
        this.vehicleModel = vehicleModel;
        this.vehicleSticker = vehicleSticker;
        this.price = price;
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleSticker() {
        return vehicleSticker;
    }
    public double getPrice() {
        return price;
    }

}
