package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class CartTM {
    private String productId;
    private String description;
    private int qty;
    private double unitPrice;
    private double total;
    private Button btn;
}
