package com.company;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.swing.*;

public class TcpServer extends JFrame{
    private ServerSocket server;
    private Socket socket;
    private TcpServerEventHandler handler;
    private ArrayList<TcpClient> clients;
    public ArrayList<TcpClient> getClients(){ return clients; }
    private boolean closer = false;
    private int readInterval;
    private JButton button1;
    private BufferedWriter bWriter;
    private BufferedReader bReader;
    private InputStreamReader iReader;
    public int getReadInterval(){ return readInterval; }
    public void setReadInterval(int msec){
        this.readInterval = msec;
        for(TcpClient sock : clients){
            sock.setReadInterval(msec);
        }
    }

    public TcpServer(int port){
        if(server != null && !server.isClosed()) return;
        if(clients == null) clients = new ArrayList<TcpClient>();
        try{
            server = new ServerSocket(port);
        }
        catch(Exception ex){
        }
    }

    public void listen(){
        new Thread(){
            public void run(){
                while(!closer){
                    try{
                        TcpClient sock = new TcpClient(server.accept());
                        clients.add(sock);
                        final int cid = clients.size()-1; // client id
                        handler.onAccept(cid);
                        if(handler != null){
                            sock.addEventHandler(new TcpClientEventHandler(){
                                public void onMessage(String line){
                                    handler.onMessage(cid, line);
                                }
                                public void onOpen(){
                                }
                                public void onClose(){
                                    handler.onClose(cid);
                                }
                            });
                        }
                        sock.run();
                    }
                    catch(Exception ex){
                    }
                }
            }
        }.start();

        new Thread(){
            public void run(){
                while(true){
                    try{
                        Thread.sleep(100);
                    }
                    catch(Exception ex){
                    }
                    for(TcpClient sock : clients){
                        sock.send("");
                    }
                }
            }
        }.start();
    }

    public TcpClient getClient(int id){
        return clients.get(id);
    }

    public void close(){
        closer = true;
        try{
            server.close();
            for(TcpClient sock : clients){
                sock.close();
            }
        }
        catch(Exception ex){
        }
    }

    public void addEventHandler(TcpServerEventHandler handler){
        this.handler = handler;
    }
}


