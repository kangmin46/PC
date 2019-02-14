package com.company;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class ServerController {
    List<ServerModel> pcList = new ArrayList<>();
    public static final int sec_per_minute = 60;
    public static final int min_per_hour = 60;
    TcpServer server = new TcpServer(5000);
    final TcpServer that_server = server;
    static boolean isSeatMoveMent = false;
    ServerController(){

        for (int i = 0; i < 32; i++) {
            pcList.add(new ServerModel(i+1)); //Model 생성
        }

        server.addEventHandler(new TcpServerEventHandler(){ //서버 이벤트 핸들러
            public void onMessage(int client_id, String line){
                String message = line;
                String[] tokens = message.split(":");
                if(tokens[0].equals("CONNECT")){
                    ServerView.buttonList.get(Integer.valueOf(tokens[1])-1).setText("사용중");
                    System.out.println("!! CONNECT !!");
                    if(tokens[2].equals("선불")) PrePayment(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[3]));

                    else{
                        DeferredPayment(Integer.parseInt(tokens[1]));
                    }
                }
                else if(tokens[0].equals("ALLCHECK")){

                    System.out.println("!! ALLCHECK !!");
                    AllCheck(client_id);

                }
                else if(tokens[0].equals("SEATMOVEMENT")){
                    System.out.println("!! SEATMOVEMENT!!");
                    PcMove(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                }
                else if(tokens[0].equals("DISCONNECT")){
                    System.out.println("!! DISCONNECT !!");
                    TurnOff(Integer.parseInt(tokens[1]));
                }
                else if(tokens[0].equals("CHECKPC")){
                    System.out.println("!! CHECK PC !!");
                    int pcNumber = Integer.parseInt(tokens[1]);
                    ServerModel pc = pcList.get(pcNumber-1);
                    if(pc.getonOffOK()){
                        that_server.getClient(client_id).send("CHECKPC:true");
                    }
                    else{
                        that_server.getClient(client_id).send("CHECKPC:false");
                    }
                }

            }
            public void onAccept(int client_id){
                System.out.println("* <"+client_id+"> connection accepted");
            }
            public void onClose(int client_id){
                System.out.println("* <"+client_id+"> closed");
            }
        });
        server.listen();

    }

    void AllCheck(int client_id) {
        for (int i = 1; i < 33; i++) {
            ServerModel pc = pcList.get(i-1);
            System.out.println(pc.getonOffOK());
            String message = "ALLCHECK:";
            if (pc.getonOffOK()) {

                message =message + DataEncapsulation(pc.getPcNumber());
                that_server.getClient(client_id).send(message);
            }
            else{
                message = message + pc.getPcNumber()+"번 PC는 꺼져있습니다.";
                that_server.getClient(client_id).send(message);
            }
        }
    }

    public boolean IsPowerOn(int pcNumber) {

        ServerModel pc = pcList.get(pcNumber-1);
        return pc.getonOffOK();

    }

    String DataEncapsulation(int pcNumber){
        String Data="";
        ServerModel pc = pcList.get(pcNumber - 1);
        SetTime(pcNumber);
        String pcData = String.valueOf(pc.getPcNumber());
        String timeData = String.valueOf(pc.getTimeHour()) + "시간" + String.valueOf(pc.getTimeMinute()) + "분";
            if (pc.getIsPayment()) {
                String reamintimeData = String.valueOf(pc.getRemaintimeHour()) + "시간"
                        + String.valueOf(pc.getRemaintimeMinute()) + "분";
                Data = pcData + "번 PC는 선불로 이용중입니다. 넣은시간 [" +
                        pc.getUseTime() +"시간] 사용시간 ["+ timeData + "] 남은시간 [" + reamintimeData+"]";
            } else {

                Data = pcData + "번 PC는 후불로 이용중입니다. 경과시간 [" + timeData + "]";

            }

        return Data;
    }

    void SetTime(int pcNumber){

        ServerModel pc = pcList.get(pcNumber - 1);
        pc.setPcUseEndTime(((System.currentTimeMillis() - pc.getPcUseStartTime()) / 1000));
        pc.setTimeHour((pc.getPcUseEndTime() / min_per_hour) / sec_per_minute);
        pc.setTimeMinute((pc.getPcUseEndTime() / sec_per_minute) % min_per_hour);

        if(pc.getIsPayment()) {
            long reamainTime = ((pc.getUseTime()) * 3600) - ((pc.getPcUseEndTime()));
            pc.setRemaintimeHour(((reamainTime/min_per_hour)/sec_per_minute));
            pc.setRemaintimeMinute(((reamainTime/min_per_hour)%sec_per_minute));
        }

    }

    void SetTextJtable(int pcNumber){

        ServerModel pc = pcList.get(pcNumber - 1);
        if(pc.getonOffOK()) {
            SetTime(pcNumber);
            String timeData = String.valueOf(pc.getTimeHour()) + "시간" + String.valueOf(pc.getTimeMinute()) + "분";
            if(pc.getIsPayment()){
                String reamintimeData = String.valueOf(pc.getRemaintimeHour()) + "시간"
                        + String.valueOf(pc.getRemaintimeMinute()) + "분";
                ServerView.tableModel.setValueAt(pc.getUseTime()+"시간", 0, 1);
                ServerView.tableModel.setValueAt(reamintimeData, 2, 1);
            }
            ServerView.tableModel.setValueAt(timeData, 1, 1);
        }
        ServerView.rowtableModel.setValueAt(pcNumber, 1, 1);
    }

    void PrePayment(int pcNumber, int time) {

        ServerModel pc = pcList.get(pcNumber - 1);
        pc.setIsPayment(true);
        pc.setUseTime(time);
        this.TunrOn(pcNumber);

    }

    void DeferredPayment(int pcNumber) {   // 후불

        ServerModel pc = pcList.get(pcNumber - 1);
        pc.StartTimeSave();
        pc.setIsPayment(false);
        this.TunrOn(pcNumber);

    }

    void TunrOn(int pcNumber){

        ServerModel pc = pcList.get(pcNumber - 1);
        ServerView.buttonList.get(pcNumber-1).setText("사용중");
        if(!isSeatMoveMent){
            pc.StartTimeSave();
        }
        else{
            isSeatMoveMent = false;
        }
        pc.TurnOn();

    }


    void TurnOff(int pcNumber) { //PC 종료

        ServerModel pc = pcList.get(pcNumber - 1);
        pc.TurnOff();
        System.out.println("여까지왔다.");
        ServerView.buttonList.get(pcNumber-1).setText("PC"+pcNumber);

    }


    void PcMove(int presentSeat, int movingSeat) { //Pc 자리이동
        ServerModel pc1, pc2;
        pc1 = pcList.get(presentSeat - 1);
        pc2 = pcList.get(movingSeat - 1);
        if (pc1.getIsPayment()) {
            pc2.setUseTime(pc1.getUseTime());
            pc2.setIsPayment(true);
        } else {
            pc2.setIsPayment(false);
        }
        isSeatMoveMent = true;
        this.TunrOn(movingSeat);
        pc2.setPcUseStartTime(pc1.getPcUseStartTime());
        this.TurnOff(presentSeat);
    }
    void ServerClose(){

        that_server.close();

    }
    List PcReturn() {
        return pcList;
    }


}
