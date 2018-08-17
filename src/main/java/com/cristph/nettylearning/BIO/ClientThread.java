package com.cristph.nettylearning.BIO;

import java.util.concurrent.TimeUnit;

public class ClientThread extends Thread {

    private Client client;

    public ClientThread(Client client) {
        this.client = client;
        System.out.println("start a client");
    }

    @Override
    public void run() {
        while (true) {
            client.send(Thread.currentThread().getName() + " send a msg");
            System.out.println(Thread.currentThread().getName() + " send a msg");
            System.out.println("receive" + client.receive());
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
