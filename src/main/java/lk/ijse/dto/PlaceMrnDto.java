package lk.ijse.dto;

import lk.ijse.dto.tm.MrnTM;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlaceMrnDto {

    private String MRNId;

    private LocalDate MRNdate;

    private String supplierId;

    private String supplierName;

    private Double total;

    private List<MrnTM> machineGrnTMS= new ArrayList<>();


}
