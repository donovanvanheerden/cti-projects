/*
 * Author       :   Donovan van Heerden 
 * Student No   :   EL2014-0043
 * Date         :
 */
package javaproject2_videostore;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.text.Element;

public class VideoStoreServerGUI 
{

    
    //Globals
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    public static ArrayList<String> CurrentUsers = new ArrayList<String>();
    private static boolean LISTENING = true;
    public static VideoServer SERVER;
    private static ServerSocket VidServerSocket;
    
    
    //GUI Globals - Server Window
    public static JMenuBar MenuBar = new JMenuBar();
    private static JMenu MainMenu = new JMenu();
    private static JMenuItem btnTest = new JMenuItem();
    private static JMenuItem btnHelp =  new JMenuItem();
    private static JMenuItem btnAbout = new JMenuItem();
    
    public static JFrame MainWindow = new JFrame();
    private static JButton btnLogin = new JButton();
    private static JButton btnStartServer = new JButton();
    private static JButton btnStopServer = new JButton();
    
    public static JTextField txtUsername = new JTextField(20);
    public static JLabel lblUsername = new JLabel();
    public static JTextField txtPassword = new JTextField(20);
    public static JLabel lblPassword = new JLabel();
    
    public static JTextArea Console = new JTextArea();
    private static JScrollPane spConsole = new JScrollPane();
    
    private static JPanel pnlServerControls = new JPanel();
    
