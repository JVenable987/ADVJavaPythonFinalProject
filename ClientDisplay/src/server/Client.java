package server;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.Socket;

public class Client {
    final String host;
    final int portNum;
    final int backlog;

    private boolean isConnected;
    private Socket serverConnection;
    private DataOutputStream output;
    public DataInputStream input;

    public Client(String host, int portNum, int backLog){
        this.host = host;
        this.portNum = portNum;
        this.backlog = backLog;
    }

    public String sendRequest(String request) throws IOException {
        this.output.writeUTF(request);
        this.output.flush();
        //this.output.close();

        displayMsg("CLIENT >> " + request);
        //String srvResponse = this.input.readLine();
        String srvResponse = this.input.readUTF();
        //System.out.println(srvResponse);
        displayMsg("SERVER >> " + srvResponse);
        return srvResponse;
    }

    public ArrayList<String> sendRequestSpecial(String request) throws IOException {
        ArrayList<String> returnList = new ArrayList<>();
        this.output.writeUTF(request);
        this.output.flush();
        //this.output.close();
        String srvResponse2;

        displayMsg("CLIENT >> " + request);
        //String srvResponse = this.input.readLine();
        String srvResponse = this.input.readUTF();

        //System.out.println(srvResponse);
        displayMsg("SERVER >> " + srvResponse);
        int size = Integer.parseInt(srvResponse);
        for(int i = 0; i < size; i++){
            srvResponse2 = this.input.readUTF();
            displayMsg("SERVER >> " + srvResponse2);
            returnList.add(srvResponse2);
        }
        return returnList;
    }

    private void displayMsg(String message) {
        System.out.println(message);
    }

    private DataOutputStream getOutputStream() throws IOException {
        return new DataOutputStream(this.serverConnection.getOutputStream());
    }
    private DataInputStream getInputStream() throws IOException {
        return new DataInputStream(this.serverConnection.getInputStream());
    }
    public void getServerInitialResponse() throws IOException {
        String srvResponse = this.input.readUTF();
        displayMsg("SERVER >> "+ srvResponse);
    }

    public void connect() {
        displayMsg("Attempting connection to Server");
        try {
            this.serverConnection = new Socket(this.host, this.portNum);
            this.isConnected = true;
            this.output = this.getOutputStream();
            this.input = this.getInputStream();
            getServerInitialResponse();
            //sendMessage();

        } catch (IOException e) {
            this.input = null;
            this.output = null;
            this.serverConnection = null;
            this.isConnected = false;
        }
    }

    public void disconnect() {
        displayMsg("\n>> Terminating Client Connection to Server");
        try {
            this.input.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        try {
            this.output.close();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        try {
            this.serverConnection.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        Socket serverConnection = null;
        //DataOutputStream output = null;
        //DataInputStream input = null;
            connect();
//            serverConnection = new Socket(host, portNum);
//            output = new DataOutputStream(serverConnection.getOutputStream());
//            input = new DataInputStream(new DataInputStream(serverConnection.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            boolean isConnected = true;
            while(isConnected) {
                //output.println("E|This is a Message");
                //output.flush();
                String clientMessage = scanner.nextLine();
                System.out.println("Client SAID>>>" + clientMessage);
                //output.println(clientMessage);
                //output.flush();
                //sendRequest(clientMessage);
//                if (clientMessage.trim().equals("TERMINATE") || clientMessage.trim().equals("QUIT")){
//                    keepRunning = false;
//                }
//                else {
//                    String serverMessage = input.readLine();
//                    output.println("Server SAID>>>" + serverMessage);
//                }
                //output.println("Hello from the client.");
                //output.flush();
                if (clientMessage.equals("T|")) {
                    try {
                        sendRequest(clientMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    disconnect();
                    isConnected = false;
                    return;
                }

                if(clientMessage.equals("4|")) {
                    try {
                        sendRequestSpecial(clientMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    System.out.println(sendRequest(clientMessage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}   // end Client.java
