package Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by orrko_000 on 03/06/2017.
 */

public class Server {
    private ServerSocket server;
    private ExecutorService executor;
    Server() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            InetAddress ip=InetAddress.getLocalHost();
            ip.getHostAddress();

            Socket clientSocket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            while (clientSocket.isConnected())
                if (in.available()>0)
                    System.out.print(in.readInt());

        } catch (Exception e) {
            e.printStackTrace();
        }

        {
        }
    }

    public void startServer(int maxClientsNum) {
        executor = Executors.newFixedThreadPool(maxClientsNum);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true){
                    try {
                        System.out.print(".");
                        Socket socket = server.accept();
                        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        executor.submit(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    check();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                        });
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }
    public synchronized void check() throws  Exception {
        Thread thread = new Thread(new Runnable() {
            public void run()
            {
                while(true)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        });thread.start();



    }
}

