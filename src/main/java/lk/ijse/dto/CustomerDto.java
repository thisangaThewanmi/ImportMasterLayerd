package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDto {

    private String id;

    private String name;

    private String address;

    private String tel;
}
