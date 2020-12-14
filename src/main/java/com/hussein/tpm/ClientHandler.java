package com.hussein.tpm;

import java.io.*;
import java.net.Socket;

/**
 * <p>Title: ClientHandler</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/8 11:45 AM
 */
public class ClientHandler implements Runnable {

    private Socket socket;

    private String id;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.id = socket.getInetAddress() + ":" + socket.getPort();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String received;
            while ((received = reader.readLine()) != null) {
                System.out.printf("received client %s message:%s\n", id, received);
                if ("quit".equals(received)) {
                    writer.write("client will be closed.");
                    writer.flush();
                    socket.close();
                    break;
                }
                writer.write("server response:" + received + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
