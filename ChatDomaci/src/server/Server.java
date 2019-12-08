/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import ui.controller.Controller;
import java.net.ServerSocket;
import java.net.Socket;
import threads.ReadingThread;
import threads.WrittingThread;

/**
 *
 * @author Heca
 */
public class Server {

    private ServerSocket serverSocket;
    Controller controller;

    public Server() {
        controller = new Controller();
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void start() throws Exception {
        serverSocket = new ServerSocket(8559);
        System.out.println("Waiting clients");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");
        ReadingThread readingThread = new ReadingThread(socket, controller);
        readingThread.start();
        WrittingThread writtingThread = new WrittingThread(socket, controller);
        writtingThread.start();
        controller.setWrittingThread(writtingThread);

        readingThread.join();
        writtingThread.join();
        System.out.println("End");

    }
}
