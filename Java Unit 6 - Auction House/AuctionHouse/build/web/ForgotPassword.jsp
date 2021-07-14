<%-- 
    Document   : ForgotPassword
    Created on : 02 Mar 2015, 11:45:39 AM
    Author     : Donovan van Heerden - EL2014-0043
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, AuctionWS.*" %>
<%@ page errorPage="AuctionErrorPage.jsp" %>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta content="text/html; charset=UTF-8"/>
        <title>Forgot Password</title>
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
                    /*  Checks to see if the user is logged in, and if so, redirects the user to the Home.jsp   */
                   if (session.getAttribute("login").equals(true))
                    {
                        response.sendRedirect("Home.jsp");
                    }
                }
                catch(NullPointerException npe)
                {

                }
                
                /*  tempUser Array is used to store the users information temporary
                 *  allowing the information typed to not be lost after a page reload.
                 */
                String[] tempUser = new String[2];
                
                /*  Checks the request, if a POST method occurs */
                if(request.getMethod().equals("POST"))
                {
                    /*  populates various string values from the request.getParameter() methods allowing only one call
                     *  per variable, so that there are not multiple calls to the same request.getParameter() for every check below.
                     */
                    String email = request.getParameter("txtEmail"), ans = request.getParameter("txtAns"), pass = request.getParameter("txtPass"), confPass = request.getParameter("txtConfPass");
                    tempUser[0] = email;
                    tempUser[1] = ans;
                    AuctionService auc = new AuctionService();

                    if (!email.equals(""))
                    {
                        if(email.contains("@"))
                        {
                            if (!ans.equals(""))
                            {
                                if (!pass.equals(""))
                                {
                                    if(!(pass.length() > 30))
                                    {
                                        if(pass.equals(confPass))
                                        {
                                            if(auc.ForgotPassword(email, ans, pass))
                                            {
                                                /*  Used to set the message for the user */
                                                session.setAttribute("fPass", "Password changed successfully!");
                                            }
                                            else
                                            {
                                                /*  Used to set the message for the user */
                                                session.setAttribute("fPass", "failed|Please make sure the information you have entered is correct!");
                                            }
                                        }
                                        else
                                        {
                                            /*  Used to set the message for the user */
                                            session.setAttribute("fPass", "failed|Please make sure your new password and confirm password fields are the same!");
                                        }
                                    }
                                    else
                                    {
                                        /*  Used to set the message for the user */
                                        session.setAttribute("fPass", "failed|Please enter a password containing 50 characters or less!");
                                    }
                                }
                                else
                                {
                                    /*  Used to set the message for the user */
                                    session.setAttribute("fPass", "failed|Please make sure you a new password!");
                                }
                            }
                            else
                            {
                                /*  Used to set the message for the user */
                                session.setAttribute("fPass", "failed|Please make sure you entered your answer to the security question!");
                            }
                        }
                        else
                        {
                            /*  Used to set the message for the user */
                            session.setAttribute("fPass", "failed|Please make sure you entered a valid email address!");
                        }
                    }
                    else
                    {
                        /*  Used to set the message for the user */
                        session.setAttribute("fPass", "failed|Please make sure you entered your email address!");
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
        <div id="main">
            <div id="msg" style='position:absolute;z-index:10;width:100%;height:20px;text-align:center;'>
                <%
                        try
                        {
                            /*  Below is used to display any messages to the user if the password was changed successfully, or if it failed.
                             *  If it was successful it redirects to Login.jsp and the message for password being changed successful
                             *  is then displayed there.
                             */
                            if(request.getMethod().equals("POST"))
                            {
                                String temp = (String)session.getAttribute("fPass");
                                if (temp.contains("failed"))
                                {
                                    String[] tempArr = temp.split("\\|");
                                    
                                    out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(255,25,25,0.6);padding:5px;'>" + tempArr[1] + "</h2>");
                                    session.removeAttribute("fPass");
                                }
                                else
                                {
                                    response.sendRedirect("Login.jsp");
                                }
                            }
                        }
                        catch(NullPointerException npe)
                        {
                            
                        }
                        
                    %>
            </div>
            <div class="container-content">
                <div id="content">
                    <h2 class="main-head">Forgot Password</h2>
                    <hr>
                    <form method="POST" action="ForgotPassword.jsp">
                        <table style="width:100%;height:100%;">
                            <tr>
                                <td style="width:40%;">
                                    <table class="tblForgotPass">
                                        <tr>
                                            <td>
                                                <h4>Please enter your Email Address</h4>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-envelope fa-fw"></i></span>
                                                    <input id="txtEmail" name="txtEmail" autocomplete="off" class="form-control" value="<% if(request.getMethod().equals("POST")){out.println(tempUser[0]);}%>" type="text" placeholder="Email Address"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <h4>Security Question</h4>
                                                <p>What is your mother's maiden name?</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                                                    <input id="txtAns" name="txtAns" autocomplete="off" class="form-control" value="<% if(request.getMethod().equals("POST")){out.println(tempUser[1]);}%>" type="password" placeholder="Answer"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <h4>Enter your new Password</h4>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>	
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                                                    <input id="txtPass" name="txtPass" autocomplete="off" class="form-control" type="password" placeholder="New Password"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>	
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                                                    <input id="txtConfPass" name="txtConfPass" autocomplete="off" class="form-control" type="password" placeholder="Confirm New Password"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                </br>
                                                <div class="pull-right" style="padding-right:10px;">
                                                    <input class="button btn btn-default btn-sm" type="submit" value="Submit"/>  <a href="Login.jsp" class="button btn btn-default btn-sm" >Cancel</a>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td style="width:60%;padding:15px;">
                                    <div class="forgot-pass-message">
                                        <div class="arrow-example-right arrow-border-example"></div>
                                        <div class="arrow-example-right"></div>
                                        <div class="forgot-pass-message-tip">
                                            <h4>Oops! It seems you forgot your Password!</h4>
                                            <p>Below are some handy tips!</p>
                                            <p>Ensure the password is easy to remember.
                                                For Example - 
                                                <ul>
                                                    <li>Simply use four random words like; "Correct", "Horse", "Battery", and "Staple"</li>
                                                    <li>Use these four words in an easy to remember sentence, "Correct!, there is a Horse near the Battery and Staple!"</li>
                                                </ul>
                                            </p>
                                            <p class="forgot-pass-message-note"><strong>Did you know:</strong> Through 20 years of effort, we've successfully trained everyone
                                                    to use passwords that are hard for humans to remember, but easy for computers to guess, eg. "P455W0rd101"<p>
                                        </div>
                                    </div>
                                    <img src="images/OwlTopHat_Left.png" style="float:right;" class="logo-left" />
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>  
