package com.example.part2externalapimomondoassignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Gender {
    private int count;
    private String gender;
    private String name;
    private double probability;

}
