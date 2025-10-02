package br.com.lucasAmorim.test;

import br.com.lucasAmorim.app.Main;
import io.javalin.Javalin;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTests {
    private static Javalin app;

    @BeforeAll
    static void start() {
        Main.main(null);
    }

    @Test
    void testHello() throws Exception {
        URL u = new URL("http://localhost:8000/hello");
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("GET");
        assertEquals(200, con.getResponseCode());
    }
}
