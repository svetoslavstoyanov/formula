package com.formula.one.graphql.input;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class CreateDriverInput {

    @NotBlank
    @Length(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Length(min = 3, max = 20)
    private String lastName;

    @Range(min = 18, max = 100)
    private Integer age;

    @Range(min = 1, max = 99)
    private Integer carNumber;

    public CreateDriverInput(String firstName, String lastname, Integer age, Integer carNumber) {
        this.firstName = firstName;
        this.lastName = lastname;
        this.age = age;
        this.carNumber = carNumber;
    }

    public CreateDriverInput() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }
}
