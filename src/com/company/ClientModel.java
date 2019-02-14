package com.company;

public class ClientModel {
    private int pcNumber;
    private boolean onOffOK = false;
    private long pcUseStartTime = 0;
    private long pcUseEndTime = 0;
    private boolean IsPayment;
    private int useTime = 0;
    private boolean checkOnOff = false;

    public void setCheckOnOff(boolean checkOnOff) {
        this.checkOnOff = checkOnOff;
    }

    public boolean isCheckOnOff() {
        return checkOnOff;
    }

    ClientModel() {

    }

    public void setPcNumber(int pcNumber) {
        this.pcNumber = pcNumber;
    }

    public void setOnOffOK(boolean onOffOK) {
        this.onOffOK = onOffOK;
    }

    public void setIsPayment(boolean IsPayment) {
        this.IsPayment = IsPayment;
    }

    public void setUseTime(int useTime) {
        this.useTime = useTime;
    }

    public int getPcNumber() {
        return pcNumber;

    }

    public boolean getIsPayment() {
        return IsPayment;
    }

    public int getUseTime() {
        return useTime;
    }

    void StartTimeSave() {
        this.pcUseStartTime = System.currentTimeMillis();
    }

    boolean getonOffOK() { //onOffOK의 Getter
        if (this.onOffOK) return true;
        else return false;
    }
}
