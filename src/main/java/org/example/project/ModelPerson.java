package org.example.project;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class ModelPerson {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String city;
    private String birthDate;
}
