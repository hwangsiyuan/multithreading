package com.hussein.tpm;

import com.hussein.pool.BasicThreadPool;
import com.hussein.pool.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>Title: ChatServer</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/8 11:38 AM
 */
public class ChatServer {

    private int port;

    private ThreadPool threadPool;

    private ServerSocket serverSocket;

    public ChatServer() {
        this(13123);
    }

    public ChatServer(int port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        threadPool = new BasicThreadPool(1, 2, 4, 100);
        serverSocket = new ServerSocket(port);
        serverSocket.setReuseAddress(true);
        System.out.println("ChatServer is started and listen port:" + port);
        this.listen();
    }

    private void listen() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.execute(new ClientHandler(socket));
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().startServer();
    }
}
