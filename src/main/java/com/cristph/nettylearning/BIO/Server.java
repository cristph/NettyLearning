package com.cristph.nettylearning.BIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ExecutorService executor = Executors.newCachedThreadPool();
    private ServerSocket serverSocket = null;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(port));
            System.out.println("server start at port [" + port + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startService() {
        while (!Thread.currentThread().isInterrupted()) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("server accept a socket at [" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "]");
            executor.submit(new ServerConnectionHandler(socket));
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        executor.shutdownNow();
    }

}
