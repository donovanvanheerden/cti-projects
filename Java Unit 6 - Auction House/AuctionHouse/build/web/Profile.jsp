<%-- 
    Document   : Profile
    Created on : 02 Mar 2015, 11:44:45 AM
    Author     : Donovan van Heerden - EL2014-0043
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, AuctionWS.*" %>
<%@ page errorPage="AuctionErrorPage.jsp" %>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta content="text/html; charset=UTF-8"/>
        <title>Profile</title>
        <link rel="SHORTCUT ICON" href="images/top_hat.png"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            try
            {
                /*  Fetching information from the logged cookie */
                Cookie[] cookies = request.getCookies();

                Cookie user = null;

                if (cookies != null)
                {
                    for (int x = 0; x < cookies.length; x++) 
                    { 
                        /* look for specific cookie by name */ 
                        if (!cookies[x].getValue().equals("") && cookies[x].getName().equals("logged")) 
                        { 
                            user = cookies[x];

                        }
                    }
                }
                
                /*  Checks if the user is logged in, if not, the user is then redirected to the login.jsp page */
                if (session.getAttribute("login").equals("") || !session.getAttribute("login").equals(true))
                {
                    response.sendRedirect("Login.jsp");
                }


                AuctionService auc = new AuctionService();
                
                /*  gets the users information to display the values as placeholder text in the input textboxes below. */
                String[] getUserInfo = auc.GetUserInfo(user.getValue()).split("\\|");
                
                /* splits the address from one string into 4 elements of a String[] */
                String[] address = getUserInfo[5].split(",");

                //System.out.println("after address split");

                if (request.getMethod().equals("POST"))
                {
                    try
                    {
                        //System.out.println("Inside POST if statement");
                        if (cookies != null)
                        {
                            String userInfo = null;
                            
                            /*  The if statements below are used to check if the variables parameters are null,
                             *  if they aren't it then uses that to build a temp string varable called userInfo.
                             *  Until eventually it has all information need to perform the update and attempts
                             *  to update the users information.
                             */
                            if (request.getParameter("txtfName").equals(null) || request.getParameter("txtfName").equals(""))
                            {
                                userInfo = getUserInfo[0] + "|";
                            }
                            else
                            {
                                userInfo = request.getParameter("txtfName") + "|";
                            }

                            if (request.getParameter("txtlName").equals(null) || request.getParameter("txtlName").equals(""))
                            {
                                userInfo += getUserInfo[1] + "|";
                            }
                            else
                            {
                                userInfo += request.getParameter("txtlName") + "|";
                            }

                            if (request.getParameter("txtPass").equals(null) || request.getParameter("txtPass").equals(""))
                            {
                                userInfo += getUserInfo[3] + "|";
                            }
                            else
                            {
                                userInfo += request.getParameter("txtPass") + "|";
                            }

                            if (request.getParameter("txtContactNum").equals(null) || request.getParameter("txtContactNum").equals(""))
                            {
                                userInfo += getUserInfo[4] + "|";
                            }
                            else
                            {
                                userInfo += request.getParameter("txtContactNum") + "|";
                            }

                            if (request.getParameter("txtStreet").equals(null) || request.getParameter("txtStreet").equals(""))
                            {
                                userInfo += address[0] + ",";
                            }
                            else
                            {
                                userInfo += request.getParameter("txtStreet") + ",";
                            }

                            if (request.getParameter("txtSuburb").equals(null) || request.getParameter("txtSuburb").equals(""))
                            {
                                userInfo += address[1] + ",";
                            }
                            else
                            {
                                userInfo += request.getParameter("txtSuburb") + ",";
                            }

                            if (request.getParameter("txtCity").equals(null) || request.getParameter("txtCity").equals(""))
                            {
                                userInfo += address[2] + ",";
                            }
                            else
                            {
                                userInfo += request.getParameter("txtCity") + ",";
                            }

                            if (request.getParameter("txtCode").equals(null) || request.getParameter("txtCode").equals(""))
                            {
                                userInfo += address[3] + "|";
                            }
                            else
                            {
                                userInfo += request.getParameter("txtCode") + "|";
                            }

                            if (request.getParameter("txtAns").equals(null) || request.getParameter("txtAns").equals(""))
                            {
                                //System.out.println("txtAns is null");
                                userInfo += getUserInfo[6];
                            }
                            else
                            {
                                //System.out.println("txtAns is not null");
                                userInfo += request.getParameter("txtAns");
                            }

                            /*  The temporary variable called userInfo is the split and used to check if the
                             *  values passed to it was correct or not. And displays a appropriate message
                             *  if the value isn't correct.
                             */
                            String[] temp = userInfo.split("\\|");
                            if(!(temp[0].length() > 20))
                            {
                                if(!(temp[1].length() > 50))
                                {
                                    if(temp[3].startsWith("27"))
                                    {
                                        if((temp[3].length() == 11))
                                        {
                                            if(auc.UpdateUserInfo(userInfo, user.getValue()))
                                            {
                                                //System.out.println("Success");
                                                //response.sendRedirect("Profile.jsp");
                                                session.setAttribute("Update", "Updating your profile was successful!");
                                                request.getRequestDispatcher("Profile.jsp").forward(request, response);

                                            }
                                            else
                                            {
                                                //System.out.println("failed");
                                                
                                                /*  Used to set the message for the user */
                                                session.setAttribute("Update", "Updating your profile was not successful!");
                                                response.sendRedirect("Profile.jsp");
                                            }
                                        }
                                        else
                                        {
                                            /*  Used to set the message for the user */
                                            session.setAttribute("Update", "failed|Please enter an 11 digit contact number! Eg.(27123125514)");
                                        }
                                    }
                                    else
                                    {
                                        /*  Used to set the message for the user */
                                        session.setAttribute("Update", "failed|Please enter an 11 digit contact number starting with 27! Eg.(27123125514)");
                                    }
                                }
                                else
                                {
                                    /*  Used to set the message for the user */
                                    session.setAttribute("Update", "failed|Please enter a last name containing 50 characters or less!");
                                }
                            }
                            else
                            {
                                /*  Used to set the message for the user */
                                session.setAttribute("Update", "failed|Please enter a name containing 20 characters or less!");
                            }
                        }
                        else
                        {
                            response.sendRedirect("Login.jsp");
                        }
                    }
                    catch(Exception x)
                    {
                        System.out.println(x);
                    }

                }

        
        %>
        <script>
            $(document).ready(function(){
                /*  This is used to set the timer for the message to be 5s and after that it will fade out for 0.35s */
                $(msg).delay(5000).fadeOut(350);
                $(btnSave).hide();
                $(btnCancel).hide();
                $(ConfPassGroup).hide();
                
                /*  This is used to make certain input tags readonly attribute false and allow the user
                 *  to input information.
                 */
                $(btnChange).click(function(){
                    
                    $(btnChange).hide();
                    $(btnSave).show();
                    $(btnCancel).show();
                    $(ConfPassGroup).show();
                    $(txtConfPass).prop('readonly', false);
                    $(txtPass).prop('readonly', false);
                    $(txtAns).prop('readonly', false);
                    $(txtfName).prop('readonly', false);
                    $(txtlName).prop('readonly', false);
                    $(txtContactNum).prop('readonly', false);
                    $(txtStreet).prop('readonly', false);
                    $(txtSuburb).prop('readonly', false);
                    $(txtCity).prop('readonly', false);
                    $(txtCode).prop('readonly', false);
                    
                    
                });
                
                /*
                 * This allows the various input textboxes readonly attribute to be set back to readonly true
                 * and set the values back to null or ""
                 */
                $(btnCancel).click(function(){
                    
                    $(btnSave).hide();
                    $(btnCancel).hide();
                    $(btnChange).show();
                    $(txtConfPass).prop('readonly', true); $(txtConfPass).val("");
                    $(txtPass).prop('readonly', true); $(txtPass).val("");
                    $(txtAns).prop('readonly', true); $(txtAns).val("");
                    $(txtfName).prop('readonly', true); $(txtfName).val("");
                    $(txtlName).prop('readonly', true); $(txtlName).val("");
                    $(txtContactNum).prop('readonly', true); $(txtContactNum).val("");
                    $(txtStreet).prop('readonly', true); $(txtStreet).val("");
                    $(txtSuburb).prop('readonly', true); $(txtSuburb).val("");
                    $(txtCity).prop('readonly', true); $(txtCity).val("");
                    $(txtCode).prop('readonly', true); $(txtCode).val("");
                    $(ConfPassGroup).hide();
                    
                });
                
                
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
                                /*  Below is used to display any messages to the user if the Update to the user's profile
                                 *  was successful, or if it failed. After that it then sets the Update attribute to be null or "".
                                 */
                                String temp = (String)session.getAttribute("Update");
                                if (!temp.equals("") || !temp.equals(null))
                                {
                                    if(temp.contains("failed"))
                                    {
                                        String[] msg = temp.split("\\|");
                                        out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(255,25,25,0.6);padding:5px;'>" + msg[1] + "</h2>");
                                    }
                                    else if (!temp.contains("not"))
                                    {

                                        out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(0,127,14,0.6);padding:5px;'>" + session.getAttribute("Update") + "</h2>");

                                        //System.out.println(session.getAttribute("Update"));
                                    }
                                    else
                                    {

                                        out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(255,25,25,0.6);padding:5px;'>" + session.getAttribute("Update") + "</h2>");

                                        //System.out.println(session.getAttribute("Update"));
                                    }
                                    session.setAttribute("Update", "");

                                }
                            }
                %>
            </div>
            <div class="header-section">
                
                <div class="separator"></div>
                <div class="nav-links">
                    <div>
                        <p class="site-name pull-left">Auction House</p>
                        <ul class="page-links pull-right">
                            <li><a href="Home.jsp"><i class="fa fa-home fa-lg" style="padding-right:7px;"></i>Home</a></li>
                            <li><a href="Profile.jsp" class="selected"><i class="fa fa-user fa-lg" style="padding-right:7px;"></i>Profile</a></li>
                            <li><a href="Browse.jsp"><i class="fa fa-search fa-lg" style="padding-right:7px;"></i>Browse</a></li>
                            <li><a href="MyItems.jsp"><i class="fa fa-list fa-lg" style="padding-right:7px;"></i>My Items</a></li>
                            <li><a href="Logout.jsp"><i class="fa fa-sign-out fa-lg" style="padding-right:7px;"></i>Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="content-full">
                <div class="profile-info">
                    <form method="POST" action="Profile.jsp">
                    <h2><% out.println(getUserInfo[0] + "'s");%> Profile</h2>
                    <table class="tblMain">
                        <tr>
                            <td>
                                <h4>Personal Information</h4>
                                <table class="tblLeft">
                                    <tr>
                                        <td>
                                            <div style="width:100%;margin:auto;" class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                                                <input id="txtfName" name="txtfName" autocomplete="off" readonly="true" class="form-control" type="text" placeholder='<% out.println(getUserInfo[0]);%>'/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div style="width:100%;margin:auto;" class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                                                <input id="txtlName" name="txtlName" autocomplete="off" readonly="true" class="form-control" type="text" placeholder='<%   out.println(getUserInfo[1]); %>'/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div style="width:100%;margin:auto;" class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-phone fa-fw"></i></span>
                                                <input id="txtContactNum" name="txtContactNum" autocomplete="off" readonly="true" class="form-control" type="text" placeholder='<%   out.println(getUserInfo[4]); %>'/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div style="width:100%;margin:auto;" class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-envelope fa-fw"></i></span>
                                                <input id="txtEmail" name="txtEmail" autocomplete="off" readonly="true" class="form-control" type="text" placeholder='<%   out.println(getUserInfo[2]); %>'/>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td>
                                <h4>Postal Information</h4>
                                <table class="tblRight">
                                    <tr>
                                        <td>
                                            <div style="width:100%;margin:auto;" class="input-group">
                                                <span class="input-group-addon"><p style="height:9px;width:45px;text-align:center;padding-top:2px;">Street:</p></span>
                                                <input id="txtStreet" name="txtStreet" readonly="true" class="form-control" type="text" placeholder='<% out.println(address[0]); %>'/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div style="width:100%;margin:auto;" class="input-group">
                                                <span class="input-group-addon"><p style="height:9px;width:45px;text-align:center;padding-top:2px;">Suburb:</p></span>
                                                <input id="txtSuburb" name="txtSuburb" autocomplete="off" readonly="true" class="form-control" type="text" placeholder='<% out.println(address[1]); %>'/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div style="width:100%;margin:auto;" class="input-group">
                                                <span class="input-group-addon"><p style="height:9px;width:45px;text-align:center;padding-top:2px;">City:</p></span>
                                                <input id="txtCity" name="txtCity" autocomplete="off" readonly="true" class="form-control" type="text" placeholder='<% out.println(address[2]); %>'/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div style="width:100%;margin:auto;" class="input-group">
                                                <span class="input-group-addon"><p style="height:9px;width:45px;text-align:center;padding-top:2px;">Code:</p></span>
                                                <input id="txtCode" name="txtCode" autocomplete="off" readonly="true" class="form-control" type="text" placeholder='<% out.println(address[3]); %>'/>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr id="AccRow2" class="sec-row">
                            <td class="sec-row-col">
                                <h4>Change Password</h4>
                                <table class="tblLeft">
                                    <tr>
                                        <td>
                                            <div style="width:100%;margin:auto;" class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                                <input id="txtPass" name="txtPass"  readonly="true"  class="form-control" type="password" placeholder="Password"/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="height:38px;">
                                            <div style="width:100%;margin:auto;" id="ConfPassGroup" class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                                <input id="txtConfPass" name="txtConfPass" readonly="true" value="" class="form-control" type="password" placeholder="Confirm Password"/>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td class="sec-row-col">
                                <h4>Security Question Information</h4>
                                <table class="tblLeft">
                                    <tr>
                                        <td style="height:39px;">
                                            <p>What is your mother's maiden name?</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div style="width:100%;margin:auto;" class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                                <input id="txtAns" name="txtAns" readonly="true" class="form-control"  type="password" placeholder="Answer"/>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    </br>
                    <div class="pull-right">
                        <a href="#" id="btnChange" class="button-lg btn btn-default btn-sm" >Make Changes</a>  <input type="submit" value="Save Changes" id="btnSave" class="button-lg btn btn-default btn-sm" />  <a href="#" id="btnCancel" class="button btn btn-default btn-sm" >Cancel</a>
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
