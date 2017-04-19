
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends javax.swing.JFrame {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private int counter = 1;
    
    public Server() {
        super("Server");
        initComponents(); 
        setVisible(true);
        
    }
    
    public void runServer() {
        try {
           server = new ServerSocket(12345, 100);
            //server = new ServerSocket(100);
            while(true) {
                try {
                    waitForConnection();
                    getStreams();
                    
                    processConnection();
                } catch(EOFException e) {
                    displayMessage("\nServer Terminated");
                }
                finally {
                    closeConnection();
                    counter++;
                }
            }
        }catch(IOException e) {
            
        }
    }
    
    private void waitForConnection() throws IOException {
        displayMessage("Waiting for connection");
        connection = server.accept();
        displayMessage("\nConnection" + counter + "received From: " + connection.getInetAddress().getHostName());
    }
    
    private void getStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());        
        displayMessage("\nGot Stream");
        
    }
    
    private void processConnection() throws IOException {
        String message ="\nConnection Succesful";
        setOnline();
        sendData(message);
        //setTextFieldEditable(true);
        do {
            try {
                message = (String) input.readObject();
                displayMessage("\n" + message);
            } catch(ClassNotFoundException e) {
               
            }
        }while(!message.equals("CLIENT: TERMINATE"));
    }
    
    private void closeConnection() {
        displayMessage("\nTerminating Connection");
       // setTextFieldEditable(false);
        
        try {
            output.close();
            input.close();
            connection.close();
        } catch(IOException e) {
            
        }
    }
    
    private void sendData(String message) {
        try {
            output.writeObject("SERVER: " + message);
            output.flush();
            displayMessage("\nSERVER: " + message);
        } catch(IOException e) {
            
        }
    }
    
    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                displayArea.append(messageToDisplay);
            }
        });
    }
    
    /*private void setTextFieldEditable(final boolean editable) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                enterField.setEditable(editable);
            }
        });
    }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        displayArea = new javax.swing.JTextArea();
        enterField = new javax.swing.JTextField();
        btn_send = new javax.swing.JButton();
        lb_online = new javax.swing.JLabel();
        lb_client1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        displayArea.setColumns(20);
        displayArea.setRows(5);
        jScrollPane1.setViewportView(displayArea);

        enterField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enterFieldKeyPressed(evt);
            }
        });

        btn_send.setText("Send");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        lb_online.setText("Currently Online:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(enterField)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_online, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_client1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_send)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_online)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_client1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_send, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(enterField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        String msg=enterField.getText();
        sendData(msg);
        enterField.setText("");
          
// TODO add your handling code here:
    }//GEN-LAST:event_btn_sendActionPerformed

    private void enterFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enterFieldKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           String msg=enterField.getText();
           sendData(msg);
            enterField.setText("");
            
        } 
        
// TODO add your handling code here:
    }//GEN-LAST:event_enterFieldKeyPressed
       public void setOnline(){
      //lb_client1.setText(new ClientTest().getUsername());
      lb_client1.setText(connection.getInetAddress().getHostName());
      System.out.println(connection.getInetAddress().getHostName());
       
   }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { 
                            }
        });
        
        Server application = new Server();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.runServer(); 
        application.setVisible(true);
        System.out.println(new ClientTest().clientname);
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_send;
    private javax.swing.JTextArea displayArea;
    private javax.swing.JTextField enterField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_client1;
    private javax.swing.JLabel lb_online;
    // End of variables declaration//GEN-END:variables
}
