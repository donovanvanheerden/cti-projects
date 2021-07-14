<%-- 
    Document   : ItemBid
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
        <title>Item Bid</title>
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

                String prodID = request.getParameter("ProdID");

                String[] prodArr = auc.GetProdInfo(prodID).split("\\|");
                
                /*  Gets date from server, to identify if the product is past its bidding date  */
                Date dt = auc.getDate();
                Date prodEnd = new SimpleDateFormat("yyyy-MM-dd").parse(prodArr[3]);
                int result = dt.compareTo(prodEnd);
                
                if (result > 0)
                {
                    session.setAttribute("Bid","Could not find " + prodArr[0] + ". " + prodArr[0] + " must have expired!");
                    response.sendRedirect("Browse.jsp");
                }
                
                
                if (request.getMethod().equals("POST"))
                {
                    try
                    {

                        String value = request.getParameter("txtBid");
                        if(!value.equals("") || !value.equals(null))
                        {
                            System.out.println(value);
                            
                            if (value.matches("^[1-9][\\.\\d]*(\\d+)?$"))
                            {
                                if(auc.ProductBid(auc.getCustomerID(check.getValue()), prodID, Double.parseDouble(value)))
                                {
                                    //System.out.println("Bid Successful");
                                    
                                    /*  Used to set the message for the user */
                                    session.setAttribute("Bid", "Bid on " + prodArr[0] + " was successful!");
                                    response.sendRedirect("Browse.jsp");
                                }
                                else
                                {
                                    //System.out.println("Bid not Successful");
                                    if (Double.parseDouble(prodArr[2]) >= Double.parseDouble(value) )
                                    {
                                        //System.out.println("Bid too low");
                                        
                                        /*  Used to set the message for the user */
                                        session.setAttribute("Bid", "Bid on " + prodArr[0] + " not was successful! Bid value too low!");
                                    }
                                    else
                                    {
                                        /*  Used to set the message for the user */
                                        session.setAttribute("Bid", "Bid on " + prodArr[0] + " not was successful!");
                                    }
                                }
                            }
                            else
                            {
                                /*  Used to set the message for the user */
                                session.setAttribute("Bid", "Bid on " + prodArr[0] + " not was successful! Please ensure you typed numbers! (eg. 24.95)");
                            }
                        }
                    }
                    catch(NumberFormatException nfe)
                    {
                        session.setAttribute("Bid", "Bid on " + prodArr[0] + " not was successful! Please enter a number!");
                        System.out.println(session.getAttribute("Bid"));
                        
                    }
                    catch(Exception x)
                    {
                        System.out.println(x);
                    }
                }
        %>
        <script>
            /*  This is used to set the timer for the message to be 5s and after that it will fade out for 0.35s */
             $(document).ready(function(){
                $(msg).delay(5000).fadeOut(350);
            });
            
        </script>
        <div id="bg">
            <img src="images/background_image_00.jpg" alt="">
        </div>
        <div id="main" style="overflow-y:hidden;overflow-x:hidden;">
            <div id="msg" style='position:absolute;z-index:10;width:100%;height:20px;text-align:center;'>
                <%
                            if (request.getMethod().equals("POST"))
                            {
                                /*  Below is used to display any messages to the user if the bid was not successful.
                                 *  after that it then removed the Bid attribute from the session.
                                 */
                                String temp = (String)session.getAttribute("Bid");
                                if (!temp.equals("") || !temp.equals(null))
                                {
                                    if (temp.contains("not"))
                                    {

                                        out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(255,25,25,0.6);padding:5px;'>" + temp + "</h2>");

                                        //System.out.println(session.getAttribute("Update"));
                                        session.removeAttribute("Bid");
                                    }
                                    
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
                            <li><a href="MyItems.jsp"><i class="fa fa-list fa-lg" style="padding-right:7px;"></i>My Items</a></li>
                            <li><a href="Logout.jsp"><i class="fa fa-sign-out fa-lg" style="padding-right:7px;"></i>Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="content-full" >
                <div class="profile-info">
                    <form method="POST" action="ItemBid.jsp?ProdID=<%out.println(prodID);%>">
                    <h2><% out.println(prodArr[0]); %></h2>
                    <table class="tblMain">
                        <tr>
                            <td>
                                <p>Item Description:</p>
                                
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="3">
                                <textarea cols="50" rows="5" style="resize:none;" readonly="true" class="form-control"><% out.println(prodArr[1]); %></textarea>
                            </td>
                            <td>
                                <p>Item Listed By: <% out.println(prodArr[4] + " " + prodArr[5]);%></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Current Bid: R<% out.println(prodArr[2]); %></p>
                            </td> 
                        </tr>
                        <tr>
                            <td>
                                <p>Bid End Date: <% out.println(prodArr[3]); %></p>
                            </td>
                        </tr>
                    </table>
                    </br>
                    </br>
                    </br>
                    </br>
                    <div style="width:100%;">
                        <table style="width:400px;margin:auto;">
                            <tr>
                                <td style="padding-left:5px;padding-right:5px;"><input type="submit" value="Bid" id="btnBid" class="button btn btn-default btn-sm" /></td>
                                <td style="padding-left:5px;padding-right:5px;">
                                    <div style="width:100%;margin:auto;" class="input-group">
                                        <span class="input-group-addon"><p style="text-align:center;padding:3px;height:8px;">R</p></span>
                                        <input id="txtBid" name="txtBid" class="form-control" autocomplete="off"  type="text" placeholder="Bid Value"/>
                                    </div> 
                                </td>
                                <td style="padding-left:5px;padding-right:5px;">
                                    <a href="Browse.jsp" id="btnCancel" class="button btn btn-default btn-sm" >Cancel</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
                    
                </div>
            </div>
            <div class="footer-section-norm">
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