/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import java.awt.event.ActionEvent;
import threads.WrittingThread;
import ui.form.FChat;

/**
 *
 * @author Heca
 */
public class Controller {

    FChat fChat;
    WrittingThread writtingThread;

    /*
    JButton send;
    JTextField jtxtYourMessage;
    JTextField jtxtLastMessage;
    JTextArea jtxtAll;
    JButton jbtnSend;*/
    public Controller() {
        fChat = new FChat();
        fChat.setVisible(true);
        prepareForm();

    }
    

    public void setWrittingThread(WrittingThread wT) {
        this.writtingThread = wT;
    }

    public void showMessage(String message) {
        if (fChat.getJtxtAll().getText().isEmpty()) {
            fChat.getJtxtAll().append(message);
        } else {
            fChat.getJtxtAll().append("\n" + message);
        }
    }
    
    public void showSent(String message) {
        fChat.getJtxtYourMessage().setText("");
        showMessage("You: " + message);
    }

    public void showReceived(String message) {
        fChat.getJtxtLast().setText(message);
        showMessage("Friend: " + message);

    }

    public String getMessageFromForm() {
        return fChat.getJtxtYourMessage().getText();
    }

    private void prepareForm() {
        fChat.getJbtnSend().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSendActionPerformed(evt);
            }

            private void jbtnSendActionPerformed(ActionEvent evt) {
                writtingThread.setSendSignal();
            }
        });

    }

}
