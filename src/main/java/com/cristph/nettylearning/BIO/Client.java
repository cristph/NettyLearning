package com.cristph.nettylearning.BIO;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("client socket start");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        try {
            writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receive() {
        String tmp = "";
        try {
            tmp = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return tmp;
        }
    }

    public void stop() {
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
