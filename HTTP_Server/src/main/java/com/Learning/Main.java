package com.Learning;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server starting.....");

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                Socket connection=serverSocket.accept();
                System.out.println("Client connected: " + connection.getInetAddress());

                // Handle connection in try-with-resources to auto-close stream and socket
                try (connection;

                     var os = connection.getOutputStream()) {

                    var jsn = """
                            {
                                "id": 1
                            }
                            """;
                    var response = """
                            HTTP/1.1 200 OK
                            Content-Type: application/json
                            Content-Length: %d
                            
                            %s
                            """.formatted(jsn.getBytes(StandardCharsets.UTF_8).length, jsn);
                    os.write(response.getBytes(StandardCharsets.UTF_8));
                }
            }
        }
    }
}
