package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.Data;

@Data
public class SupplierTm {
    private String id;
    private String name;
    private String address;
    private String tel;
    private Button option;
}