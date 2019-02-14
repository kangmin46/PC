package com.company;

public class ServerModel {
    private int pcNumber;
    private boolean onOffOK = false;
    private long pcUseStartTime = 0;
    private long pcUseEndTime = 0;
    private boolean IsPayment;
    private int useTime = 0;
    private long timeHour = 0 ;
    private long timeMinute = 0 ;
    private long remaintimeHour = 0 ;
    private long remaintimeMinute = 0;

    public void setRemaintimeHour(long remaintimeHour) {
        this.remaintimeHour = remaintimeHour;
    }

    public void setRemaintimeMinute(long remaintimeMinute) {
        this.remaintimeMinute = remaintimeMinute;
    }

    public long getRemaintimeHour() {
        return remaintimeHour;
    }

    public long getRemaintimeMinute() {
        return remaintimeMinute;
    }

    public void setTimeHour(long timeHour) {
        this.timeHour = timeHour;
    }

    public void setTimeMinute(long timeMinute) {
        this.timeMinute = timeMinute;
    }

    public long getTimeHour() {
        return timeHour;
    }

    public long getTimeMinute() {
        return timeMinute;
    }

    ServerModel(int pcNumber) {
        this.pcNumber = pcNumber;
    }

    public void setPcNumber(int pcNumber) {
        this.pcNumber = pcNumber;
    }

    public void setOnOffOK(boolean onOffOK) {
        this.onOffOK = onOffOK;
    }

    public void setPcUseStartTime(long pcUseStartTime) {
        this.pcUseStartTime = pcUseStartTime;
    }

    public void setPcUseEndTime(long pcUseEndTime) {
        this.pcUseEndTime = pcUseEndTime;
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

    public long getPcUseStartTime() {
        return pcUseStartTime;
    }

    public long getPcUseEndTime() {
        return pcUseEndTime;
    }

    public boolean getIsPayment() {
        return IsPayment;
    }

    public int getUseTime() {
        return useTime;
    }

    void TurnOn() {
        this.onOffOK = true;
    }

    void StartTimeSave() {
        this.pcUseStartTime = System.currentTimeMillis();
    }

    void TurnOff() {
        this.onOffOK = false;
    }

    boolean getonOffOK() { //onOffOK의 Getter
        if (this.onOffOK) return true;
        else return false;
    }

}