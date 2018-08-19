package com.cristph.nettylearning.BIO;

import java.io.*;
import java.net.Socket;

public class ServerConnectionHandler extends Thread {

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ServerConnectionHandler(Socket socket) {
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
            try {
                while (reader.ready()) {
                    String msg = reader.readLine();
                    System.out.println("server receive a msg [" + msg + "]");
                    writer.write("server get msg:" + msg + System.lineSeparator());
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
