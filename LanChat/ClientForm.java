
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClientForm extends javax.swing.JFrame {
    private String chatServer;
     Socket server = null;//simply jst a socket
    ObjectInputStream dis=null;
    ObjectOutputStream dos=null;
    String message = "";
    static String username;
   // static String name;
    
   
    public ClientForm(String host) {
        super("Client");
         chatServer = host;
        initComponents();
        setVisible(true);
    }
    public ClientForm()
    {
        chatServer="127.0.0.1";
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt_recMsg = new javax.swing.JTextArea();
        txt_msg = new javax.swing.JTextField();
        btn_Send = new javax.swing.JButton();
        lb_online = new javax.swing.JLabel();
        lb_loggedin = new javax.swing.JLabel();
        lb_username = new javax.swing.JLabel();
        lb_server = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_recMsg.setColumns(20);
        txt_recMsg.setRows(5);
        jScrollPane1.setViewportView(txt_recMsg);

        txt_msg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_msgKeyPressed(evt);
            }
        });

        btn_Send.setText("Send");
        btn_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SendActionPerformed(evt);
            }
        });

        lb_online.setText("Currently Online");

        lb_loggedin.setText("Currently Loggedin As : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Send, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_loggedin)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lb_username, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_online, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_server, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_username, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lb_online)
                        .addGap(8, 8, 8)
                        .addComponent(lb_server, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_loggedin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btn_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SendActionPerformed
        String msg=txt_msg.getText();
           sendData(msg);
            txt_msg.setText("");
    }//GEN-LAST:event_btn_SendActionPerformed

    private void txt_msgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_msgKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           String msg=txt_msg.getText();
           sendData(msg);
            txt_msg.setText("");
            
        }
    }//GEN-LAST:event_txt_msgKeyPressed
    
    public void runClient() {
        try {
            connectToServer();
            getStreams();
            processConnection();
        } catch(EOFException e) {
            
        }catch(IOException e) {
          
        } finally {
          closeConnection();
        }
    }
    
    private void connectToServer() throws IOException {
        displayMessage("Attempting Connection");        
        server=new Socket(InetAddress.getByName(chatServer),12345);
        //server=new Socket("127.0.0.1",100);
        displayMessage("\n Connected to: " + server.getInetAddress().getHostName());
    }
    
    private void getStreams() throws IOException {
        dos = new ObjectOutputStream(server.getOutputStream());
        dos.flush();
        dis = new ObjectInputStream(server.getInputStream());
        displayMessage("\n Got I/O Srreams");
    }
    
    private void processConnection() throws IOException {
        do {
            try {
                message = (String) dis.readObject();
                displayMessage("\n" + message);
            } catch(ClassNotFoundException e) {
                
            }
        }while (!message.equals("SERVER: TERMINATE"));
    }
    
    private void closeConnection() {
        displayMessage("\nClosing Connection");
        //setTextFieldEditable(false);
        
        try {
            dos.close();
            dis.close();
            server.close();
        } catch(IOException e) {
            
        }
    } 
    
    private void sendData(String message) {
        try {
            dos.writeObject(username+": " + message);
            dos.flush();
            displayMessage("\n"+username+": " + message);
        } catch(IOException e) {
            
        }
    }
    
    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                txt_recMsg.append(messageToDisplay);
            }
        });
    }
    /*
    private void setTextFieldEditable(final boolean editable) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                txt_msg.setEditable(editable);
            }
        });
    }*/
     public void setUserName(String name1){
         try{
             lb_username.setText(name1);
         }catch(NullPointerException e){
             
         }
         username=name1;
         

        }
     public String getUserName(){
         return this.username;
     }
  
        
        
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Send;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_loggedin;
    private javax.swing.JLabel lb_online;
    private javax.swing.JLabel lb_server;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextField txt_msg;
    private javax.swing.JTextArea txt_recMsg;
    // End of variables declaration//GEN-END:variables
}
