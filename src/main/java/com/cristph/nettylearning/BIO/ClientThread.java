package com.cristph.nettylearning.BIO;

import java.util.concurrent.TimeUnit;

public class ClientThread extends Thread {

    private Client client;

    public ClientThread(String host, int port) {
        this.client = new Client(host, port);
        System.out.println("start a client [" + Thread.currentThread().getName() + "] at [" + host + ":" + port + "]");
    }

    @Override
    public void run() {
        while (client.isAlive()) {
            String msg = Thread.currentThread().getName() + " send a msg";
            client.send(msg);
            System.out.println("client [" + Thread.currentThread().getName() + "] send a msg [" + msg + "]");
            String msg_received = client.receive();
            System.out.println("client [" + Thread.currentThread().getName() + "] receive a msg from server [" + msg_received + "]");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void interrupt() {
        client.stop();
        super.interrupt();
    }
}
