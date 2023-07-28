package org.carrental.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VehicleOwner {
    private Long id;
    private String ownerName;
    private String phone;
    private String cnic;
    private String address;
    private Double commission;
}
