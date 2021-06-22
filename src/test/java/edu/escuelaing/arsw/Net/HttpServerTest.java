package edu.escuelaing.arsw.Net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import junit.framework.*;

public class HttpServerTest extends TestCase {
    private static int requests = 0;

    public Runnable makeRequest() {
        return new Runnable() {
            @Override
            public void run() {
                Socket client = null;
                try {
                    client = new Socket("127.0.0.1", 35000);
                    ++requests;
                    client.close();

                } catch (UnknownHostException e) {
                } catch (IOException e) {
                }
            }
        };
    }

    public void testMake20Request() {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0; i <= 20; i++) {
            threadPool.submit(makeRequest());
        }
        threadPool.submit(() -> {
            assertTrue(requests == 20);
        });
    }

    public void testMake30Request() {
        ExecutorService threadPool = Executors.newFixedThreadPool(30);
        for (int i = 0; i <= 30; i++) {
            threadPool.submit(makeRequest());
        }
        threadPool.submit(() -> {
            assertTrue(requests == 30);
        });
    }

    public void testItClose() {
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", 35000);
            ++requests;
            client.close();
            assertTrue(client.isClosed());
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }
}