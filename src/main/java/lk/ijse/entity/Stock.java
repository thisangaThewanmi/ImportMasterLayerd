package lk.ijse.entity;

import lombok.*;


    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor

    public class Stock {

    private String id;

    private String name;

    private int qty;

    private double price;


}