    public VideoStoreServerGUI()
    {
        MainWindow.setTitle("VideoStore-Server");
        MainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        MainWindow.setResizable(false);
        ConfigureMainWindow();
        MainWindow_Action();
        MainWindow.setVisible(true);
    }
    
//-----------------------------------------------------------------------------
    public static void main(String[] args) 
    {
        VideoStoreServerGUI VideoStore = new VideoStoreServerGUI();
    }
//-----------------------------------------------------------------------------
     public static void ConfigureMainWindow()
     {
        MainWindow.setBackground(new java.awt.Color(255, 255, 255));
        MainWindow.setSize(620, 370);
        MainWindow.setLocation(220,180);
        MainWindow.getContentPane().setLayout(null);
        
        MainWindow.setJMenuBar(MenuBar);
        MenuBar.setVisible(true);
        
        btnTest.setText("Test");
        btnHelp.setText("Help");
        btnAbout.setText("About");
        MainMenu.setText("Information");
        MainMenu.add(btnTest);
        MainMenu.add(btnAbout);
        MainMenu.add(btnHelp);
        MenuBar.add(MainMenu);
        
        
        txtUsername.setBounds(80, 10, 150, 25);
        lblUsername.setBounds(10 ,10 , 80, 25);
        lblUsername.setText("Username:");
        MainWindow.getContentPane().add(txtUsername);
        MainWindow.getContentPane().add(lblUsername);
        
        txtPassword.setBounds(310, 10, 150, 25);
        lblPassword.setBounds(240, 10, 80, 25);
        lblPassword.setText("Password:");
        MainWindow.getContentPane().add(txtPassword);
        MainWindow.getContentPane().add(lblPassword);
        
        btnLogin.setBounds(500,10,100,25);
        btnLogin.setText("Login");
        MainWindow.getContentPane().add(btnLogin);
        
        pnlServerControls.setBounds(10, 50, 595, 255);
        pnlServerControls.setBorder(BorderFactory.createEtchedBorder());
        pnlServerControls.setLayout(null);
        pnlServerControls.setVisible(true);
        
        btnStartServer.setBounds(10, 10, 120, 25);
        btnStartServer.setText("Start Server");
        //btnStartServer.setEnabled(false);
        pnlServerControls.add(btnStartServer);
        
        btnStopServer.setBounds(140, 10, 120, 25);
        btnStopServer.setText("Stop Server");
        btnStopServer.setEnabled(false);
        pnlServerControls.add(btnStopServer);
        
        spConsole.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spConsole.setViewportView(Console);
        pnlServerControls.add(spConsole);
        spConsole.setBounds(10, 50, 575, 195);
        
        Console.setColumns(20);
        Console.setFont(new java.awt.Font("Tahoma", 0, 12));
        Console.setForeground(new java.awt.Color(0, 0, 0));
        Console.setLineWrap(true);
        Console.setRows(5);
        Console.setEditable(false);
        Console.append("---Server Console---\n");
        
        MainWindow.getContentPane().add(pnlServerControls);
        
        
     }
//-----------------------------------------------------------------------------
     public static void MainWindow_Action()
     {
        btnLogin.addActionListener(
                new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) 
                {
                   
                }
            }
        );
        
        btnTest.addActionListener(
                new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) 
                {
                   
                }
            }
        );
         
        btnStartServer.addActionListener(new java.awt.event.ActionListener() {

               public void actionPerformed(java.awt.event.ActionEvent evt) 
               {
                   ACTION_START();
               }
           });
        
        btnStopServer.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) 
                {
                    ACTION_STOP();
                }
            });
        
        btnAbout.addActionListener(
                new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) 
                {
                    try
                   {
                       JOptionPane.showMessageDialog(null, "Video Store Server\nCreated by Donovan van Heerden");
                   }
                   catch(Exception X)
                   {
                       Console.append("\n" + X.toString());
                       //Console.setCaretPosition(Console.getDocument().getLength());
                   }
                }
            }
        );
        
        MainWindow.addWindowListener( new java.awt.event.WindowAdapter() {
        
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                try
                {
                    System.out.println("In windowClosing Event");
                    if (VidServerSocket != null && !VidServerSocket.isClosed())
                    {
                        System.out.println("Not in else");
                        if (JOptionPane.showConfirmDialog(MainWindow,"Are you sure you want to close\nwhile the server is still running?",
                        "Close?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                        {
                            
                            for (int i = 0; i< ConnectionArray.size(); i++)
                            {
                                System.out.println((ConnectionArray.get(i).toString()) + "\nGoing to try close sockets.");
                                if(ConnectionArray.get(i).isClosed())
                                {
                                    return;
                                }
                                PrintWriter OUT = new PrintWriter(ConnectionArray.get(i).getOutputStream());
                                OUT.println("!ServerClosed");
                                OUT.flush();
                                
                                VideoStoreServerGUI.Console.append("\n" + VideoStoreServerGUI.CurrentUsers.get(i) + " has disconnected.");
                                Console.setCaretPosition(Console.getDocument().getLength());
                                VideoStoreServerGUI.CurrentUsers.remove(i);
                                VideoStoreServerGUI.ConnectionArray.remove(i);
                            }
                            
                            System.exit(0);
                        }
                    }
                    else
                    {
                        System.out.println("In else");
                        System.exit(0);
                    }
                }
                catch (Exception X)
                {
                    System.out.println("Tried to close ServerSocket");
                    System.out.println(X);
                }
            }
        });
     }
//------------------------------------------------------------------------------
     public static void ACTION_START()
     {
         try
                   {
                        System.out.println("Attempting to start");
                        LISTENING = true;
                        SERVER = new VideoServer();
                        btnStartServer.setEnabled(false);
                        btnStopServer.setEnabled(true);
                   }
                   catch(Exception X)
                   {
                       Console.append(X.toString());
                   }
     }
