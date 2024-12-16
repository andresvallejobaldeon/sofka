package com.av.sofka.accounting.dao.model.account;

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
