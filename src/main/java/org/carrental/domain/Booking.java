package org.carrental.domain;

import lombok.*;

import java.util.Date;

@Data                     // Combines @ToString, @EqualsAndHashCode, @Getter, and @Setter annotations together.
@AllArgsConstructor       // @AllArgsConstructor: Generates all-argument constructors
@NoArgsConstructor        // @NoArgsConstructor: Generates no-argument constructors
@ToString                 // Generates a toString() method that includes a string representation of all class fields.
@Builder                  // Provides a builder pattern for constructing instances of the class.
public class Booking
{
    private Integer id;
    private Integer customerId;
    private Integer vehicleId;
    private Date bookingDate;
    private Date completeDate;
    private Double price;
    private String status;
    private String customerName;
    private String vehicleName;
    private Double commission;
}
