package com.customify.client.dashboards;

import com.customify.client.Colors;
import com.customify.client.utils.authorization.UserSession;
import com.customify.client.views.Business.BusinessView;

import java.net.Socket;
import java.util.Scanner;

public class SuperAdminDashboard {
    private Socket socket;
    private boolean loggedIn;
    private UserSession userSession;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public SuperAdminDashboard(Socket socket) throws Exception{
        this.socket = socket;
        this.userSession= new UserSession();
        if(userSession.isLoggedIn()) {
            this.setLoggedIn(true);
            this.view();
        }else
            System.out.println("\t\t\tSORRY YOU CAN'T ACCESS THIS ROUTE _ LOG IN FIRST");
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void view()throws Exception{
        Scanner scan = new Scanner(System.in);

        if(isLoggedIn()){
            do {
                System.out.flush();

                System.out.println(Colors.ANSI_PURPLE);
                System.out.println("---------------------------------------------");
                System.out.println("--------------CUSTOMIFY HOME-----------------\n\n");
                System.out.println(Colors.ANSI_RESET);

                System.out.println("           1. BUSINESS MANAGEMENT");
                System.out.println("           2. MY PROFILE");
                System.out.println("           3. PROFILE SETTINGS");
                System.out.println("           4. LOGOUT !!!");

                System.out.print("\n Chose between [ 1 -  4 ] ");
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        BusinessView business = new  BusinessView(this.socket);
                        business.view();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        if (userSession.unSet())
                            loggedIn = false;
                        break;
                    default:
                        System.out.println("INVALID CHOICE");
                }
            }while(loggedIn);
        }



    }

}
