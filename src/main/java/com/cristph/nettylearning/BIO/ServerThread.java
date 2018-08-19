package com.cristph.nettylearning.BIO;

public class ServerThread extends Thread {

    private Server server;

    public ServerThread(int port) {
        server = new Server(port);
    }

    @Override
    public void run() {
        server.startService();
        super.run();
    }

    @Override
    public void interrupt() {
        server.stop();
        super.interrupt();
    }
}
