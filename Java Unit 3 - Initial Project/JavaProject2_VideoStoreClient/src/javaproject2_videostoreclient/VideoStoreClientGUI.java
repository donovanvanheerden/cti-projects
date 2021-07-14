/*
 * Author       :   Donovan van Heerden 
 * Student No   :   EL2014-0043
 * Date         :
 */
package javaproject2_videostoreclient;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class VideoStoreClientGUI
{
    
    //Globals
    private static VideoStoreClient VideoClient;
    public static String HostName = "";
    public static Socket SOCK;
    
    //GUI Globals - Client Window
    public static JMenuBar MenuBar = new JMenuBar();
    public static JMenuBar AdminMenuBar = new JMenuBar();
    private static JMenu InfoMenu = new JMenu();
    private static JMenu AdminInfoMenu = new JMenu();
    private static JMenu ConMenu = new JMenu();
    private static JMenu SearchMenu = new JMenu();
    private static JMenuItem btnHelp =  new JMenuItem();
    private static JMenuItem btnAdminHelp =  new JMenuItem();
    private static JMenuItem btnAbout = new JMenuItem();
    private static JMenuItem btnAdminAbout = new JMenuItem();
    public static JMenuItem btnConn = new JMenuItem();
    public static JMenuItem btnDisc = new JMenuItem();
    public static JMenuItem btnSearchMov = new JMenuItem();
    public static JMenuItem btnSearchGen = new JMenuItem();
    public static JMenuItem btnConLogin = new JMenuItem();
    public static JMenuItem btnConLogout = new JMenuItem();
    
    public static JFrame ClientMainWindow = new JFrame();
    
    private static JLabel lblSearch = new JLabel();
    public static JTextField txtSearch = new JTextField();
    
    public static JTextField txtUsername = new JTextField(20);
    private static JLabel lblUsername = new JLabel();
    public static JTextField txtPassword = new JTextField(20);
    private static JLabel lblPassword = new JLabel();
    
    public static JTable dtResults = new JTable();
    public static JScrollPane spResults = new JScrollPane();
    
    public static JPanel pnlSearchControls = new JPanel();
    
    //GUI Globals - Client Admin Window
    public static JFrame AdminMainWindow = new JFrame();
    private static JButton btnLogout = new JButton();
    
    private static JComboBox cbxMovieList = new JComboBox();
    private static JButton btnSelect = new JButton();
    
    private static JLabel lblMovieTitle = new JLabel();
    private static JLabel lblMovieDesc =  new JLabel();
    private static JLabel lblMovieGenre = new JLabel();
    
    public static JTextField txtMovieTitle = new JTextField();
    public static JTextField txtMovieGenre = new JTextField();
    public static JTextArea taMovieDesc = new JTextArea();
    private static JScrollPane spMovieDesc = new JScrollPane();
    
    private static JButton btnEdit = new JButton();
    private static JButton btnSave = new JButton();
    private static JButton btnDelete = new JButton();
    
    private static JPanel pnlMovieInfo = new JPanel();
    
//-----------------------------------------------------------------------------    
    public VideoStoreClientGUI()
    {
        ClientMainWindow.setTitle("VideoStore-Client");
        ClientMainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ClientMainWindow.setResizable(false);
        ConfigureClientMainWindow();
        ClientMainWindow_Action();
        ClientMainWindow.setVisible(true);
        DisableControls();
    }
//-----------------------------------------------------------------------------
    public static void main(String[] args)
    {
        VideoStoreClientGUI VideoStore = new VideoStoreClientGUI();
        ACTION_CONNECT();
    }
//-----------------------------------------------------------------------------
    public static void ConfigureClientMainWindow()
    {
        ClientMainWindow.setBackground(new java.awt.Color(255, 255, 255));
        ClientMainWindow.setSize(620, 370);
        ClientMainWindow.setLocation(220,180);
        ClientMainWindow.getContentPane().setLayout(null);
        
        ClientMainWindow.setJMenuBar(MenuBar);
        MenuBar.setVisible(true);
        
        btnDisc.setText("Disconnect");
        btnConn.setText("Connect");
        btnConLogin.setText("Login");
        btnConLogout.setText("Logout");
        ConMenu.add(btnConn);
        ConMenu.add(btnDisc);
        ConMenu.add(new JSeparator());
        ConMenu.add(btnConLogin);
        ConMenu.add(btnConLogout);
        ConMenu.setText("Connection");
        MenuBar.add(ConMenu);
        
        btnSearchMov.setText("Movie Search");
        btnSearchGen.setText("Genre Search");
        SearchMenu.setText("Search");
        SearchMenu.add(btnSearchMov);
        SearchMenu.add(btnSearchGen);
        MenuBar.add(SearchMenu);
        
        btnHelp.setText("Help");
        btnAbout.setText("About");
        InfoMenu.setText("Info");
        InfoMenu.add(btnAbout);
        InfoMenu.add(btnHelp);
        MenuBar.add(InfoMenu);
        
        txtUsername.setBounds(80, 10, 220, 25);
        lblUsername.setBounds(10 ,10 , 80, 25);
        lblUsername.setText("Username:");
        ClientMainWindow.getContentPane().add(txtUsername);
        ClientMainWindow.getContentPane().add(lblUsername);
        
        txtPassword.setBounds(385, 10, 220, 25);
        lblPassword.setBounds(315, 10, 80, 25);
        lblPassword.setText("Password:");
        ClientMainWindow.getContentPane().add(txtPassword);
        ClientMainWindow.getContentPane().add(lblPassword);
        
        pnlSearchControls.setBounds(10, 50, 595, 255);
        pnlSearchControls.setBorder(BorderFactory.createEtchedBorder());
        pnlSearchControls.setLayout(null);
        pnlSearchControls.setVisible(true);
        
        lblSearch.setBounds(10, 10, 80, 25);
        lblSearch.setText("Search:");
        txtSearch.setBounds(60, 10, 525, 25);
        pnlSearchControls.add(lblSearch);
        pnlSearchControls.add(txtSearch);
        
        spResults.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spResults.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spResults.setViewportView(dtResults);
        pnlSearchControls.add(spResults);
        spResults.setBounds(10, 50, 575, 195);
        
        dtResults.setFont(new java.awt.Font("Tahoma", 0, 12));
        dtResults.setForeground(new java.awt.Color(0, 0, 0));
        
        ClientMainWindow.getContentPane().add(pnlSearchControls);
        
    }
//-----------------------------------------------------------------------------
    public static void ClientMainWindow_Action()
    {
        try
        {
            btnConLogin.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) 
                    {
                        BuildAdminMainWindow();
                    }
                });

            btnSearchMov.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) 
                    {
                        ACTION_SENDQUERY();
                    }
                });
            
            btnDisc.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) 
                    {
                        ACTION_DISCONNECT();
                    }
                });
            
            btnConn.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) 
                    {
                        ACTION_CONNECT();
                    }
                });

            ClientMainWindow.addWindowListener(new java.awt.event.WindowAdapter(){
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent)
                {
                    ACTION_CLOSE();
                }
            });
            
        }
        catch(Exception X)
        {
            System.out.println(X);
        }                
    }
