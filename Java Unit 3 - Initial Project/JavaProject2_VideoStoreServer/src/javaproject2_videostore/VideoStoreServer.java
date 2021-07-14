/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject2_videostore;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.text.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class VideoStoreServer implements Runnable, Serializable
{
    //Globals
    Socket SOCK;
    private Scanner INPUT;
    private ObjectOutputStream OUT;
    private ArrayList<String[]> MovieList = new ArrayList<String[]>();
//-----------------------------------------------------------------------------
    public VideoStoreServer(Socket X)
    {
        this.SOCK = X;
    }
//-----------------------------------------------------------------------------
    public void run()
    {
        try
        {
            try
            {
                try
                {
                    
                    INPUT = new Scanner(SOCK.getInputStream());
                    OUT = new ObjectOutputStream(SOCK.getOutputStream());
                    
                    String COMMAND;
                    
                    while (true)
                    {
                        if (!INPUT.hasNext())
                        {
                            return;
                        }
                        else
                        {
                            COMMAND = INPUT.nextLine();
                            if (COMMAND.contains("#Query:"))
                            {
                                String data = "jdbc:odbc:VideoStoreDb";
                                String temp = COMMAND.substring(COMMAND.indexOf(":")+1, COMMAND.length());
                                
                                VideoStoreServerGUI.Console.append("\nUser Searched: "+temp);
                                
                                try
                                {
                                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
                                    Connection conn = DriverManager.getConnection(data);

                                    Statement st = conn.createStatement();

                                    ResultSet rec = st.executeQuery("SELECT movie_name,movie_description,genre_name FROM Movie,Genre WHERE Movie.genre_id = Genre.genre_id AND movie_name LIKE '%" + temp + "%'");
                                    
                                    VideoStoreServerGUI.Console.append("\n");
                                    while(rec.next())
                                    {
                                        String[] tempArr = new String[3];
                                        //VideoStoreServerGUI.Console.append("\n"+rec.getString(1) + " - " + rec.getString(2).substring(0,20)+ "... " + ": "+rec.getString(3));
                                        //VideoStoreServerGUI.Console.setCaretPosition(VideoStoreServerGUI.Console.getDocument().getLength());
                                        tempArr[0] = rec.getString(1); tempArr[1] = rec.getString(2); tempArr[2] = rec.getString(3);
                                        MovieList.add(tempArr);
                                    }
                                    
                                    st.close();
                                    
                                    VideoStoreServerGUI.Console.append("\n\nArrayList Created!\n");
                                    VideoStoreServerGUI.Console.setCaretPosition(VideoStoreServerGUI.Console.getDocument().getLength());
                                    
                                    OUT.writeObject(MovieList);
                                    OUT.flush();
                                    
                                    VideoStoreServerGUI.Console.append("\n\nArrayList SENT!\n");
                                    
                                }
                                catch(SQLException sql)
                                {
                                    System.out.println("SQL Error: " + sql);
                                }
                                catch (Exception X)
                                {
                                    System.out.println(X);
                                }
                            }
                        }

                    }   //Close while loop
                }
                finally
                {
                    int x = VideoStoreServerGUI.ConnectionArray.indexOf(SOCK);
                    if (x == -1)
                    {
                        return;
                    }
                    //VideoStoreServerGUI.Console.append("\n" + x);
                    VideoStoreServerGUI.Console.append("\n" + VideoStoreServerGUI.CurrentUsers.get(x) + " has disconnected.\n");
                    VideoStoreServerGUI.Console.setCaretPosition(VideoStoreServerGUI.Console.getDocument().getLength());
                    VideoStoreServerGUI.CurrentUsers.remove(x);
                    VideoStoreServerGUI.ConnectionArray.remove(x);
                    SOCK.close();
                    
                }
            }
            catch(Exception X)
            {
                //System.out.println("Something went wrong...");
                System.out.println(X.toString());
            }
        }
        catch (Exception X)
        {
            System.out.println(X);
        }
    }
}
