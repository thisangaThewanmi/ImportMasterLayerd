package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderStockDto {
    private String e_id;

    private String e_name;

    private String esinId;

    private String stockId;

    private String stockName;

    private int qty;

    private double unitPrice;
}
