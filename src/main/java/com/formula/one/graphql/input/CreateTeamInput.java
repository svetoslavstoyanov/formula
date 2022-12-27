package com.formula.one.graphql.input;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CreateTeamInput {

    @NotBlank
    @Length(min = 3, max = 20)
    private String name;

    public CreateTeamInput(String name) {
        this.name = name;
    }

    public CreateTeamInput() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
