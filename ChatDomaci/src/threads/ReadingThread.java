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
import java.net.Socket;
/**
 *
 * @author Heca
 */
public class ReadingThread extends Thread{
    
    private final Socket socket;
    Controller controller;

    public ReadingThread(Socket socket,Controller controller) {
        super();
        this.socket = socket;
        this.controller=controller;
    }
    
    
    
    @Override
    public void run() {
        while(true){
            try {
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                controller.showReceived(bufferedReader.readLine());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
}

