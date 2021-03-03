package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.Sale.SaleDataFormat;
import com.customify.client.data_format.SalesFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class SalesService {
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;

    public SalesService(Socket socket) {
        this.socket = socket;
    }

    public void getSaleById(String saleId){}

    public void deleteSale(String salesId){}

    public void getAllSales(String json) throws IOException {
        SendToServer sendToServer = new SendToServer(json,this.socket);
        if(sendToServer.send()){
            this.handleAllSalesResponse();
        }
    }

    public void create(SaleDataFormat saleDataFormat)  {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(saleDataFormat);
            System.out.println(jsonData);
        }catch (JsonProcessingException jsonProcessingException){
          jsonProcessingException.printStackTrace();
        }
    }

    private void handleAllSalesResponse() {
        try {
             this.inputStream = this.socket.getInputStream();
             this.objectInputStream = new ObjectInputStream(this.inputStream);

             ObjectMapper objectMapper = new ObjectMapper();

            List<String> res =(List) this.objectInputStream.readObject();


            Iterator itr = res.iterator();

            String leftAlignFormat = "| %-10s | %-10s | %-10s | %-11s | %-11s | %-10s |%n";

            System.out.println("\n");
            System.out.format("+------------+------------+------------+-------------+-------------+------------+%n");
            System.out.format("| SaleId     | customerID |  quantity  |totalPrice   | employeeID  | productID  |%n");
            System.out.format("+------------+------------+------------+-------------+-------------+------------+%n");
            while (itr.hasNext()) {
                JsonNode jsonNode = objectMapper.readTree((String) itr.next());
                System.out.format(leftAlignFormat,jsonNode.get("saleId"),jsonNode.get("customerID").textValue(),jsonNode.get("quantity").textValue(),jsonNode.get("totalPrice").textValue(),jsonNode.get("employeeID").textValue(),jsonNode.get("productID").textValue());
            }
            System.out.format("+------------+------------+------------+-------------+-------------+------------+%n");
            System.out.println("\n");
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }
}
