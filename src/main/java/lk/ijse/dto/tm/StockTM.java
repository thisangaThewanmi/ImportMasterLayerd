package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockTM {

    private String id;
    private String name;
    private int qty;
    private double price;
    private double total;
    private Button option;
}
