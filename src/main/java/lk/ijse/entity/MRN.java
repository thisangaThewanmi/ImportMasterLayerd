package lk.ijse.entity;

import lombok.Getter;

import java.time.LocalDate;

public class MRN {

    private String mrnId;

    private LocalDate date;

    private String supplierId;

    private double total;


    public MRN(String mrnId, LocalDate date, String supplierId, double total) {
        this.mrnId = mrnId;
        this.date = date;
        this.supplierId = supplierId;
        this.total = total;
    }

    public MRN() {

    }

    public String getMrnId() {
        return mrnId;
    }

    public void setMrnId(String mrnId) {
        this.mrnId = mrnId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "MRN{" +
                "mrnId='" + mrnId + '\'' +
                ", date=" + date +
                ", supplierId='" + supplierId + '\'' +
                ", total=" + total +
                '}';
    }
}
