package com.customify.client;

import com.customify.shared.Request;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Common {

    private OutputStream output = null;
    private ObjectOutputStream objectOutput = null;
    private boolean isConnectionOn = true;
    private Request request;
    private String serverIP;

    private Socket socket;


    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }


    public Common(Request request,Socket socket) throws IOException {
        this.socket = socket;
        this.request = request;
        this.serverIP = "";
        this.sendToServer();
    }

    public void sendToServer() throws IOException {
        try {
            List<Request> dataToSend = new ArrayList<>();
            dataToSend.add(request);
            this.output = socket.getOutputStream();
            this.objectOutput = new ObjectOutputStream(output);
            this.objectOutput.writeObject(dataToSend);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
