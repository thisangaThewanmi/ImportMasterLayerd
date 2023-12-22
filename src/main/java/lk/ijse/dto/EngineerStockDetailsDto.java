package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineerStockDetailsDto {
    private String esinId;
    private String stockId;
    private int qty;
    private double total;
}
