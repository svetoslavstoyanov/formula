package com.formula.one.enums;

public enum RaceStatus {
    READY("READY"),
    RUNNING("RUNNING"),
    CANCELLED("CANCELLED"),
    FINISHED("FINISHED");

    public final String status;

    private RaceStatus(String status) {
        this.status = status;
    }
}
