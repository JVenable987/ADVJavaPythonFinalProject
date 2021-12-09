package server;

public class ClientStarter {
    public static void main(String[] args){
        Client c = new Client("localhost", 10000, 5);
        c.run();
    }
}
