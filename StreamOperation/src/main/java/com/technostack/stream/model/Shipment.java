package com.technostack.stream.model;

import java.time.LocalDate;

public class Shipment {

    private String carrier; // DHL, BLUEDART
    private String zone;    // NORTH, SOUTH, EAST, WEST
    private LocalDate deliveredDate;
    private String status;  // DELIVERED, DELAYED

    public Shipment(String carrier, String zone, LocalDate deliveredDate, String status) {
        this.carrier = carrier;
        this.zone = zone;
        this.deliveredDate = deliveredDate;
        this.status = status;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public LocalDate getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "carrier='" + carrier + '\'' +
                ", zone='" + zone + '\'' +
                ", deliveredDate=" + deliveredDate +
                ", status='" + status + '\'' +
                '}';
    }
}
