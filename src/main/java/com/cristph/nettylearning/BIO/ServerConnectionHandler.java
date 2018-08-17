package com.cristph.nettylearning.BIO;

import java.io.*;
import java.net.Socket;

public class ServerConnectionHandler extends Thread {

    private Socket socket;

    public ServerConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = reader.readLine();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write("server get msg:" + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
