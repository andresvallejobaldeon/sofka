package com.av.sofka.accounts.model;

public enum MovementType {

    DEBIT("D", "Debito"),
    CREDIT("C", "Credito");

    private String value;
    private String description;

    MovementType(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
