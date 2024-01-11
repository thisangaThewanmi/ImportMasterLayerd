package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineerStockDetails {
    private String esinId;
    private String stockId;
    private int qty;
    private double total;
}