package org.carrental.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User
{
    private Integer id;
    private String username;
    private String password;
}
