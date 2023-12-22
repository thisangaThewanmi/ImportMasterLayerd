package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@ToString
@Setter
@AllArgsConstructor
@Getter

public class MrnTM {



        private String machineId;
        private String machineName;
        private int qty;
        private double unitPrice;
        private double sellingPrice;
        private  Double total;
        private Button option;

    }


