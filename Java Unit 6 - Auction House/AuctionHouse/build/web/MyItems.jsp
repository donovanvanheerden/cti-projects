<%-- 
    Document   : MyItems
    Created on : 02 Mar 2015, 11:45:04 AM
    Author     : Donovan van Heerden - EL2014-0043
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, AuctionWS.*, java.text.*" %>
<%@ page errorPage="AuctionErrorPage.jsp" %>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta content="text/html; charset=UTF-8"/>
        <title>My Items</title>
        <link rel="SHORTCUT ICON" href="images/top_hat.png"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            try
            {
                /*  Fetching information from the logged cookie */
                Cookie[] cookies = request.getCookies();

                Cookie check = null;

                if (cookies != null)
                {
                    for (int x = 0; x < cookies.length; x++) 
                    { 
                        /* look for specific cookie by name */ 
                        if (!cookies[x].getValue().equals("") && cookies[x].getName().equals("logged")) 
                        { 
                            check = cookies[x];

                        }
                    }
                }
                
                /*  Checks if the user is logged in, if not, the user is then redirected to the login.jsp page */
                if (session.getAttribute("login").equals("") || !session.getAttribute("login").equals(true))
                {
                    response.sendRedirect("Login.jsp");
                }
                
                AuctionService auc = new AuctionService();
                
                /*  gets the users ID   */
                String ownerID = auc.getCustomerID(check.getValue());
                
                /*  populates the arrListed String[] with all items the user has listed */
                String[] arrListed = auc.getListedItems(ownerID);
                
                /*  populates the arrPurchased String[] with all items the user has purchased */
                String[] arrPurchased = auc.getPurchasedItems(ownerID);
                
                //System.out.println("Listed Items");
                //for (int x = 0; x < arrListed.length; x++)
                //{
                //    System.out.println("Index " + x + ": " + arrListed[x]);
                //}
                
                //System.out.println("Purchased Items");
                //for(int y = 0; y < arrPurchased.length; y++)
                //{
                //    System.out.println("Index " + y + ": " + arrPurchased[y]);
                //}
                
                /*  
                 *  defaults the prodName, prodDesc, prodEndDate and prodBid to null so it can be referenced below
                 *  in the input tags.
                 */
                String prodName = null, prodDesc = null, prodEndDate = null, prodBid = null;
                
                /*  boolean variable used to check if the temp variables should be loaded into the 
                 *  various input textboxes or not.
                 */
                boolean success = false;
                if (request.getMethod().equals("POST"))
                {
                    prodName = request.getParameter("txtProdName");
                    prodDesc = request.getParameter("txtProdDesc");
                    prodEndDate = request.getParameter("txtProdEndDate");
                    prodBid = request.getParameter("txtProdStartBid");
                    
                    if(!prodName.equals(""))
                    {
                        if(!(prodName.length() > 50))
                        {
                            if(!prodDesc.equals(""))
                            {
                                if (!(prodDesc.length() > 255))
                                {
                                    if(!prodEndDate.equals(""))
                                    {
                                        if (prodEndDate.matches("^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$"))
                                        {
                                            Date dt = auc.getDate();
                                            Date prodDate = new SimpleDateFormat("yyyy-MM-dd").parse(prodEndDate);
                                            int compare = dt.compareTo(prodDate);
                                            if (compare < 0)
                                            {
                                            
                                                if(!prodBid.equals(""))
                                                {

                                                    if(prodBid.matches("^[1-9][\\.\\d]*(\\d+)?$"))
                                                    {

                                                        if (auc.AddProduct(prodName + "|" + prodDesc + "|" + prodBid + "|" + prodBid + "|" + prodEndDate + "|" + ownerID))
                                                        {
                                                            success = true;
                                                            session.setAttribute("addItem", prodName + " added successfully!");
                                                        }
                                                        else
                                                        {
                                                            /*  Used to set the message for the user */
                                                            session.setAttribute("addItem", "failed|Please check that you have entered the correct information!");
                                                        }
                                                    }
                                                    else
                                                    {
                                                        /*  Used to set the message for the user */
                                                            session.setAttribute("addItem", "failed|Please ensure you have entered numbers into the starting bid field!");
                                                    }
                                                }
                                                else
                                                {
                                                    /*  Used to set the message for the user */
                                                    session.setAttribute("addItem", "failed|Please enter a value into the product starting bid field!");
                                                }
                                            }
                                            else
                                            {
                                                /*  Used to set the message for the user */
                                                session.setAttribute("addItem", "failed|Please enter a date that is after today!");
                                            }
                                        }
                                        else
                                        {
                                            /*  Used to set the message for the user */
                                            session.setAttribute("addItem", "failed|Please make sure you follow this date format, YYYY-MM-DD!");
                                        }
                                    }
                                    else
                                    {
                                        /*  Used to set the message for the user */
                                        session.setAttribute("addItem", "failed|Please enter a value into the product end date field!");
                                    }
                                }
                                else
                                {
                                    /*  Used to set the message for the user */
                                    session.setAttribute("addItem", "failed|Please enter a product description that is less than 255 characters!");
                                }
                            }
                            else
                            {
                                /*  Used to set the message for the user */
                                session.setAttribute("addItem", "failed|Please enter a value into the product description field!");
                            }
                        }
                        else
                        {
                            /*  Used to set the message for the user */
                            session.setAttribute("addItem", "failed|Please enter a product name that is less than 50 characters!");
                        }
                    }
                    else
                    {
                        /*  Used to set the message for the user */
                        session.setAttribute("addItem", "failed|Please enter a value into the product name field!");
                    }
                    
                    //System.out.println(prodName + "|" + prodDesc + "|" + prodEndDate + "|" + prodBid);
                }

        %>
        <script>
            /*  This is used to set the timer for the message to be 5s and after that it will fade out for 0.35s */
            $(document).ready(function() {
                $(msg).delay(5000).fadeOut(350);
                $(txtDesc).val();
            });
        </script>
        <div id="bg">
            <img src="images/background_image_00.jpg" alt="">
        </div>
        <div id="main">
            <div id="msg" style='position:absolute;z-index:10;width:100%;height:20px;text-align:center;'>
                <%
                            if (request.getMethod().equals("POST"))
                            {
                                try
                                {
                                    
                                    /*  Below is used to display any messages to the user if adding a product successful, or if it failed.
                                     *  after that it then removed the addItem attribute from the session.
                                     */
                                    String temp = (String)session.getAttribute("addItem");
                                    //System.out.println(temp);
                                    if (!temp.equals("") || !temp.equals(null))
                                    {
                                        if (temp.contains("failed"))
                                        {
                                            String[] msg = temp.split("\\|");
                                            System.out.println(msg[0]);
                                            out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(255,25,25,0.6);padding:5px;'>" + msg[1] + "</h2>");

                                            //System.out.println(session.getAttribute("Update"));
                                        }
                                        else
                                        {
                                            out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(0,127,14,0.6);padding:5px;'>" + temp + "</h2>");
                                        }
                                        session.removeAttribute("addItem");

                                    }
                                }
                                catch(NullPointerException npe)
                                {
                                    
                                }
                            }
                %>
            </div>
            <div class="header-section">
                <div class="separator"></div>
                <div class="nav-links">
                    <div class="">
                        <p class="site-name pull-left">Auction House</p>
                        <ul class="page-links pull-right">
                            <li><a href="Home.jsp"><i class="fa fa-home fa-lg" style="padding-right:7px;"></i>Home</a></li>
                            <li><a href="Profile.jsp"><i class="fa fa-user fa-lg" style="padding-right:7px;"></i>Profile</a></li>
                            <li><a href="Browse.jsp"><i class="fa fa-search fa-lg" style="padding-right:7px;"></i>Browse</a></li>
                            <li><a href="MyItems.jsp" class="selected"><i class="fa fa-list fa-lg" style="padding-right:7px;"></i>My Items</a></li>
                            <li><a href="Logout.jsp"><i class="fa fa-sign-out fa-lg" style="padding-right:7px;"></i>Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="content-full" style="padding-top:20px;height:100%;" >
                <div id="itemAdd" class="itemAdd">
                    <form method="POST" action="MyItems.jsp">
                        <h2 id="itemHead">Add Item</h2>
                        <hr>
                        <table style="width:100%">
                            <tr>
                                <td style="width:400px;padding-right:20px;padding-top:3px;">
                                    <div style="width:100%;margin:auto;" class="input-group">
                                        <span class="input-group-addon"><p style="height:9px;padding-top:2px;width:90px;">Product Name</p></span>
                                        <input id="txtProdName" tabindex="1" autocomplete="off" name="txtProdName" class="form-control" value="<% if (request.getMethod().equals("POST") & success != true){ out.println(prodName);}%>"  type="text"/>
                                    </div>
                                </td>
                                <td rowspan="3" style="padding-left:20px;padding-top:3px;">
                                    <div style="width:100%;" class="input-group">
                                        <span class="input-group-addon" autocomplete="off" style="border-radius: 4px 4px 0px 0px "><p style="height:9px;padding-top:2px;">Description</p></span>
                                    </div>
                                    <textarea id="txtDesc" tabindex="2" style="resize:none; border-radius: 0px 0px 5px 5px; width:100%;height:80px;" name="txtProdDesc" class="form-control"><% if (request.getMethod().equals("POST") & success != true){ out.print(prodDesc);}%></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-right:20px;padding-top:3px;">
                                    <div style="width:100%;margin:auto;" class="input-group">
                                        <span class="input-group-addon"><p style="height:9px;padding-top:2px;width:90px;">End Date</p></span>
                                        <input id="txtProdEndDate" autocomplete="off" tabindex="1" value="<% if (request.getMethod().equals("POST") & success != true){ out.println(prodEndDate);}%>" name="txtProdEndDate"class="form-control"  type="text"/>
                                    </div>
                                </td>

                            </tr>
                            <tr>
                                <td style="padding-right:20px;padding-top:3px;">
                                    <div style="width:100%;margin:auto;" class="input-group">
                                        <span class="input-group-addon"><p style="height:9px;padding-top:2px;width:90px;">Starting Bid:  R</p></span>
                                        <input id="txtProdStartBid" autocomplete="off" tabindex="1" value="<% if (request.getMethod().equals("POST") & success != true){ out.println(prodBid);}%>" name="txtProdStartBid"class="form-control"  type="text"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="height:39px;"></td>
                            </tr>
                        </table>
                        <div class="pull-right" style="padding-top:10px;"> 
                            <input type="submit" tabindex="2" value="Add Item" id="btnSubmit" class="button btn btn-default btn-sm" />   <a href="#" tabindex="2" id="btnCancel" class="button btn btn-default btn-sm" >Cancel</a>
                        </div>
                    </form>
                </div>
                <br/><br/><br/>
                <div id="Listed" class="tblItems">
                    <h2>Listed Items</h2>
                    <table>
                        <tr>
                            <th>Product Name</th>
                            <th>Description</th>
                            <th>End Date</th>
                            <th>Current Bid</th>
                            <!--<th>Status</th>-->
                        </tr>
                    <%
                        /* Below is used to create the Listed Items table in the MyItems.jsp page */
                        if (arrListed.length > 0)
                        {
                            for(int x = 0; x < arrListed.length; x++)
                            {
                                String[] temp = arrListed[x].split("\\|");
                            %>
                            <tr>
                            <td><% out.println(temp[0]);%></td>
                            <td><% out.println(temp[1]);%></td>
                            <td><% out.println(temp[3]);%></td>
                            <td><% out.println("R " + temp[2]);%></td>
                            <!--<td></td>-->
                            </tr>
                            <%
                                
                            }
                        }
                        else
                        {
                    %>
                    <tr>
                        <td colspan="4"><p>No Items Listed</p></td>
                    </tr>
                            
                    <%
                        }
                    %>
                    </table>
                    <br/>
                </div>
                <div id="Purchased" class="tblItems">
                    <h2>Purchased Items</h2>
                    <table>
                        <tr>
                            <th>Product Name</th>
                            <th>Description</th>
                            <th>End Date</th>
                            <th>Current Bid</th>
                            <!--<th>Status</th>-->
                        </tr>
                    <%
                         /* Below is used to create the Purchased Items table in the MyItems.jsp page */
                        if (arrPurchased.length > 0)
                        {
                            for(int x = 0; x < arrPurchased.length; x++)
                            {
                                String[] temp = arrPurchased[x].split("\\|");
                            %>
                            <tr>
                            <td><% out.println(temp[0]);%></td>
                            <td><% out.println(temp[1]);%></td>
                            <td><% out.println(temp[3]);%></td>
                            <td><% out.println("R " + temp[2]);%></td>
                            <!--<td></td>-->
                            </tr>
                            <%
                                
                            }
                        }
                        else
                        {
                    %>
                    <tr>
                        <td colspan="4"><p style="font-size:15pt;padding:5px;">No Items Purchased</p></td>
                    </tr>
                            
                    <%
                        }
                    %>
                    </table>
                </div>
                
            </div>
        <%
            }
            catch(NullPointerException npe)/*  This catch is used to check if the session for the user has expried, asks the user to login again  */
            {
                session.setAttribute("login", "Login session has expired, please login again.");
                response.sendRedirect("Login.jsp");
            }
        %>
        </div>
    </body>
</html>  