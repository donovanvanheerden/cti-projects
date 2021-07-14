<%-- 
    Document   : Browse
    Created on : 02 Mar 2015, 11:45:52 AM
    Author     : Donovan van Heerden - EL2014-0043
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, AuctionWS.*" %>
<%@ page errorPage="AuctionErrorPage.jsp" %>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta content="text/html; charset=UTF-8"/>
        <title>Browse</title>
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
                
                /*  Used to retrieve items for the current user's browse page   */
                String[] prodArr = auc.BrowseItems(auc.getCustomerID(check.getValue()));
                //System.out.println(prodArr.size());
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
                        try
                        {
                            /*  Below is used to display any messages to the user if the bid was successful, or if it failed.
                             *  after that it then removed the Bid attribute from the session.
                             */
                            String temp = (String)session.getAttribute("Bid");
                            //System.out.println("outside temp check");
                            //System.out.println(temp);
                            if (!temp.equals("") || !temp.equals(null))
                            {
                                //System.out.println("In temp check");
                                if (!temp.contains("not"))
                                {

                                    out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(0,127,14,0.6);padding:5px;'>" + session.getAttribute("Bid") + "</h2>");

                                    //System.out.println(session.getAttribute("Bid"));
                                }
                                else
                                {

                                    out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(255,25,25,0.6);padding:5px;'>" + session.getAttribute("Bid") + "</h2>");

                                    //System.out.println(session.getAttribute("Bid"));
                                }
                                session.removeAttribute("Bid");

                            }
                            else
                            {
                                session.removeAttribute("Bid");
                            }
                        }
                        catch(NullPointerException npEx)
                        {

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
                            <li><a href="Browse.jsp" class="selected"><i class="fa fa-search fa-lg" style="padding-right:7px;"></i>Browse</a></li>
                            <li><a href="MyItems.jsp"><i class="fa fa-list fa-lg" style="padding-right:7px;"></i>My Items</a></li>
                            <li><a href="Logout.jsp"><i class="fa fa-sign-out fa-lg" style="padding-right:7px;"></i>Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="content-full"style="padding-top:50px;padding-bottom:40px;height:100%;">
                <div class="items-heading">
                    <table style='width:100%'><tr><td style='width:300px;padding-bottom:5px;' class='pull-left'><h3>Product Name</h3></td><td style='width:200px;text-align:center;'><h3>End Date</h3></td><td style='width:150px;text-align:center;'><h3>Current Bid</h3></td></tr></table>
                </div>
                <div class="items">
                    <ul class="item-links">
                        <%
                                /*  This is used to display each product on the browse.jsp page */
                                for (int x = 0; x < prodArr.length; x++)
                                {
                                    //System.out.println(prodArr[x] + ",\nPosition: " + x);
                                    String[] tempArr = prodArr[x].split("\\|");

                                    out.println("<li><a href='ItemBid.jsp?ProdID=" + tempArr[0] + "' class='item-btn'><div class='item-content'><table style='width:100%'><tr><td style='width:300px;padding-top:25px;padding-bottom:25px;padding-left:10px;' class='pull-left'>" + tempArr[1] +"</td><td style='width:200px;text-align:center;'>" + tempArr[4] + "</td><td style='width:150px;text-align:center;'>R " + tempArr[3] + "</td></tr></table></div></a></li>");
                                }
                        %>
                    </ul>
                </div>
            </div>
        <%
            }
            catch(NullPointerException npe) /*  This catch is used to check if the session for the user has expried, asks the user to login again  */
            {
                session.setAttribute("login", "Login session has expired, please login again.");
                response.sendRedirect("Login.jsp");
            }
        %>
        </div>
    </body>
</html>  
