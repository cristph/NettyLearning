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
    public void TestServerAndClient() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            testServer();
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.submit(() -> {
            testClient(10);
        });

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }


    private void testServer() {
        ExecutorService server = Executors.newSingleThreadExecutor();
        server.submit(new ServerThread(port));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.shutdownNow();
        }));
    }

    private void testClient(int clientCount) {
        ExecutorService clientPool = Executors.newFixedThreadPool(clientCount);
        for (int i = 0; i < clientCount; i++) {
            clientPool.submit(new ClientThread(host, port));
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            clientPool.shutdownNow();
        }));
    }

}
