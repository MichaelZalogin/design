package ru.job4j.io;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String line = in.readLine();
                    if (line != null && !line.isEmpty() && line.matches(".+msg=Hello.+")) {
                        out.write(("HTTP/1.1 200 OK \r\n\r\n").getBytes());
                        out.write(("Hello").getBytes());
                    }

                    if (line != null && !line.isEmpty() && line.matches(".+msg=Exit.+")) {
                        server.close();
                    }
                    if (line != null && !line.isEmpty()
                            && !line.matches(".+msg=Hello.+") && !line.matches(".+msg=Exit.+")) {
                        out.write(("HTTP/1.1 200 OK \r\n\r\n").getBytes());
                        out.write(("What").getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}