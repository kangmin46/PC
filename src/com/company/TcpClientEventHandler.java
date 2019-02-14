package com.company;

public interface TcpClientEventHandler{
    public void onMessage(String line);
    public void onOpen();
    public void onClose();
}