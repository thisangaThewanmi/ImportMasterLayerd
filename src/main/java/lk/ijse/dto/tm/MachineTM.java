package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MachineTM {

    private String id;
    private String name;
    private int qty;
    private double price;
    private Button action;


    public void setOption(Button button) {
    }
}
