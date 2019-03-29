package io.quarkus.extest;

import static org.hamcrest.Matchers.is;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.SubstrateTest;
import io.restassured.RestAssured;

/**
 * Native image tests
 */
@SubstrateTest
public class NativeImageIT {

    /**
     * Test the RuntimeXmlConfigService using old school sockets
     */
    @Test
    public void testRuntimeXmlConfigService() throws Exception {
        // From config.xml
        Socket socket = new Socket("localhost", 12345);
        OutputStream os = socket.getOutputStream();
        os.write("testRuntimeXmlConfigService\n".getBytes());
        os.flush();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String reply = reader.readLine();
            Assertions.assertEquals("testRuntimeXmlConfigService-ack", reply);
        }
        socket.close();
    }

    @Test
    public void verifyCommandServlet() {
        RestAssured.when().get("/commands/ping").then()
                .body(is("/ping-ack"));
    }
}
