package org.example.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonDetails {
    private  long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String city;
    private String birthDate;
    public static PersonDetails person(PersonDetails personDetails){
        if (personDetails==null){
            return new PersonDetails();
        }
        return personDetails;
    }
}
