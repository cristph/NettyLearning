package com.cristph.nettylearning.BIO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestBIOModel {

    private static String host;
    private static int port;

    @BeforeAll
    public static void setEnv() {
        host = "127.0.0.1";
        port = 8080;
    }

    @Test
    public void testBIOModel() {
        Server server = new Server(port);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executor.submit(new ClientThread(new Client(host, port)));
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executor.shutdownNow();
        }));
        try {
            TimeUnit.MINUTES.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
