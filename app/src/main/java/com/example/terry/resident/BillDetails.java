package com.example.terry.resident;

public class BillDetails {

    String billID;
    String billDetails;
    String carPlate;
    String billDate;
    String vehicleType;
    Double billAmount;
    public Double billPayment(double payment)
    {
        return payment - billAmount;
    }

    public BillDetails()
    {

    }

    public BillDetails( String billID, String billDetails, String billDate,  Double billAmount, String carPlate,String vehicleType) {
        this.billDetails = billDetails;
        this.billDate = billDate;
        this.billAmount = billAmount;
        this.carPlate = carPlate;
        this.vehicleType = vehicleType;
        this.billID=billID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBillDetails() {
        return billDetails;
    }

    public String getBillDate() {
        return billDate;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public void setBillDetails(String billDetails) {
        this.billDetails = billDetails;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }
}
