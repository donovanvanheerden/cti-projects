/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airtrafficcontrolsystem_question3;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Donovan van Heerden
 * The Air Traffic Control System GUI
 */
public class AirTrafficGUI {
    
    private static final String[] COLUMN_HEADERS = {"Flight Name", "Priority"};
    
    JFrame MainWindow = new JFrame("Air Traffic Control");
    JPanel pnlMain = new JPanel();
    JPanel pnlRemove = new JPanel();
    
    JLabel lblFlightName = new JLabel("Flight Name:");
    JLabel lblLandingPriority = new JLabel("Priority:");
    JLabel lblTakeoff = new JLabel("Taking-Off Flights");
    JLabel lblLanding = new JLabel("Landing Flights");
    JLabel lblAddFlight = new JLabel("Remove Flight:");
    JLabel lblRemoveFlight = new JLabel("Remove Flight:");
    
    JTextField txtFlightName = new JTextField();
    JComboBox cbxPriority = new JComboBox(); 
    JComboBox cbxAddFlight = new JComboBox();
    JComboBox cbxRemoveFlight = new JComboBox();
    
    JButton btnAddFlight = new JButton("Add Flight");
    JButton btnRemoveFlight = new JButton("Remove Flight");
    
    JScrollPane spLandingFlights = new JScrollPane();
    JScrollPane spTakeoffFlights = new JScrollPane();
    JTable dtLandingFlights = new JTable();
    JTable dtTakeoffFlights = new JTable();
    
    private final Queue<Flight> landingQueue;
    private final Queue<Flight> takeoffQueue;
    
    Comparator<Flight> comparer = new FlightComparer();
    
    /**
     * Creates a new instance of the Air Traffic Control System GUI
     */
    public AirTrafficGUI() {
        
        this.landingQueue = new PriorityQueue<>(comparer);
        this.takeoffQueue = new PriorityQueue<>(comparer);
        
        ConfigureMainWindow();
        MainWindow_Action();
        setPriorityCbx();
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
        
        pnlMain.setBounds(10, 10, 250, 220);
        pnlMain.setLayout(null);
        pnlMain.setBorder(BorderFactory.createEtchedBorder());
        pnlRemove.setBounds(10, 220, 250, 110);
        pnlRemove.setLayout(null);
        pnlRemove.setBorder(BorderFactory.createEtchedBorder());
        
        MainWindow.add(pnlMain);
        MainWindow.add(pnlRemove);
        
        lblFlightName.setBounds(10, 10, 150, 25);
        lblLandingPriority.setBounds(10, 40, 150, 25);
        lblLanding.setBounds(pnlMain.getSize().width+15, 10, 340, 25);
        lblTakeoff.setBounds(pnlMain.getSize().width+15, 170, 340, 25);
        lblAddFlight.setBounds(10, 70, 150, 25);
        lblRemoveFlight.setBounds(10, 20, 150, 25);
        
        txtFlightName.setBounds(85, 10, 150, 25);
        cbxPriority.setBounds(110, 40, 125, 25);
        cbxAddFlight.setBounds(110, 70, 125, 25);
        cbxRemoveFlight.setBounds(110, 20, 125, 25);
        
        btnAddFlight.setBounds(50, 150, 140, 25);
        btnRemoveFlight.setBounds(50, 70, 140, 25);

        pnlMain.add(lblFlightName);
        pnlMain.add(lblLandingPriority);
        pnlMain.add(lblAddFlight);
        MainWindow.add(lblLanding);
        MainWindow.add(lblTakeoff);
        pnlRemove.add(lblRemoveFlight);
        
        pnlMain.add(txtFlightName);
        pnlMain.add(cbxPriority);
        pnlMain.add(cbxAddFlight);
        pnlRemove.add(cbxRemoveFlight);
        
        pnlMain.add(btnAddFlight);
        pnlRemove.add(btnRemoveFlight);
        
        spLandingFlights.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spLandingFlights.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spLandingFlights.getViewport().add(dtLandingFlights, null);
        MainWindow.add(spLandingFlights);
        spLandingFlights.setBounds(pnlMain.getSize().width+15, 35, 340, 135);
        
        spTakeoffFlights.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spTakeoffFlights.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spTakeoffFlights.getViewport().add(dtTakeoffFlights, null);
        MainWindow.add(spTakeoffFlights);
        spTakeoffFlights.setBounds(pnlMain.getSize().width+15, 195, 340, 135);
        
        dtTakeoffFlights.setFont(new java.awt.Font("Tahoma", 0, 12));
        dtTakeoffFlights.setForeground(new java.awt.Color(0, 0, 0));
        
        dtLandingFlights.setFont(new java.awt.Font("Tahoma", 0, 12));
        dtLandingFlights.setForeground(new java.awt.Color(0, 0, 0));
        
        MainWindow.setVisible(true);
    }
    
