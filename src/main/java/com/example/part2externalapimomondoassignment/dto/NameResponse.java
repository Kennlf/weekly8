package com.example.part2externalapimomondoassignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NameResponse {

    private String name;
    private String gender;
    private double genderProbability;
    private int age;
    private int ageCount;
    private String country;
    private double countryProbability;

    public void setGenderProbability(double p){
        genderProbability = p*100;
    }

    public void setCountryProbability(double p){
        countryProbability = p*100;
    }
}
