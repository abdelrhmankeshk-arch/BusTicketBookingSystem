package client;

import java.io.*;
import java.net.Socket;

public class ClientNetwork {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 12345;

    public static String sendRequest(String request) {
        try (Socket socket = new Socket(SERVER_IP, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(request);
            String response = in.readLine();
            return (response != null) ? response : "ERROR No response";
        } catch (IOException e) {
            return "ERROR Connection failed. Is the Server running?";
        }
    }
}