<%-- 
    Document   : Home
    Created on : 02 Mar 2015, 11:45:21 AM
    Author     : Donovan van Heerden - EL2014-0043
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, AuctionWS.*" %>
<%@ page errorPage="AuctionErrorPage.jsp" %>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta content="text/html; charset=UTF-8"/>
        <title>Home</title>
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
        %>
        
        <div id="bg">
            <img src="images/background_image_00.jpg" alt="">
        </div>
        <div id="main" style="overflow-y:auto;">
            <div class="header-section">
                <div class="separator"></div>
                <div class="nav-links">
                    <div class="">
                        <p class="site-name pull-left">Auction House</p>
                        <ul class="page-links pull-right">
                            <li><a href="Home.jsp" class="selected"><i class="fa fa-home fa-lg" style="padding-right:7px;"></i>Home</a></li>
                            <li><a href="Profile.jsp"><i class="fa fa-user fa-lg" style="padding-right:7px;"></i>Profile</a></li>
                            <li><a href="Browse.jsp"><i class="fa fa-search fa-lg" style="padding-right:7px;"></i>Browse</a></li>
                            <li><a href="MyItems.jsp"><i class="fa fa-list fa-lg" style="padding-right:7px;"></i>My Items</a></li>
                            <li><a href="Logout.jsp"><i class="fa fa-sign-out fa-lg" style="padding-right:7px;"></i>Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="content-partial">
                <img src="images/OwlTopHat_Right.png" class="logo-right" />
                <div class="home-message">
                    <div class="arrow-example-left arrow-border-example"></div>
                    <div class="arrow-example-left"></div>
                    <%
                        AuctionService auc = new AuctionService();
                        /*  Used to get User's Name*/
                        String[] user = auc.GetUserInfo(check.getValue()).split("\\|");
                        
                    %>
                    <h2><%  out.println("Welcome, " + user[0] + "!");  %></h2>
                    <br/>
                    <p style="font-size:14pt;">This is Auction House!</p>
                    <p style="font-size:12pt;">
                        An online Antique Auction Website, we hope you enjoy your stay! We allow our users to add antique items
                        valued from R1 to over R100 000!
                    </p>
                    <br/>
                    <br/>
                    <br/>
                    <p><i>Feel free to contact us via email on <b>info@AH.co.za</b> if you have any questions, or view our <b>FAQ</b> below if you happen to have a common issue.</i></p>
                </div>
            </div>
            <div class="envelop-top">
                <!--<hr>-->
                <div class="envelop-effect"></div>
                <p class="trans-header">F.A.Q.</p>
            </div>
            <div class="trans-content">
                <table class="tbl-FAQ">
                    <tr>
                        <td>
                            <div class="Question">
                                <img src="images/FAQ_User.png" class="logo-right" />
                                <div class="quest-message">
                                    <div class="arrow-example-left arrow-border-example"></div>
                                    <div class="arrow-example-left"></div>
                                    <p style="font-size:12pt;">How do I view and edit my profile?</p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="Answer">
                                <div class="ans-message">
                                    <div class="arrow-example-right arrow-border-example"></div>
                                    <div class="arrow-example-right"></div>
                                    <p style="font-size:12pt;">If you look to the right of the website you will see our website menu, click on the "Profile" link to continue, and from there you can click on the button bottom right-hand side of the website to edit your information.</p>
                                </div>
                                <img src="images/OwlTopHat_Left.png" class="logo-left" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="Question">
                                <img src="images/FAQ_User.png" class="logo-right" />
                                <div class="quest-message">
                                    <div class="arrow-example-left arrow-border-example"></div>
                                    <div class="arrow-example-left"></div>
                                    <p style="font-size:12pt;">How long does a bid on an item last?</p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="Answer">
                                <div class="ans-message">
                                    <div class="arrow-example-right arrow-border-example"></div>
                                    <div class="arrow-example-right"></div>
                                    <p style="font-size:12pt;">The bid on an item lasts until someone outbids your bid, or the item bidding period has expired.</p>
                                </div>
                                <img src="images/OwlTopHat_Left.png" class="logo-left" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="Question">
                                <img src="images/FAQ_User.png" class="logo-right" />
                                <div class="quest-message">
                                    <div class="arrow-example-left arrow-border-example"></div>
                                    <div class="arrow-example-left"></div>
                                    <p style="font-size:12pt;">I can't register a new account for my friend, I keep getting redirected to the "Home" page?</p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="Answer">
                                <div class="ans-message">
                                    <div class="arrow-example-right arrow-border-example"></div>
                                    <div class="arrow-example-right"></div>
                                    <p style="font-size:12pt;">You probably haven't logged out, simply click on the logout button, on the menubar at the top of the page, on the far right.</p>
                                </div>
                                <img src="images/OwlTopHat_Left.png" class="logo-left" />
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="footer-section">
                    <!--<hr>-->
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
