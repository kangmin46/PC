package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientController {

    private TcpClient sock = new TcpClient("localhost", 5000);
    ClientModel PC = new ClientModel();

    ClientController() {
        sock.connect();
        sock.addEventHandler(new TcpClientEventHandler(){
            public void onMessage(String line){
                String message = line;
                String[] tokens = message.split(":");
                if (tokens[0].equals("ALLCHECK")){

                    System.out.println(tokens[1]);

                }
                else if(tokens[0].equals("CHECKPC")){

                     PC.setCheckOnOff(Boolean.valueOf(tokens[1]));
                }

            }
            public void onOpen(){
                System.out.println("* socket connected");
            }
            public void onClose(){
                System.out.println("* socket closed");
            }
        });

    }

    void AllCheck() {

       sock.send("ALLCHECK:");

        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){

        }

    }


    public boolean IsPowerOn() {

        return PC.getonOffOK();
    }

    void TurnOn(int pcNumber){

        PC.setPcNumber(pcNumber);
        PC.setOnOffOK(true);
        String Data = DataEncapsulation();
        sock.send(Data);

    }


    boolean CheckPc(int pcNumber){

        sock.send("CHECKPC:"+Integer.toString(pcNumber));
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e ){
            System.out.println("스레드 익셉션");
        }

        return PC.isCheckOnOff();

    }


    String DataEncapsulation(){

        String Data="";
            if (PC.getIsPayment()) {
                Data = "CONNECT" + ":"+ PC.getPcNumber()+":"+"선불:"+PC.getUseTime()+":";
            } else {
                Data = "CONNECT" + ":"+PC.getPcNumber()+":후불:"+"NoData:";
            }

        return Data;
    }

    void PrePayment(int pcNumber, int time) {

        PC.StartTimeSave();
        PC.setIsPayment(true);
        PC.setUseTime(time);
        this.TurnOn(pcNumber);

    }

    void DeferredPayment(int pcNumber) {   // 후불

        PC.StartTimeSave();
        PC.setIsPayment(false);
        this.TurnOn(pcNumber);

    }


    void TurnOff() { //PC 종료

        String message = "DISCONNECT:"+PC.getPcNumber();
        PC.setOnOffOK(false);
        sock.send(message);
        sock.close();

    }

    void PcMove(int movingSeat) { //Pc 자리이동

        String message = "SEATMOVEMENT:"+PC.getPcNumber()+":"+movingSeat;
        PC.setPcNumber(movingSeat);
        sock.send(message);

    }

}
