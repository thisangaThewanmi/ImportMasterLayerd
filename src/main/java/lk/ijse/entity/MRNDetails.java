package lk.ijse.entity;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor

public class MRNDetails {
    private String mrnId;

    private String machineId;

    private String machineName;

    private int qty;

    private String supplierId;
}
