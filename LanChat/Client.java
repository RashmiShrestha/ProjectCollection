
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends javax.swing.JFrame {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message = "";
    private String chatServer;
    private Socket client;
    static String username;
   public Client(){
       super("Client");
       initComponents();
       
       
       
   }
    public Client(String host) {
        super("Client");
        chatServer = host;
        initComponents();      
        setVisible(true); 
              
    }
    
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
        displayMessage("\nAttempting Connection");        
        //client=new Socket("127.0.0.1",2000);
        client = new Socket(InetAddress.getByName(chatServer), 100);        
        displayMessage("\nConnected to: " + client.getInetAddress().getHostName());
        
    }
    
    private void getStreams() throws IOException {
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();        
        input = new ObjectInputStream(client.getInputStream());        
        displayMessage("\nGot I/O Srreams");
    }
    
    private void processConnection() throws IOException {
       // setTextFieldEditable(true);        
        do {
            try {
                message = (String) input.readObject();
                displayMessage("\n" + message);
            } catch(ClassNotFoundException e) {
                
            }
        }while (!message.equals("SERVER: TERMINATE"));
    }
    
    private void closeConnection() {
        displayMessage("\nClosing Connection");
        //setTextFieldEditable(false);
        
        try {
            output.close();
            input.close();
            client.close();
        } catch(IOException e) {
            
        }
    } 
    
    private void sendData(String message) {
        try {
            output.writeObject("CLIENT: " + message);
            output.flush();
            displayMessage("\nCLIENT: " + message);
        } catch(IOException e) {
            displayArea.append("\nError writing object");
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
        jLabel1 = new javax.swing.JLabel();
        lb_username = new javax.swing.JLabel();

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

        jLabel1.setText("Logged in As:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(enterField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_send))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_username, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_send))
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

    public void setUserName(String name){
         lb_username.setText(name);

        }
     public String getUserName(){
         return this.username;
     }
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                }
        });
        Client application=new Client();
            username=JOptionPane.showInputDialog("Enter Clients name:");
            application.setName(username);        
        if(args.length == 0) {
                        application=  new Client("127.0.0.1");
                    } else {
                        application = new Client(args[0]);
                    }
                    application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    application.runClient();
                    application.setVisible(true);
                   
                    
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_send;
    private javax.swing.JTextArea displayArea;
    private javax.swing.JTextField enterField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_username;
    // End of variables declaration//GEN-END:variables
}
