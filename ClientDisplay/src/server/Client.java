package server;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    final String host;
    final int portNum;
    final int backlog;
    Client(String host, int portNum, int backLog){
        this.host = host;
        this.portNum = portNum;
        this.backlog = backLog;
    }
    public void run(){
        Socket serverConnection = null;
        PrintWriter output = null;
        BufferedReader input = null;
        try {
            serverConnection = new Socket(host, portNum);
            output = new PrintWriter(serverConnection.getOutputStream());
            input = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            boolean keepRunning = true;
            while(keepRunning) {
                //output.println("E|This is a Message");
                //output.flush();
                String clientMessage = scanner.nextLine();
                System.out.println("Client SAID>>>" + clientMessage);
                output.println(clientMessage);
                output.flush();
                if (clientMessage.trim().equals("TERMINATE") || clientMessage.trim().equals("QUIT")){
                    keepRunning = false;
                }
                else {
                    String serverMessage = input.readLine();
                    output.println("Server SAID>>>" + serverMessage);
                }
                //output.println("Hello from the client.");
                //output.flush();

            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                input.close();
                output.close();
                serverConnection.close();
            }
            catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
