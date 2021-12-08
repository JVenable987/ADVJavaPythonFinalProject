import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server implements Runnable{

    private final int port;
    private final int backlog;
    private int connectionCounter;
    private ServerSocket serverSocket;
    private Morse m1;
    private boolean keepClientRunning;
    private ArrayList<ClientWorker> cwList;
    private boolean available;
    private ExecutorService executorService;

    private ArrayList<Customer> customers;
    private Catalog catalog;
    private ArrayList<Order> orders;
    private ArrayList<ShoppingCart> carts;

    public Server(int port, int backlog) {
        this.port = port;
        this.backlog = backlog;
        this.connectionCounter = 0;
        this.m1 = new Morse();
        this.available = true;
        this.keepClientRunning = false;
        this.executorService = Executors.newCachedThreadPool();
        this.cwList = new ArrayList<>();
    }

    public int getPort() {
        return port;
    }

    public int getBacklog() {
        return backlog;
    }

    public Server() {
        this(10000, 10);
    }
    public void shutdownServer(){
        this.available = false;
        for(ClientWorker cw: cwList) {
            cw.shutdownClient();
        }
        try{
            serverSocket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        executorService.shutdownNow();
    }

    private Socket waitForClientConnection() throws IOException {
        System.out.println("Waiting for a connection....");
        Socket clientConnection = this.serverSocket.accept();
        this.connectionCounter++;
        System.out.printf("Connection #%d accepted from %s%n",
                this.connectionCounter, clientConnection.getInetAddress().getHostName());
        keepClientRunning = true;
        return clientConnection;

    }

    private PrintWriter getOutputStream(Socket clientConnection) throws IOException {
        return new PrintWriter(clientConnection.getOutputStream(), true);
    }

    private BufferedReader getInputStream(Socket clientConnection) throws IOException {
        return new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
    }

    private void sendMessage(PrintWriter output, String message) {
        output.println(message);
    }

    private String processClientRequest(BufferedReader input, Socket clientConnection, PrintWriter output1) throws IOException {

        //TODO this needs to be changed
        String clientMessage = input.readLine();
        if (clientMessage == null) {
            closeClientConnection(clientConnection, input, output1);
            this.keepClientRunning = false;
            return "";
        }

        String output = "";
        String customerName, productName, quantity;
        Item product;
        if (keepClientRunning) {
            try {
                System.out.println("CLIENT SAID>>>" + clientMessage);

                String[] args = clientMessage.split("|");
                switch(args[0]) {
                    case 0:
                        for(Customer tempCustomer: this.customers){
                            if(tempCustomer.getUserName == args[1]) {
                                Item product = new Item(args[2], args[3])
                                tempCustomer.shoppingCart.cartList.add(prodcut);
                            }
                        }
                }
                /*
                if (clientMessage.charAt(0) == 'E' && clientMessage.charAt(1) == '|') {
                    output = this.m1.encode(clientMessage.substring(2));
                } else if (clientMessage.charAt(0) == 'D' && clientMessage.charAt(1) == '|') {
                    output = this.m1.decode(clientMessage.substring(2));
                } else {
                    return "1|Not Implemented";
                }
                if (output == "")
                    return "2|Invalid Message Format";
                return "0|" + output;
                */
                 */
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
        return "";
    }

    private void closeClientConnection(Socket clientConnection, BufferedReader input, PrintWriter output) {
        try {
            input.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            clientConnection.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void shutdown() {
        this.available = false;
        for (ClientWorker cw : cwList)
            cw.close();

        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {



        try {

            this.serverSocket = new ServerSocket(this.port, this.backlog);

            while (this.available) {
                try {

                    Socket clientConnection = this.waitForClientConnection();

                    ClientWorker cw = new ClientWorker(this, clientConnection, this.m1, this.connectionCounter);
                    this.cwList.add(cw);
                    executorService.execute(cw);

                } catch (IOException ioe) {
                    System.out.println("\n-------------------\nServer Terminated");
                    ioe.printStackTrace();
                    executorService.shutdownNow();
                }
            }


        } catch (IOException ioe) {
            System.out.println("\n++++++ Cannot open the Server+++++\n");
            executorService.shutdown();
            ioe.printStackTrace();
        }
    }


    private purchaseCartItems (ShoppingCart cart){
        //TODO
        for(Item i : cart.cartList) {
            for (Item j : this.catalog.getItems) {
                if(i.getName() == j.getName() && i.getQuantity() =< j.getQuantity()) {
                    j.setQuantity(j.getQuantity() - i.getQuantity())
                } else{
                    //invalid cart option there isn't that much inventory space
                }
            }
        }
    }
}