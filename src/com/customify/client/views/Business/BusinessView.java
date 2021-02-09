/**
 * @description
 *Main view for operating on businesses
 *
 * @author Kellia Umuhire, Anselme Habumugisha
 * @since Wednesday, 3 February 2021
 * */


package com.customify.client.views.Business;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class BusinessView {
    private Socket socket;

    public BusinessView(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        int choice;
        System.out.println("------------------BUSINESS---------------------");
        System.out.println("\n         00. Return Home");
        System.out.println("         1. Register business");
        System.out.println("         2. View business by id");
        System.out.println("         3. View businesses");
        System.out.println("         4. Delete business");
        choice = scan.nextInt();

        switch (choice){
            case 1:
                BusinessRegisterView businessRegisterView = new BusinessRegisterView(this.socket);
                businessRegisterView.view();
                break;
            case 2:
                BusinessReadView businessReadView = new BusinessReadView(this.socket);
                businessReadView.viewById();
                break;
            case 3:
                BusinessReadView businessReadView0 = new BusinessReadView(this.socket);
                businessReadView0.viewAll();
                break;
            case 4:
                BusinessReadView businessReadView1 = new BusinessReadView(this.socket);
                businessReadView1.deleteBusiness();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
