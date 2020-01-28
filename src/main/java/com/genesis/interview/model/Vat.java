package com.genesis.interview.model;

public enum Vat {

    SIX_PERCENT(6.0),
    TWELVE_PERCENT(12.0),
    TWENTY_ONE_PERCENT(21.0);

    private double value;

    Vat(double value) {
        this.value = value;
    }

    public double getValue(){
        return value;
    }
}
