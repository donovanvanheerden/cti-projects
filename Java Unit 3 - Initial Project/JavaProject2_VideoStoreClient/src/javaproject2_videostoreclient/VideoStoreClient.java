/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject2_videostoreclient;
import java.net.*;
import java.io.*;
import java.util.*;
import static javaproject2_videostoreclient.VideoStoreClientGUI.ClientMainWindow;
import javax.swing.*;

public class VideoStoreClient implements Runnable
{
    //Globals
    Socket SOCK;
    Scanner INPUT;
    ObjectInputStream objInput;
    Scanner SEND = new Scanner(System.in);
    PrintWriter OUT;
    ArrayList<String[]> MovieList;
//-----------------------------------------------------------------------------
    public VideoStoreClient(Socket X)
    {
        this.SOCK = X;
    }
//--------------------------------------------------------------------------------
    public void run()
    {
        try
        {
            try
            {
                INPUT = new Scanner(SOCK.getInputStream());
                objInput = new ObjectInputStream(SOCK.getInputStream());
                OUT = new PrintWriter(SOCK.getOutputStream());
                OUT.flush();
                
                while (true)
                {
                    if (INPUT.hasNext())
                    {
                        System.out.println("INPUT HAS NEXT!");
//                        Object objTemp = objInput.readObject();
//                        System.out.println(objTemp);
                        String temp = INPUT.nextLine();
                        System.out.println(temp);
                        if (temp.equals("!Disconnect"))
                        {
                            System.out.println("you were disconnected!");
                            if (JOptionPane.showConfirmDialog(ClientMainWindow,"You were disconnected.\nWould you like to reconnect?",
                                    "Alert: You were disconnected!", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                            {
                                VideoStoreClientGUI.Connect(VideoStoreClientGUI.HostName);
                            }
                            else
                            {
                                VideoStoreClientGUI.DisableControls();
                            }
                        }
                        else if (temp.equals("!ServerClosed"))
                        {
                            System.out.println("you were disconnected!");
                            if (JOptionPane.showConfirmDialog(ClientMainWindow,"Server shutdown.\nWould you like to reconnect?",
                                    "Alert: Server Shutdown!", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                            {
                                VideoStoreClientGUI.Connect(VideoStoreClientGUI.HostName);
                            }
                            else
                            {
                                VideoStoreClientGUI.DisableControls();
                            }
                        }
                        else
                        {
//                            System.out.println("In Else");
//                            
//                            System.out.println("Made OBJECT");
//                            MovieList = (ArrayList<String[]>)objTemp;
//                            System.out.println("Made ARRAYLIST");
//                            String[] colNames = {"Movie Name", "Description", "Genre"};
//                            System.out.println("Made STRING[]");
//                            int itemCount = MovieList.size() * 3;
//                            System.out.println("Made INT COUNT");
//                            String[][] MovieArr = new String[itemCount][3];
//                            System.out.println("Made 2D Array");
//                            for (int i = 0; i < MovieList.size(); i++)
//                            {
//                                String[] tempMovInfo = MovieList.get(i);
//                                for (int j = 0; j < tempMovInfo.length; j++)
//                                {
//                                    MovieArr[i][j] = tempMovInfo[j];
//                                }
//                            }
//                            System.out.println("Made ADDING TO TABLE");
//                            VideoStoreClientGUI.dtResults = new JTable(MovieArr, colNames);
//                            VideoStoreClientGUI.spResults.getViewport().add(VideoStoreClientGUI.dtResults);
                        }
                    }
                }
            }
            catch(Exception Y)
            {
                System.out.print(Y);
            }
            finally
            {
                OUT.println("Disconnecting...");
                SOCK.close();
            }
        }
        catch (Exception X)
        {
            System.out.print(X);
        }
    }
//-----------------------------------------------------------------------------
    public void SendQuery(String Query)
    {
        OUT.println("#Query:" + Query);
        OUT.flush();
        VideoStoreClientGUI.txtSearch.setText("");
    }
//-----------------------------------------------------------------------------
    public void DISCONNECT()
    {
        try
        {
            SOCK.close();
            JOptionPane.showMessageDialog(null, "You disconnected!");
            VideoStoreClientGUI.DisableControls();
        }
        catch(IOException io)
        {
            System.out.println(io);
        }
    }
}
