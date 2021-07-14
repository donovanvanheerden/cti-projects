/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuctionWS;

import javax.jws.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.text.*;

/**
 *
 * @author Donovan van Heerden - EL2014-0043
 */
@WebService(serviceName = "AuctionService")
public class AuctionService {

        /*  Globals   */
    
//------------------------------------------------------------------------------
    
    /**
     * This WebMethod checks to see if the login information passed to the method
     * match the information in the database, if successful, it returns true, otherwise
     * returns false.
     * @param Email user's email address.
     * @param Password user's password.
     * @return true if successful, otherwise false.
     */
    @WebMethod(operationName = "LoginCheck")
    public boolean LoginCheck(@WebParam(name = "Email") String Email, @WebParam(name = "Password") String Password) {
        
        String temp = "";
        String user = Email + "|" + Password;
        try
        {
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");

            Statement st = conn.createStatement();

            ResultSet rec = st.executeQuery("SELECT EmailAddress, Password FROM CustomerInformation WHERE EmailAddress = '" + Email + "' AND Password ='" + Password + "';");
            
            while(rec.next())
            {
                temp = rec.getString(1) + "|" + rec.getString(2);
            }
            
            st.close();
            
            //System.out.println("From User: " + user);
            //System.out.println("From Database: " + temp);
            
            return temp.equals(user);
        }
        catch(SQLException sql)
        {
            System.out.println("SQLException:\n\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return false;
    }

//------------------------------------------------------------------------------
   
    /**
     * This WebMethod checks to see if the user exists already in the database,
     * it checks this using the user's email which gets passed to this method
     * and compared to other email addresses in the database, if it finds that the
     * user exists the method returns true, otherwise false.
     * @param Email user's email address
     * @return true if user found, otherwise false
     */
    @WebMethod(operationName = "UserExistsCheck")
    public boolean UserExistsCheck(@WebParam(name = "Email") String Email) {
        
        try
        {
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");

            Statement st = conn.createStatement();

            ResultSet rec = st.executeQuery("SELECT EmailAddress FROM CustomerInformation WHERE EmailAddress = '" + Email + "';");
            
            boolean check = rec.next();
            
            st.close();
            
            return check;
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch(Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return false;
    }

//------------------------------------------------------------------------------
    
    /**
     * This WebMethod is used to register a new user, by inserting the new user's
     * information into the database, and returns true if the user was registered
     * successfully, otherwise false. 
     * @param fName user's first name
     * @param lName user's last name
     * @param Email user's email address
     * @param Password user's password
     * @param ContactNumber user's contact number
     * @param PostalAddress user's postal address
     * @param SecQuestAns user's security question answer
     * @return true if successful, otherwise false
     */
    @WebMethod(operationName = "RegisterUser")
    public boolean RegisterUser(@WebParam(name = "fName") String fName, @WebParam(name = "lName") String lName, @WebParam(name = "Email") String Email, @WebParam(name = "Password") String Password, @WebParam(name = "ContactNumber") String ContactNumber, @WebParam(name = "PostalAddress") String PostalAddress, @WebParam(name = "SecQuestAns") String SecQuestAns) {
        
        try
        {
            boolean UserCheck = UserExistsCheck(Email);
            int rowsAdded;
            
            //System.out.println("UserCheck Value: " + UserCheck);
            
            if (!UserCheck)
            {
                String data = "jdbc:odbc:AuctionDb";

                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

                Connection conn = DriverManager.getConnection(data, "", "");

                Statement st = conn.createStatement();
                
                //System.out.println("Before SQL Statement");
                
                rowsAdded = st.executeUpdate("INSERT INTO CustomerInformation" +
                        " VALUES('" + fName + "', '" + lName + "','" + Email + "','" + Password + "','" + ContactNumber + "','" + PostalAddress + "','" + SecQuestAns + "')");
                
                //System.out.println("After SQL Statement");
                
                st.close();

                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return false;
    }

//------------------------------------------------------------------------------
    
    /**
     * This WebMethod first checks to see if the user with that email address
     * exists, if a user does exist, it then compares if the security question
     * answer is the same as the answer saved in the database for that user.
     * Then returns true if the user's password was changed successfully, otherwise
     * false.
     * @param Email user's email address
     * @param SecQuestAns user's security question answer
     * @param Password user's new password
     * @return true if successful, otherwise false
     */
    @WebMethod(operationName = "ForgotPassword")
    public boolean ForgotPassword(@WebParam(name = "Email") String Email, @WebParam(name = "SecQuestAns") String SecQuestAns, @WebParam(name = "Password") String Password) {
        
        try
        {
            boolean UserCheck = UserExistsCheck(Email);
            int rowsAdded;
            
            if (UserCheck)
            {
                String temp = "";
                
                String data = "jdbc:odbc:AuctionDb";

                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

                Connection conn = DriverManager.getConnection(data, "", "");

                Statement st = conn.createStatement();
                
                //System.out.println("Before SELECT");

                ResultSet rec = st.executeQuery("SELECT SecQuestAnswer FROM CustomerInformation WHERE EmailAddress = '" + Email + "' AND SecQuestAnswer = '" + SecQuestAns + "';");
                
                //System.out.println("After SELECT");
                
                while(rec.next())
                {
                    temp = rec.getString(1);
                }
                
                //System.out.println("Temp Value: " + temp);
                
                if (SecQuestAns.equals(temp))
                {
                    
                    //System.out.println("Before UPDATE");
                    
                    rowsAdded = st.executeUpdate("UPDATE CustomerInformation SET Password = '" + Password + "' WHERE EmailAddress = '" + Email + "';");
                    
                    //System.out.println("After UPDATE");
                    
                    st.close();
                    
                    return true;
                }
                else
                {
                    st.close();
                    
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch(Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return false;
    }
    
//------------------------------------------------------------------------------
    
    /**
     * This WebMethod, gets the current user's information from the database, this
     * is used to populate the placeholder values in the Profile.jsp page for the
     * input textboxes. It uses the email for that user to fetch the user's information.
     * @param Email user's email address
     * @return returns String value separated with pipes of user's information
     */
    @WebMethod(operationName = "GetUserInfo")
    public String GetUserInfo(@WebParam(name = "Email") String Email) {
        
        try
        {
            String temp = "";
            
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");

            Statement st = conn.createStatement();

            ResultSet rec = st.executeQuery("SELECT * FROM CustomerInformation WHERE EmailAddress = '" + Email + "';");
            
            while(rec.next())
            {
                for (int i = 2; i < 9; i++ )
                {
                    if ((i < 8))
                    {
                        temp += rec.getString(i) + "|";
                    }
                    else
                    {
                        temp += rec.getString(i);
                    }
                }
            }
            
            st.close();
            
            //System.out.println(temp);
            
            return temp;
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return null;
    }
    
//------------------------------------------------------------------------------
    /**
     * This WebMethod is used to update the user's account information.
     * It accepts the users information as one parameter, which is a single string, that is split
     * by pipes, and that user's email address as the second parameter.
     * @param Info user's information separated with pipes
     * @param Email user's email address
     * @return true if successful, otherwise false
     */
    @WebMethod(operationName = "UpdateUserInfo")
    public boolean UpdateUserInfo(@WebParam(name = "Info") String Info,@WebParam(name = "Email") String Email) {
        
        String[] userInfo = Info.split("\\|");
        
        int rowsAdded;
        
        try
        {
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");
            
            //System.out.println(userInfo[4]);

            Statement st = conn.createStatement();
            //System.out.println("BEFORE UPDATE");
            rowsAdded = st.executeUpdate("UPDATE CustomerInformation SET FirstName = '" + userInfo[0] + "', LastName = '" + userInfo[1] + "', [Password] = '" + userInfo[2] 
                                                + "', ContactNumber = '" + userInfo[3] + "', PostalAddress = '" + userInfo[4]
                                                + "', SecQuestAnswer = '" + userInfo[5] + "' WHERE EmailAddress = '" + Email + "';");
            
            st.close();
            //System.out.println("AFTER UPDATE");
            return true;
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return false;
    }

//------------------------------------------------------------------------------
    
    /**
     * This WebMethod is used to return all the items in the database that wasn't
     * added by the current user. It accepts the user's ID as a parameter. The
     * are returned in a String Array, with each element's value separated with pipes.
     * @param CustomerID user's ID
     * @return returns String Array of products
     */
    @WebMethod(operationName = "BrowseItems")
    public String[] BrowseItems(@WebParam(name = "CustomerID") String CustomerID) {
        
        try
        {
            ArrayList<String> tempArr = new ArrayList<>();
            
            java.util.Date today;
            today = new java.util.Date();
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            
            String date = cal.get(Calendar.YEAR) + "";
            
            if (cal.get(Calendar.MONTH) < 9)
            {
                date += "-0" + (cal.get(Calendar.MONTH) + 1);
            }
            else
            {
                date += "-" + (cal.get(Calendar.MONTH) + 1);
            }
            
            if (cal.get(Calendar.DAY_OF_MONTH) < 10)
            {
                date += "-0" + cal.get(Calendar.DAY_OF_MONTH);
            }
            else
            {
                date += "-" + cal.get(Calendar.DAY_OF_MONTH);
            }
            
            //System.out.println(date);
            
            String temp = "";
                
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");

            Statement st = conn.createStatement();
            
            //System.out.println("BEFORE SELECT");
            
            ResultSet rec = st.executeQuery("SELECT * FROM ProductInformation WHERE EndDate > '" + date + "' AND OwnerID <> '" + CustomerID + "';");
            
            //System.out.println("AFTER SELECT");
            
            while(rec.next())
            {
//                ProductBean prod = new ProductBean();
//                prod.setName(rec.getString("ProdName"));
//                prod.setDesc(rec.getString("ProdDesc"));
//                prod.setCurrBid(rec.getString("CurrentBid"));
//                prod.setEndDate(rec.getString("EndDate"));
                
                tempArr.add(rec.getString("ProductID") + "|" + rec.getString("ProdName") + "|" + rec.getString("ProdDesc") + "|" + rec.getString("CurrentBid") + "|" + rec.getString("EndDate"));
            }
            
            st.close();
            
            int rows = tempArr.size();
            
            //System.out.println(rows);
            
            String[] prodArr = new String[rows];
            
            for (int x = 0; x < rows; x++)
            {
                prodArr[x] = tempArr.get(x);
                //System.out.println(tempArr.get(x));
            }
            
            return prodArr;
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return null;
    }
    
//------------------------------------------------------------------------------
    
    /**
     * This WebMethod is used to retrieve information about a specific product, by
     * passing that products ID as a parameter. The products information gets returned
     * in a single string, which contains pipes for separating information about
     * that product.
     * @param ProductID product ID
     * @return String of the specified products information separated with pipes
     */
    @WebMethod(operationName = "GetProdInfo")
    public String GetProdInfo(@WebParam(name = "ProductID") String ProductID) {
        
        try
        {
            String temp = "";
            
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");

            Statement st = conn.createStatement();

            ResultSet rec = st.executeQuery("SELECT ProdName, ProdDesc, CurrentBid, EndDate, FirstName, LastName FROM ProductInformation, CustomerInformation WHERE ProductInformation.OwnerID = CustomerInformation.CustomerID AND ProductID = '" + ProductID + "'");
            
            while(rec.next())
            {
                for (int x = 1; x < 7; x++)
                {
                    if (x < 6)
                    {
                        temp += rec.getString(x) + "|";
                    }
                    else
                    {
                        temp += rec.getString(x);
                    }
                }
            }
            
            st.close();
            
            return temp;
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return null;
    }
    
//------------------------------------------------------------------------------

    /**
     * This WebMethod is used to retrieve all items which the user has successfully 
     * purchased, by sending the user's ID as a parameter, and returning a String
     * Array with each elements value separated with pipes.
     * @param BuyerID user's ID
     * @return String array of purchased products
     */
    @WebMethod(operationName = "getPurchasedItems")
    public String[] getPurchasedItems(@WebParam(name = "BuyerID") String BuyerID) {
        
        try
        {
            ArrayList<String> tempArr = new ArrayList<>();
            
            java.util.Date today;
            today = new java.util.Date();
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            
            String date = cal.get(Calendar.YEAR) + "";
            
            if (cal.get(Calendar.MONTH) < 9)
            {
                date += "-0" + (cal.get(Calendar.MONTH) + 1);
            }
            else
            {
                date += "-" + (cal.get(Calendar.MONTH) + 1);
            }
            
            if (cal.get(Calendar.DAY_OF_MONTH) < 10)
            {
                date += "-0" + cal.get(Calendar.DAY_OF_MONTH);
            }
            else
            {
                date += "-" + cal.get(Calendar.DAY_OF_MONTH);
            }
            
            //System.out.println(date);
            
            String temp = "";
                
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");

            Statement st = conn.createStatement();
            
            //System.out.println("BEFORE SELECT");

            ResultSet rec = st.executeQuery("SELECT * FROM ProductInformation,BidInformation WHERE ProductInformation.ProductID = BidInformation.ProductID AND ProductInformation.EndDate < '" + date + "' AND  BidInformation.CustomerID = '" + BuyerID + "' AND BidInformation.BidValue = ProductInformation.CurrentBid");
            
            while(rec.next())
            {
                temp = rec.getString("ProdName") + "|" + rec.getString("ProdDesc") + "|" + rec.getString("CurrentBid") + "|" + rec.getString("EndDate");
                tempArr.add(temp);
            }
            
            //System.out.println("AFTER SELECT");
            
            st.close();
            
            int rows = tempArr.size();
            
            String[] arrProd = new String[rows];
            
            for(int x = 0; x < rows; x++)
            {
                arrProd[x] = tempArr.get(x);
            }
            
            return arrProd;
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return null;
    }
    
//------------------------------------------------------------------------------

    /**
     * This WebMethod simply accepts the user's email address as a parameter,
     * and returns the user's ID as a String.
     * @param Email user's email address
     * @return String value of user's ID
     */
    @WebMethod(operationName = "getCustomerID")
    public String getCustomerID(@WebParam(name = "Email") String Email) {
        
        try
        {
            String temp = "";
            
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");

            Statement st = conn.createStatement();

            ResultSet rec = st.executeQuery("SELECT CustomerID FROM CustomerInformation WHERE EmailAddress = '" + Email + "';");
            
            while(rec.next())
            {
                temp = rec.getString(1);
            }
            
            st.close();
            
            return temp;
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return null;
    }
    
//------------------------------------------------------------------------------

    /**
     * This WebMethod is used to retrieve the items which the current user has
     * added to the database, it accepts the user's ID as a parameter, and returns
     * a String Array, with each elements value separated with pipes
     * @param OwnerID user's ID
     * @return String Array of items listed by user
     */
    @WebMethod(operationName = "getListedItems")
    public String[] getListedItems(@WebParam(name = "OwnerID") String OwnerID) {
        
        try
        {
            ArrayList<String> tempArr = new ArrayList<>();
            
            String temp = "";
            
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");

            Statement st = conn.createStatement();

            ResultSet rec = st.executeQuery("SELECT ProdName, ProdDesc, CurrentBid, EndDate FROM ProductInformation WHERE OwnerID = '" + OwnerID + "'");
            
            while(rec.next())
            {
                temp = rec.getString(1) + "|" + rec.getString(2) + "|" + rec.getString(3) + "|" + rec.getString(4);
                
                tempArr.add(temp);
            }
            
            st.close();
            
            int rows = tempArr.size();
            
            String[] arrProd = new String[rows];
            
            for(int x = 0; x < rows; x++)
            {
                arrProd[x] = tempArr.get(x);
            }
            
            return arrProd;
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return null;
    }
    
//------------------------------------------------------------------------------

    /**
     * This WebMethod is used to add a product to the database, by accepting a 
     * single String of that products information separated with pipes. And returning
     * true if the product was added successfully, otherwise false.
     * @param ProductInfo product information separated with pipes
     * @return true if successful, otherwise false
     */
    @WebMethod(operationName = "AddProduct")
    public boolean AddProduct(@WebParam(name = "ProductInfo") String ProductInfo) {
        
        String[] prodInfo = ProductInfo.split("\\|");
        int rowsAdded;
        
        try
        {
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");

            Statement st = conn.createStatement();
            
            for (int x = 0; x < prodInfo.length; x++)
            {
                System.out.println(prodInfo[x]);
            }
            
            //System.out.println("BEFORE INSERT");

            rowsAdded = st.executeUpdate("INSERT INTO ProductInformation(ProdName, ProdDesc, StartBid, CurrentBid, EndDate, OwnerID) VALUES('" + prodInfo[0] + 
                                                "','" + prodInfo[1] + "','" + prodInfo[2] + "','" + prodInfo[3] + 
                                                "','" + prodInfo[4] + "'," + prodInfo[5] + ")");
            
            //System.out.println("AFTER INSERT");
            
            st.close();
            
            return true;
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return false;
    }
    
//------------------------------------------------------------------------------

    /**
     * This WebMethod is used to bid on a product, by accepting the user's ID,
     * the products ID and the user's bid as parameters, and then writes this information
     * to a logfile called AHBidLog.txt in your My Documents. Returns true if the bid
     * was successful otherwise false.
     * @param CustomerID user's ID
     * @param ProductID product's ID
     * @param BidValue user's bid amount
     * @return true if successful, otherwise false
     */
    @WebMethod(operationName = "ProductBid")
    public boolean ProductBid(@WebParam(name = "CustomerID") String CustomerID, @WebParam(name = "ProductID") String ProductID, @WebParam(name = "BidValue") double BidValue) {
        
        int rowsAdded;
        double tempVal = 0;
        
        System.out.println(System.getProperty("user.home") + File.separatorChar + "My Documents");
        
        try
        {
            String data = "jdbc:odbc:AuctionDb";

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            Connection conn = DriverManager.getConnection(data, "", "");

            Statement st = conn.createStatement();

            //System.out.println("BEFORE INSERT");
            
            ResultSet rs = st.executeQuery("SELECT CurrentBid FROM ProductInformation WHERE ProductID = '" + ProductID +"'");
            
            while(rs.next())
            {
                tempVal = rs.getDouble(1);
            }
            
            //System.out.println(tempVal);
            
            //System.out.println("About to check values");
            
            if (tempVal >= BidValue)
            {
                return false;
            }
            
            //System.out.println("About to Insert");
            
            rowsAdded = st.executeUpdate("INSERT INTO BidInformation VALUES('" + CustomerID + "', '" + ProductID + "', '" + BidValue + "');");
            
            //System.out.println("AFTER INSERT");
            
            //System.out.println("BEFORE UPDATE");
            
            rowsAdded = st.executeUpdate("UPDATE ProductInformation SET CurrentBid = '" + BidValue + "' WHERE ProductID = '" + ProductID + "'");
            
            //System.out.println("AFTER UPDATE");
            
            st.close();
            
            File BidLog = new File(System.getProperty("user.home") + File.separatorChar + "My Documents" + File.separatorChar + "AHBidLog.txt");
            
            //System.out.println(System.getProperty("user.home"));
            
            if (BidLog.exists())
            {
                //System.out.println("About to Write to file.");
                try 
                {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(BidLog,true)),true);
                    //System.out.println("Writing to File...");
                    out.println("Customer ID: " + CustomerID + ", ProductID: " + ProductID + ", BidValue: R" + BidValue);
                    //System.out.println("Written to file.");
                    out.flush();
                    out.close();
                }
                catch(IOException iox)
                {
                    System.out.println(iox);
                }
            }
            else
            {
                try
                {
                    //System.out.println("About to Create file and Write to file.");
                    BidLog.createNewFile();
                    try
                    {
                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(BidLog,true)),true);
                            //System.out.println("Writing to File...");
                            out.println("Customer ID: " + CustomerID + ", ProductID: " + ProductID + ", BidValue: R" + BidValue);
                            //System.out.println("Written to file.");
                            out.close();
                    }
                    catch(IOException iox)
                    {
                        System.out.println(iox);
                    }
                }
                catch(IOException io)
                {
                    System.out.println(io);
                }
                catch(Exception x)
                {
                    System.out.println(x);
                }
                        
            }
            
            return true;
        }
        catch(SQLException sql)
        {
            System.out.print("SQLException:\n\n" + sql);
        }
        catch (Exception x)
        {
            System.out.println("Some Exception:\n\n" + x);
        }
        
        return false;
    }
    
//------------------------------------------------------------------------------

    /**
     * This WebMethod is used to get the server's date, as the user may be from
     * a different time-zone.
     * @return java.util.Date value
     */
    @WebMethod(operationName = "getDate")
    public java.util.Date getDate() {
        
        java.util.Date dt = new java.util.Date();
        
        return dt;
    }
}