    /**
     * Adds event listeners to the buttons on the JFrame
     */
    private void MainWindow_Action()
    {
        try
        {
            btnAddFlight.addActionListener((java.awt.event.ActionEvent evt) -> {
                String name = txtFlightName.getText().trim();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Flight Name cannot be blank", "Error Adding Flight", 0);
                    return;
                }
                
                if (((FlightType)cbxAddFlight.getSelectedItem()).getValue() == 1) {
                    addFlightForLanding(new Flight(name, (FlightPriority)cbxPriority.getSelectedItem()));
                } else {
                    addFlightForTakeoff(new Flight(name, (FlightPriority)cbxPriority.getSelectedItem()));
                }
                
                txtFlightName.setText("");
            });
            
            btnRemoveFlight.addActionListener((java.awt.event.ActionEvent evt) -> {
                if (((FlightType)cbxRemoveFlight.getSelectedItem()).getValue() == 1) {
                    if (landingQueue.size() > 0) {
                        removeFlightForLanding();
                    } else {
                        JOptionPane.showMessageDialog(null, "There are no landing flights to remove.", "Error Removing Flight", 0);
                    }
                } else {
                    if (takeoffQueue.size() > 0) {
                        removeFlightForTakeoff();
                    } else {
                        JOptionPane.showMessageDialog(null, "There are no take-off flights to remove.", "Error Removing Flight", 0);
                    }
                }
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
     * sets the values for the 3 checkboxes used
     */
    void setPriorityCbx() {
        cbxPriority.addItem(new FlightPriority("Low", 2));
        cbxPriority.addItem(new FlightPriority("High", 1));
        
        cbxAddFlight.addItem(new FlightType("Landing", 1));
        cbxAddFlight.addItem(new FlightType("Take-Off", 2));
        
        cbxRemoveFlight.addItem(new FlightType("Landing", 1));
        cbxRemoveFlight.addItem(new FlightType("Take-Off", 2));
    }
    
    /**
     * Adds Landing Type flights
     * @param flight instance of the Flight class
     */
    void addFlightForLanding(Flight flight) {
        landingQueue.add(flight);
        loadLandingFlights();
    }
    
    /**
     * Adds Takeoff Type flights
     * @param flight instance of the Flight class
     */
    void addFlightForTakeoff(Flight flight) {
        takeoffQueue.add(flight);
        loadTakeoffFlights();
    }
    
    /**
     * Removes Landing Type flights
     */
    void removeFlightForLanding() {
        landingQueue.remove();
        loadLandingFlights();
    }
    
    /**
     * Removes Takeoff Type flights
     */
    void removeFlightForTakeoff() {
        takeoffQueue.remove();
        loadTakeoffFlights();
    }
    
    /**
     * Shows the Landing Type flights in the JTable
     */
    void loadLandingFlights() {
        int rowCount = landingQueue.size();
        Queue<Flight> temp = new PriorityQueue<>(comparer);
        temp.addAll(landingQueue);
        
        String[][] flights = new String[rowCount][2];
        int index = 0;
        while(temp.size() > 0) {
            Flight flight = temp.remove();
            flights[index] = new String[] {flight.flightName, flight.priority.toString()};
            index++;
        }
        
        dtLandingFlights =  new JTable(flights, COLUMN_HEADERS);
        spLandingFlights.getViewport().add(dtLandingFlights);
        spLandingFlights.setViewportView(dtLandingFlights);
    }
    
    /**
     * Shows the Takeoff Type flights in the JTable
     */
    void loadTakeoffFlights() {
        int rowCount = takeoffQueue.size();
        Queue<Flight> temp = new PriorityQueue<>(comparer);
        temp.addAll(takeoffQueue);
        
        String[][] flights = new String[rowCount][2];
        int index = 0;
        while(temp.size() > 0) {
            Flight flight = temp.remove();
            flights[index] = new String[] {flight.flightName, flight.priority.toString()};
            index++;
        }
        
        dtTakeoffFlights =  new JTable(flights, COLUMN_HEADERS);
        spTakeoffFlights.getViewport().add(dtTakeoffFlights);
        spTakeoffFlights.setViewportView(dtTakeoffFlights);
    }

    /**
     * Handles the JFrame closing event, prompting the user.
     * @param frame the current JFrame
     */
    public static void ACTION_CLOSE(JFrame frame)
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
