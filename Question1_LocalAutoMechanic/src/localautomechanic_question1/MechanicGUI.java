/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localautomechanic_question1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author DocMad
 * 
 * GUI for the Local Auto Mechanic System
 */
public class MechanicGUI {
    
    JFrame MainWindow = new JFrame("Local Auto Mechanic");
    JPanel pnlMain = new JPanel();
    
    JLabel lblName = new JLabel("Client Name:");
    JLabel lblRegNo = new JLabel("Reg No:");
    JLabel lblServiceStatus;
    
    JTextField txtName = new JTextField();
    JTextField txtRegNo = new JTextField(); 
    
    JButton btnAddCar = new JButton("Add Car");;
    JButton btnRelease = new JButton("Release Car");;
    
    JTable dtCarService = new JTable();;
    JScrollPane spCarService = new JScrollPane();;
    
    private final Queue<CarService> service_queue;
    
    private final Integer MAX_CARS;
    private static final String[] COLUMN_HEADERS = {"Name", "Registration Number"};
    
    /**
     * Creates a new instance of the Local Auto Mechanic System.
     * @param max_cars maximum cars allowed
     */
    public MechanicGUI(int max_cars) {
        this.service_queue = new LinkedList<>();
        this.MAX_CARS = max_cars;
        
        lblServiceStatus = new JLabel("Free Mechanics: " + MAX_CARS + "/" + MAX_CARS);
        
        ConfigureMainWindow();
        MainWindow_Action();
    }
    
    /**
     * Configures the GUI and sets up the layout of each component.
     */
    private void ConfigureMainWindow()
    {
        MainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        MainWindow.setResizable(false);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        MainWindow.setBackground(new java.awt.Color(255, 255, 255));
        MainWindow.setSize(620, 370);
        int width = dim.width / 2 - MainWindow.getSize().width / 2;
        int height = dim.height / 2 - MainWindow.getSize().height / 2;
        MainWindow.setLocation(width, height);
        MainWindow.getContentPane().setLayout(null);
        
        pnlMain.setBounds(10, 10, 250, 320);
        pnlMain.setLayout(null);
        pnlMain.setBorder(BorderFactory.createEtchedBorder());
        MainWindow.add(pnlMain);
        
        lblName.setBounds(10, 10, 150, 25);
        lblRegNo.setBounds(10, 35, 150, 25);
        lblServiceStatus.setBounds(pnlMain.getSize().width + 15, 10, 150, 25);

        txtName.setBounds(85, 10, 150, 25);
        txtRegNo.setBounds(85, 35, 150, 25);
        
        btnAddCar.setBounds(60, 220, 130, 25);
        btnRelease.setBounds(60, 250, 130, 25);

        pnlMain.add(lblName);
        pnlMain.add(lblRegNo);
        MainWindow.add(lblServiceStatus);
        
        pnlMain.add(txtName);
        pnlMain.add(txtRegNo);
        
        pnlMain.add(btnAddCar);
        pnlMain.add(btnRelease);
        
        spCarService.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spCarService.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spCarService.getViewport().add(dtCarService, null);
        MainWindow.add(spCarService);
        spCarService.setBounds(pnlMain.getSize().width+15, 35, 340, 295);
        
        dtCarService.setFont(new java.awt.Font("Tahoma", 0, 12));
        dtCarService.setForeground(new java.awt.Color(0, 0, 0));
        
        MainWindow.setVisible(true);
    }
    /**
     * Adds event listeners to the buttons on the JFrame
     */
    private void MainWindow_Action()
    {
        try
        {
            btnAddCar.addActionListener((java.awt.event.ActionEvent evt) -> {
                if (txtName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a name.", "Failed to Add Car", 0);
                    return;
                }
                
                if (txtRegNo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a registration number.", "Failed to Add Car", 0);
                    return;
                }
                
                if (!RegNoExists(service_queue)) {
                    addCar(new CarService(txtRegNo.getText(), txtName.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "A car with this registration number has already been added", "Failed to Add Car", 0);
                }
            });
            
            btnRelease.addActionListener((java.awt.event.ActionEvent evt) -> {
                removeCar();
            });
            
            MainWindow.addWindowListener(new java.awt.event.WindowAdapter(){
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent)
                {
                    ACTION_CLOSE(MainWindow);
                }
            });
        }
        catch(Exception X)
        {
            System.out.println(X);
        }         
    }
    
    /**
     * Adds a new Car for service.
     * @param service CarService instance.
     */
    void addCar(CarService service) {
        if (!(service_queue.size() == MAX_CARS)) {
            service_queue.add(service);
            loadCarServices();

            txtName.setText("");
            txtRegNo.setText("");
        }
        else {
            JOptionPane.showMessageDialog(
                null, "Reached car max capacity.", "No Free Mechanics", 1);
        }
    }
     
    /**
     *  Removes the first car from service.
     */
    void removeCar() {
        if (!service_queue.isEmpty()) {
            service_queue.poll();
            loadCarServices();
        } else {
            JOptionPane.showMessageDialog(
                null, "There are no cars to be released", "Error Releasing Cars", 0);
        }
    }
    
    /**
     * Displays the cars currently being serviced in a JTable.
     */
    void loadCarServices() {
        int total_cars = (MAX_CARS - service_queue.size());
        lblServiceStatus.setText("Free Mechanics: " + total_cars + "/" + MAX_CARS);
        int rowCount = service_queue.size();
        
        Iterator it = service_queue.iterator();
        String[][] cars = new String[rowCount][2];
        
        int index = 0;
        while(it.hasNext()) {
            CarService car = (CarService)it.next();
            cars[index] = new String[] { car.client_name, car.registration_number};
            index++;
        }
        
        dtCarService =  new JTable(cars, COLUMN_HEADERS);
        spCarService.getViewport().add(dtCarService);
        spCarService.setViewportView(dtCarService);
    }
    
    /**
     * Checks if the registration number entered exists already in the list of 
     * cars to be serviced.
     * 
     * @param myList List of CarService indicating the cars currently being serviced.
     * @return True if registration_number exists within the list provided.
     */
    boolean RegNoExists(Queue<CarService> myList) {
        for (CarService car : myList) {
            if (car.registration_number.equals(txtRegNo.getText())) {
                return true;
            } else {
            }
        }
        return false;
    }
    
    /**
     * Handles the JFrame closing event, prompting the user.
     * @param frame the current JFrame
     */
    static void ACTION_CLOSE(JFrame frame)
    {
        String[] closingOptions = {"Minimize", "Exit", "Cancel"};
        int response = JOptionPane.showOptionDialog(null,
             "Are you sure you want to exit?", "Exit?", 0,
                 JOptionPane.WARNING_MESSAGE, null, closingOptions, closingOptions[1]);

        if (response == 0)
        {
            frame.setState(JFrame.ICONIFIED);
        }
        else if (response == 1)
        {
            System.exit(0);
        }
    }
}