//------------------------------------------------------------------------------
     public static void ACTION_STOP()
     {
        try
        {
            try
            {
                for (int i = 0; i< ConnectionArray.size(); i++)
                {
                    System.out.println((ConnectionArray.get(i).toString()) + "\nGoing to try close sockets.");
                    PrintWriter OUT = new PrintWriter(ConnectionArray.get(i).getOutputStream());
                    OUT.println("!Disconnect");
                    OUT.flush();

                    VideoStoreServerGUI.Console.append("\n" + VideoStoreServerGUI.CurrentUsers.get(i) + " has disconnected.");
                    //Console.setCaretPosition(Console.getDocument().getLength());
                    VideoStoreServerGUI.ConnectionArray.get(i).close();
                    VideoStoreServerGUI.CurrentUsers.remove(i);
                    VideoStoreServerGUI.ConnectionArray.remove(i);
                }

                if(VidServerSocket.isClosed())
                {
                    System.out.println("ServerSocket is closed");
                }
                else
                {
                    System.out.println("ServerSocket is open");
                    LISTENING = false;
                    System.out.println("LISTENING SET TO FALSE");
                    VidServerSocket.close();
                    System.out.println("SOCKET CLOSED");
                    SERVER.SERVER.interrupt();
                    System.out.println("THREAD INTERUPTED");
                    SERVER.SERVER = null;
                    System.out.println("THREAD SET TO NULL");
                    btnStartServer.setEnabled(true);
                    btnStopServer.setEnabled(false);
                }
            }
            catch(Exception X)
            {
                Console.append("\n" + X.toString());
                //Console.setCaretPosition(Console.getDocument().getLength());
                VidServerSocket.close();
            }
        }
        catch(IOException IO)
        {
            Console.append("\n" + IO.toString());
            //Console.setCaretPosition(Console.getDocument().getLength());
        }
         
     }
//------------------------------------------------------------------------------
     public static void StartServer(int ServerPort) throws IOException
     {
         
        ServerSocket SERVER;
        try
        {
            Console.append("\nAttempting to start server...");
            System.out.println("In StartServer()");
            VidServerSocket = new ServerSocket(ServerPort);
            //VidServerSocket = SERVER;
            Console.append("\nWaiting for clients...");
            Console.setCaretPosition(Console.getDocument().getLength());
            
            while(LISTENING)
            {
                System.out.println("In StartServer(): While(LISTENING)");
                Socket SOCK = VidServerSocket.accept();
                Console.setCaretPosition(Console.getDocument().getLength());
                //System.out.println("In StartServer(): While(LISTENING)\nSocket Accpeted");
                ConnectionArray.add(SOCK);
                CurrentUsers.add(SOCK.getInetAddress().getHostName().toString());
                System.out.println(ConnectionArray);
                
                //System.out.println("In StartServer(): While(LISTENING)\nAdded to ConnectionArray");
                Console.append("\n\nClient connected: " + SOCK.getInetAddress().getHostName());
                Console.setCaretPosition(Console.getDocument().getLength());
                VideoStoreServer VidStore = new VideoStoreServer(SOCK);
                
                //System.out.println("In StartServer(): While(LISTENING)\nNew VidStore");
                Thread x = new Thread(VidStore);
                //System.out.println("In StartServer(): While(LISTENING)\nNew Thread About to start");
                x.start();
            }
            
            //VidServerSocket.close();
            
        }
        catch(Exception x)
        {
            //Console.append("\nCaught Exception in main method");
            if (x.toString().contains("socket closed"))
            {
                Console.append("\nConnection Closed.\n");
                Console.setCaretPosition(Console.getDocument().getLength());
            }
            else
            {
                Console.append("\n" + x.toString());
                Console.setCaretPosition(Console.getDocument().getLength());
            }
        }
     }
//------------------------------------------------------------------------------
     
}
//------------------------------------------------------------------------------
class VideoServer implements Runnable
{
    Thread SERVER;
    VideoStoreServerGUI MAIN;
    
    VideoServer() {
        init();
    }
    
    private void init() {
        try
        {
            if(SERVER == null)
            {
                System.out.println("In Thread");
                SERVER = new Thread(this);
                SERVER.start();
                System.out.println("Thread Started");
            }

        }
        catch (Exception x)
        {
            
        }
    }
    
    public void run()
    {
        
        while(!Thread.currentThread().isInterrupted())
        {
            try
            {
                if (SERVER == null)
                {
                    System.out.println("I AM NULL");
                    return;
                }
                if (SERVER.isInterrupted())
                {
                    System.out.println("I WAS INTERRUPTED!!!!");
                    return;
                }
                
                System.out.println(SERVER.isInterrupted());
                MAIN.StartServer(7777);
            }
            catch(Exception ie)
            {
                System.out.println("Server Thread");
                System.out.println(ie.toString());
            }
        }
        
    }
    
}