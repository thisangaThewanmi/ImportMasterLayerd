package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EngineerTeamStockDto {
    private  String e_id;
    private  String  ESIN_NO;
    private String stockId;

    private String stock_name;
    private int qty;

    private double total;


}
