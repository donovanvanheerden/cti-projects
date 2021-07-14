<%-- 
    Document   : Login
    Created on : 02 Mar 2015, 11:26:02 AM
    Author     : Donovan van Heerden - EL2014-0043
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="AuctionErrorPage.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
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
                    /*  sets the logged cookie value to null */
                    Cookie[] cookies = request.getCookies();

                    Cookie check = null;

                    if (cookies != null)
                    {
                        for (int x = 0; x < cookies.length; x++) 
                        { 
                            /* look for specific cookie by name */ 
                            if (!cookies[x].getValue().equals("") && cookies[x].getName().equals("logged")) 
                            { 
                                check = new Cookie("logged", null);
                                response.addCookie(check);
                            }
                        }
                    }
                    /*  Checks if the user is logged in, and redirects them to the Home.jsp if they are logged in. */
                    if (session.getAttribute("login").equals(true))
                    {
                        response.sendRedirect("Home.jsp");
                    }
                }
                catch(NullPointerException npe)
                {

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
        <div id="main">
            <div id="msg" style='position:absolute;z-index:10;width:100%;height:20px;text-align:center;'>
                <%
                        try
                        {
                            /*  Below is used to display any messages to the user if the registration was successful.
                             *  after that it then removes the signUp attribute from the session.
                             */
                            
                            //System.out.println("On Login Page");
                            String temp = (String)session.getAttribute("signUp");
                            if ((!temp.equals("") || !temp.equals(null)) && (!temp.contains("false")))
                            {
                                //System.out.println("In if Check");
                                out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(0,127,14,0.6);padding:5px;'>" + temp + "</h2>");
                                session.removeAttribute("signUp");
                            }
                        }
                        catch(NullPointerException npe)
                        {
                            
                        }
                        try
                        {
                            /*  Below is used to display any messages to the user if the login attemp was not successful.
                             *  after that it then removes the login attribute from the session.
                             */
                            
                            //System.out.println(session.getAttribute("login"));
                            if (session.getAttribute("login").equals(false))
                            {
                                //System.out.println("Inside login check for false");
                                out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(255,25,25,0.6);padding:5px;'>Login Information Incorrect, please try again!</h2>");
                                session.removeAttribute("login");
                            }
                        }
                        catch(NullPointerException npe)
                        {
                            
                        }
                        try
                        {
                            /*  Below is used to display any messages to the user if the session for the user has exprired.
                             *  after that it then removed the login attribute from the session.
                             */
                            String tempLog = (String)session.getAttribute("login");
                            if (tempLog.contains("expired"))
                            {
                                //System.out.println("Inside login check for false");
                                out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(255,25,25,0.6);padding:5px;'>" + tempLog + "</h2>");
                                session.removeAttribute("login");
                            }
                        }
                        catch(NullPointerException npe)
                        {
                            
                        }
                        try
                        {
                            /*  Below is used to display any messages to the user if the password reset was successful.
                             *  after that it then removed the fPass attribute from the session.
                             */
                            String fPass = (String)session.getAttribute("fPass");
                            if (!fPass.equals(""))
                            {
                                out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(0,127,14,0.6);padding:5px;'>" + fPass + "</h2>");
                                session.removeAttribute("fPass");
                            }
                        }
                        catch(NullPointerException npe)
                        {
                            
                        }
                        
                        
                    %>
            </div>
            <div class="container-content">
                <div id="content">
                    <h2 class="main-head">Welcome to Auction House!</h2>
                    <hr>
                    </br>
                    <table id="tblForms">
                        <tr>
                            <td>
                                <div id="login-info">
                                    <img src="images/OwlTopHat_Right.png" class="logo-right" />
                                    <div id="login-page-message" class="left">
                                        <div class="arrow-example-left arrow-border-example"></div>
                                        <div class="arrow-example-left"></div>
                                            <h2 style="text-shadow: 0px 1px 1px gray;">Welcome!</h2>
                                            <p>Please enter your login information if you already have an account.</p>
                                            </br>
                                            <p>Don't have an account, <a href="SignUp.jsp">Sign Up!</a></p>
                                            </br>
                                            <p>Incorrect login details? Did you perhaps,</p><p> <a href="ForgotPassword.jsp">Forget your Password?</a></p>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div id="login-form">
                                    <h2>Login</h2>
                                    <hr>
                                    <form method="POST" action="LoginCheck.jsp">
                                        <table id="tblInputs">
                                            <tr>
                                                <td>
                                                    <div style="width:100%;margin:auto;" class="input-group">
                                                        <span class="input-group-addon"><i class="fa fa-envelope fa-fw"></i></span>
                                                        <input autocomplete="off" name="email" class="form-control" type="text" placeholder="Email Address"/>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div style="width:100%;margin:auto;" class="input-group">
                                                        <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                                        <input autocomplete="false" name="pass" class="form-control" type="password" placeholder="Password"/>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input class="button btn btn-default btn-sm" type="submit" value="Login"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html> 
