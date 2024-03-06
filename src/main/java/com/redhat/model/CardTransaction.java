package com.redhat.model;

public class CardTransaction {
    private double distanceFromLastTransaction;
    private double ratioToMedianPurchase;
    private boolean chip;
    private boolean pin;
    private boolean online;

    public double getDistanceFromLastTransaction() {
        return distanceFromLastTransaction;
    }

    public void setDistanceFromLastTransaction(double distanceFromLastTransaction) {
        this.distanceFromLastTransaction = distanceFromLastTransaction;
    }

    public double getRatioToMedianPurchase() {
        return ratioToMedianPurchase;
    }

    public void setRatioToMedianPurchase(double ratioToMedianPurchase) {
        this.ratioToMedianPurchase = ratioToMedianPurchase;
    }

    public boolean isChip() {
        return chip;
    }

    public void setChip(boolean chip) {
        this.chip = chip;
    }

    public boolean isPin() {
        return pin;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