//-----------------------------------------------------------------------------
    public static void ACTION_SENDQUERY()
    {
        if (!txtSearch.getText().equals(""))
        {
            VideoClient.SendQuery(txtSearch.getText());
            txtSearch.requestFocus();
        }
    }
//-----------------------------------------------------------------------------
    public static void ACTION_CONNECT()
    {
        DisableControls();
        HostName = JOptionPane.showInputDialog(null, "Please enter Hostname:");
        if (HostName == null||(HostName != null && ("".equals(HostName))))
        {

        }
        else
        {
            Connect(HostName);
        }
    }
//-----------------------------------------------------------------------------
   public static void ACTION_DISCONNECT()
   {
       VideoClient.DISCONNECT();
   }
//-----------------------------------------------------------------------------
   public static void ACTION_CLOSE()
   {
       String[] closingOptions = {"Minimize", "Exit", "Cancel"};
       int response = JOptionPane.showOptionDialog(null,
            "Are you sure you want to exit?", "Exit?", 0,
                JOptionPane.WARNING_MESSAGE, null, closingOptions, closingOptions[1]);

       if (response == 0)
       {
           ClientMainWindow.setState(ClientMainWindow.ICONIFIED);
       }
       else if (response == 1)
       {
           System.exit(0);
       }
       
   }
//-----------------------------------------------------------------------------
    public static void EnableControls()
    {
        btnConn.setEnabled(false);
        txtSearch.setEnabled(true);
        txtUsername.setEnabled(true);
        txtPassword.setEnabled(true);
        btnDisc.setEnabled(true);
        btnConLogin.setEnabled(true);
        SearchMenu.setEnabled(true);
    }
    public static void DisableControls()
    {
        btnConn.setEnabled(true);
        txtSearch.setEnabled(false);
        txtUsername.setEnabled(false);
        txtPassword.setEnabled(false);
        btnDisc.setEnabled(false);
        btnConLogin.setEnabled(false);
        btnConLogout.setEnabled(false);
        SearchMenu.setEnabled(false);
    }
