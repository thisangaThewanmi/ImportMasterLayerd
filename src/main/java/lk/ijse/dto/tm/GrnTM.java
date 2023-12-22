package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class  GrnTM {

    private String stockId;
    private String stockName;
    private int qty;
    private double unitPrice;
    private double sellingPrice;
    private  Double total;
    private Button option;

}
