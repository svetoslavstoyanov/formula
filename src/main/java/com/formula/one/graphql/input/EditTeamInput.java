package com.formula.one.graphql.input;

import java.util.UUID;

public class EditTeamInput extends CreateTeamInput{

    private UUID id;

    public EditTeamInput(String name, UUID id) {
        super(name);
        this.id = id;
    }

    public EditTeamInput(UUID id) {
        this.id = id;
    }

    public EditTeamInput() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