//-----------------------------------------------------------------------------
    public static void Connect(String host)
    {
        try
        {
            final int PORT = 7777;
            final String HOST;
            HOST = host;
            SOCK = new Socket(HOST, PORT);
            System.out.println("You connected to: " + HOST);
            
            VideoClient = new VideoStoreClient(SOCK);
            
            Thread X = new Thread(VideoClient);
            X.start();
            EnableControls();
        }
        catch (Exception X)
        {
            System.out.print(X+"\n");
            if (JOptionPane.showConfirmDialog(ClientMainWindow,
                "Server not responding!\nDo you want to retry?",
                    "Alert!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                if (!HostName.equals(""))
                {
                    Connect(HostName);
                }
                else
                {
                    HostName = JOptionPane.showInputDialog(null, "Please enter Hostname:");
                    if (HostName == null||(HostName != null && ("".equals(HostName))))
                    {
                        
                    }
                    else
                    {
                        Connect(HostName);
                    }
                }
            }
        }
    }
//-----------------------------------------------------------------------------
    public static void BuildAdminMainWindow()
    {
        ClientMainWindow.setVisible(false);
        AdminMainWindow.setTitle("VideoStore-Client");
        AdminMainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        AdminMainWindow.setResizable(false);
        ConfigureAdminMainWindow();
        AdminMainWindow_Action();
        AdminMainWindow.setVisible(true);
    }
//-----------------------------------------------------------------------------
    public static void ConfigureAdminMainWindow()
    {
        AdminMainWindow.setBackground(new java.awt.Color(255, 255, 255));
        AdminMainWindow.setSize(620, 370);
        AdminMainWindow.setLocation(220,180);
        AdminMainWindow.getContentPane().setLayout(null);
        
        AdminMainWindow.setJMenuBar(AdminMenuBar);
        AdminMenuBar.setVisible(true);
        
        btnAdminHelp.setText("Help");
        btnAdminAbout.setText("About");
        AdminInfoMenu.setText("Info");
        AdminInfoMenu.add(btnAbout);
        AdminInfoMenu.add(btnHelp);
        AdminMenuBar.add(AdminInfoMenu);
        
        cbxMovieList.setBounds(10, 10, 485, 25);
        btnSelect.setBounds(505, 10, 100, 25);
        btnSelect.setText("Select");
        AdminMainWindow.getContentPane().add(cbxMovieList);
        AdminMainWindow.getContentPane().add(btnSelect);
        
        pnlMovieInfo.setBounds(10, 50, 595, 225);
        pnlMovieInfo.setBorder(BorderFactory.createEtchedBorder());
        pnlMovieInfo.setLayout(null);
        pnlMovieInfo.setVisible(true);
        
        lblMovieTitle.setBounds(10, 10, 100, 25);
        lblMovieTitle.setText("Movie Title:");
        lblMovieGenre.setBounds(10, 45, 100, 25);
        lblMovieGenre.setText("Genre:");
        lblMovieDesc.setBounds(10, 80, 150, 25);
        lblMovieDesc.setText("Movie Description:");
        pnlMovieInfo.add(lblMovieTitle);
        pnlMovieInfo.add(lblMovieDesc);
        pnlMovieInfo.add(lblMovieGenre);
        
        txtMovieTitle.setBounds(80, 10, 200, 25);
        txtMovieTitle.setEditable(false);
        txtMovieGenre.setBounds(80, 45, 200, 25);
        txtMovieGenre.setEditable(false);
        taMovieDesc.setEditable(false);
        
        spMovieDesc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spMovieDesc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spMovieDesc.setViewportView(taMovieDesc);
        pnlSearchControls.add(spMovieDesc);
        spMovieDesc.setBounds(10, 100, 575, 80);
        
        pnlMovieInfo.add(txtMovieTitle);
        pnlMovieInfo.add(txtMovieGenre);
        pnlMovieInfo.add(spMovieDesc);
        
        btnSave.setBounds(375, 190, 100, 25);
        btnSave.setText("Save");
        btnSave.setVisible(false);
        btnEdit.setBounds(375, 190, 100, 25);
        btnEdit.setText("Edit");
        btnDelete.setBounds(485, 190, 100, 25);
        btnDelete.setText("Delete");
        pnlMovieInfo.add(btnEdit);
        pnlMovieInfo.add(btnDelete);
        
        AdminMainWindow.getContentPane().add(pnlMovieInfo);
        
        btnLogout.setBounds(505, 285, 100, 25);
        btnLogout.setText("Logout");
        AdminMainWindow.getContentPane().add(btnLogout);
    }
//-----------------------------------------------------------------------------
    public static void AdminMainWindow_Action()
    {
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) 
                    {
                        AdminMainWindow.setVisible(false);
                        ClientMainWindow.setVisible(true);
                    }
                });
        
        AdminMainWindow.addWindowListener(new java.awt.event.WindowAdapter(){
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent)
                {
                    AdminMainWindow.setVisible(false);
                    ClientMainWindow.setVisible(true);
                }
            });
    }
}


