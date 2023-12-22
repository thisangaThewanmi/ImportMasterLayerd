package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MachineInstallDto {

  private String  MIid;
  private LocalDate date;

  private   String machineId;

    private String machineName;
    private String customerId;

    private String customerName;
    private String engineerId;

    private String engineerName;
    private Double price;



}
