package org.carrental.domain;

import lombok.*;

@Data                     // Combines @ToString, @EqualsAndHashCode, @Getter, and @Setter annotations together.
@AllArgsConstructor       // @AllArgsConstructor: Generates all-argument constructors
@NoArgsConstructor        // @NoArgsConstructor: Generates no-argument constructors
@ToString                 // Generates a toString() method that includes a string representation of all class fields.
@Builder                  // Provides a builder pattern for constructing instances of the class.
public class Vehicle
{
    private Long id;
    private String vehicleName;
    private Integer model;
    private String brand;
    private String color;
    private Long vehicleOwnerId;
    private String ownerName;
}
