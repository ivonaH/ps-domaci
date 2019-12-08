/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import ui.controller.Controller;
import java.net.Socket;
import threads.ReadingThread;
import threads.WrittingThread;

/**
 *
 * @author Heca
 */
public class Client {

    private Socket socket;
    Controller controller;

    public Client() {
        controller = new Controller();
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
        
    }

    private void connect() {
        try {
            socket = new Socket("localhost", 8559);
            ReadingThread readingThread = new ReadingThread(socket,controller);
            readingThread.start();
            WrittingThread writtingThread = new WrittingThread(socket,controller);
            writtingThread.start();
            controller.setWrittingThread(writtingThread);

            readingThread.join();
            writtingThread.join();
            System.out.println("End");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
