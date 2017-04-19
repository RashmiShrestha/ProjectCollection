/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication2;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ClientTest {
   /* public static String clientname;
    public String getUsername(){
        return this.clientname;
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {}
        });
        ClientForm application=new ClientForm();
            clientname=JOptionPane.showInputDialog("Enter Clients name:");
            application.setUserName(clientname);  
            application.runClient();
            application.setVisible(true);
            application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 
                    
    }
}
    */
     public static String clientname;
    public String getUsername(){
        return this.clientname;
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {}
        });
        ClientForm application;
        ClientForm app=new ClientForm();
        
        clientname=JOptionPane.showInputDialog("Enter Clients name:");
        try{
        app.setUserName(clientname);
        }
        catch(NullPointerException e){
            
        }
        if(args.length == 0) {
            application=  new ClientForm("127.0.0.1");
        } else {
            application = new ClientForm(args[0]);
         }
        application.runClient();
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 
                    
    }
}
