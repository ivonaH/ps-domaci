/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import ui.controller.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Heca
 */
public class WrittingThread extends Thread {

    private final Socket socket;
    private final Controller controller;
    boolean sendSignal;
    String message = "";

    public WrittingThread(Socket socket, Controller controller) {
        super();
        this.socket = socket;
        this.controller = controller;
        sendSignal = false;
    }

    @Override
    public void run() {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            sendSignal = false;
            while (true) {

                if (sendSignal) {
                    printWriter.println(controller.getMessageFromForm());
                    controller.showSent(controller.getMessageFromForm());
                    sendSignal = false;
                }
                Thread.sleep(100);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSendSignal() {
        sendSignal = true;
    }

}
