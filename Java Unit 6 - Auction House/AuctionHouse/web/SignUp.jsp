<%-- 
    Document   : SignUp
    Created on : 02 Mar 2015, 11:44:20 AM
    Author     : Donovan van Heerden - EL2014-0043
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, AuctionWS.*" %>
<%@ page errorPage="AuctionErrorPage.jsp" %>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta content="text/html; charset=UTF-8"/>
        <title>SignUp</title>
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
                    /*  Checks if the user is logged in, and redirects them to the Home.jsp if they are logged in. */
                    if (session.getAttribute("login").equals(true))
                    {
                        response.sendRedirect("Home.jsp");
                    }
                }
                catch(NullPointerException npe)
                {

                }

                AuctionService auc = new AuctionService();
                
                /*  setting the string variable all to ""  */
                String tempfName = "", templName = "", tempContactNum = "", tempEmail = "", tempStreet = "", tempSuburb = "", tempCity = "", tempCode = "";

                if (request.getMethod().equals("POST"))
                {
                    /*  assigning the request.getParameter() of each parameter to a variable to stop multiple
                     *  calls to the same request.getParameter() method.
                     */
                    String fName = request.getParameter("txtfName");
                    String lName = request.getParameter("txtlName");
                    String contactNum = request.getParameter("txtContactNum");
                    String email = request.getParameter("txtEmail");
                    String street = request.getParameter("txtStreet");
                    String suburb = request.getParameter("txtSuburb");
                    String city = request.getParameter("txtCity");
                    String code = request.getParameter("txtCode");
                    String confPass = request.getParameter("txtConfPass");
                    String pass = request.getParameter("txtPass");
                    String ans = request.getParameter("txtAns");
                    
                    /*  setting a temporary varialbe to certain variables */
                    String temp = fName + "|" + lName + "|" + contactNum + "|" + email + "|" + street + "," + suburb + "," + city + "," + code;
                    if(!fName.equals(""))
                    {
                       if (!(fName.length() > 20))
                       {
                            if(!lName.equals(""))
                            {
                                if(!(lName.length() > 50))
                                {
                                    if(contactNum.length() >= 10  && !contactNum.equals(""))
                                    {
                                        //System.out.println("Contact Number entered");
                                        if (contactNum.startsWith("27"))
                                        {
                                            if((!email.equals("")  && email.contains("@")))
                                            {
                                                //System.out.println("Email entered");

                                                if(!auc.UserExistsCheck(email))
                                                {
                                                    //System.out.println("Email is new");
                                                    if(!street.equals(""))
                                                    {
                                                        String tempAddress = street + ",";

                                                        if(!suburb.equals(""))
                                                        {
                                                            tempAddress += suburb + ",";

                                                            if(!city.equals(""))
                                                            {
                                                                tempAddress += city + ",";

                                                                if(!code.equals(""))
                                                                {
                                                                    tempAddress += code;
                                                                    if(!pass.equals(""))
                                                                    {
                                                                        if(!(pass.length() > 30))
                                                                        {
                                                                            if (confPass.equals(pass))
                                                                            {
                                                                                if(!ans.equals(""))
                                                                                {
                                                                                    if(!(ans.length() > 50))
                                                                                    {
                                                                                        if(auc.RegisterUser(fName, lName, email, pass, contactNum, tempAddress, ans))
                                                                                        {
                                                                                            /*  Used to set the message for the user */
                                                                                            session.setAttribute("signUp", "true|" + email + ", has signed up successfully!");
                                                                                            //System.out.println(session.getAttribute("signUp"));
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            /*  Used to set the message for the user */
                                                                                            session.setAttribute("signUp", "false|Please ensure that the information entered is valid!");
                                                                                        }
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        /*  Used to set the message for the user */
                                                                                        session.setAttribute("signUp", "false|Please enter an answer to the security question that contains 50 characters or less!");
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    /*  Used to set the message for the user */
                                                                                    session.setAttribute("signUp", "false|Please ensure that you have entered an answer for the Security Question!");
                                                                                }
                                                                            }
                                                                            else
                                                                            {
                                                                                /*  Used to set the message for the user */
                                                                                session.setAttribute("signUp", "false|Please ensure that the Password field and confirm Password are the same!");
                                                                            }
                                                                        }
                                                                        else
                                                                        {
                                                                            /*  Used to set the message for the user */
                                                                            session.setAttribute("signUp", "false|Please type a password that contains 30 characters or less!");
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        /*  Used to set the message for the user */
                                                                        session.setAttribute("signUp", "false|Please enter a value for the Password field!");
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    /*  Used to set the message for the user */
                                                                    session.setAttribute("signUp", "false|Please enter a value for the Code field!");
                                                                }
                                                            }
                                                            else
                                                            {
                                                                /*  Used to set the message for the user */
                                                                session.setAttribute("signUp", "false|Please enter a value for the City field!");
                                                            }
                                                        }
                                                        else
                                                        {
                                                            /*  Used to set the message for the user */
                                                            session.setAttribute("signUp", "false|Please enter a value for the Suburb field!");
                                                        }
                                                    }
                                                    else
                                                    {
                                                        /*  Used to set the message for the user */
                                                        session.setAttribute("signUp", "false|Please enter a value for the Street field!");
                                                    }
                                                }
                                                else
                                                {
                                                    /*  Used to set the message for the user */
                                                    session.setAttribute("signUp", "false|There is already an existing account registered to that email address!");
                                                }
                                            }
                                            else
                                            {
                                                /*  Used to set the message for the user */
                                                session.setAttribute("signUp", "false|Please ensure that you entered a valid email address!");
                                            }
                                        }
                                        else
                                        {
                                            /*  Used to set the message for the user */
                                            session.setAttribute("signUp", "false|Please ensure that you contact number starts with 27! (eg. 27783540098)");
                                        }
                                    }
                                    else
                                    {
                                        /*  Used to set the message for the user */
                                        session.setAttribute("signUp", "false|Please ensure that you enter your 11 digit contact number! (eg. 27783540098)");
                                    }
                                }
                                else
                                {
                                    /*  Used to set the message for the user */
                                    session.setAttribute("signUp", "false|Please enter a last name which contains 50 characters or less!");
                                }
                            }
                            else
                            {
                                /*  Used to set the message for the user */
                                session.setAttribute("signUp", "false|Please enter your last name!");
                            }
                       }
                       else
                       {
                           /*  Used to set the message for the user */
                           session.setAttribute("signUp", "false|Please enter a name which contains 20 characters or less!");
                       }
                    }
                    else
                    {
                        /*  Used to set the message for the user */
                        session.setAttribute("signUp", "false|Please enter your name!");
                    }

                    //System.out.println(temp);
                    String[] userTemp = temp.split("\\|");
                    //for (int x = 0; x < userTemp.length; x++)
                    //{
                    //    System.out.println("Index " + x + ": " + userTemp[x]);
                    //}

                    String[] tempAdd = userTemp[4].split("\\,");
                    //for (int y = 0; y < tempAdd.length; y++)
                    //{
                    //    System.out.println("Index " + y + ": " + tempAdd[y]);
                    //}

                    /*  This is used to set the temp variables to certain values if any were typed in
                     *  and then if a page reload occurs it will load those values back into their respective
                     *  input textboxes.
                     */
                    try
                    {
                        if (!userTemp[0].equals(null) || !userTemp[0].equals(""))
                        {
                            tempfName = userTemp[0];
                        }
                        if (!userTemp[1].equals(null) || !userTemp[1].equals(""))
                        {
                            templName = userTemp[1];
                        }
                        if (!userTemp[2].equals(null) || !userTemp[2].equals(""))
                        {
                            tempContactNum = userTemp[2];
                        }
                        if (!userTemp[3].equals(null) || !userTemp[3].equals(""))
                        {
                            tempEmail = userTemp[3];
                        }
                        if (!userTemp[4].equals(null) || !userTemp[4].equals(""))
                        {
                            if(!tempAdd[0].equals(null) || !tempAdd[0].equals(""))
                            {
                                tempStreet = tempAdd[0];
                            }
                            if(!tempAdd[1].equals(null) || !tempAdd[1].equals(""))
                            {
                                tempSuburb = tempAdd[1];
                            }
                            if(!tempAdd[2].equals(null) || !tempAdd[2].equals(""))
                            {
                                tempCity = tempAdd[2];
                            }
                            if(!tempAdd[3].equals(null) || !tempAdd[3].equals(""))
                            {
                                tempCode = tempAdd[3];
                            }
                        }

                    }
                    catch(NullPointerException npe)
                    {
                        //System.out.println("NullPointerException Caught!");
                    }
                    catch(ArrayIndexOutOfBoundsException arr)
                    {
                        //System.out.println("ArrayIndexOutOfBoundsException Caught!");
                    }
                    catch(Exception x)
                    {
                        System.out.println(x);
                    }
                }
        %>
        <script>
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
                        if (request.getMethod().equals("POST"))
                        {
                             /*  Below is used to display any messages to the user if the registration was successful, or if it failed.
                              *  If it was successful, it changes the message to be displayed and then redirects to the login.jsp page.
                              *  If it failed, it displays the appropriate message to the user.
                              */
                            String temp = (String)session.getAttribute("signUp");
                            
                            if (!temp.equals("") || !temp.equals(null))
                            {
                                String[] tempArr = temp.split("\\|");
                                
                                if (!tempArr[0].equals("false"))
                                {
                                    //out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(0,127,14,0.6);padding:5px;'>" + tempArr[1] + "</h2>");
                                    session.setAttribute("signUp", tempArr[1]);
                                    //System.out.println(session.getAttribute("signUp"));
                                    response.sendRedirect("Login.jsp");
                                }
                                else
                                {
                                    
                                    out.println("<h2 style='color:rgba(255,255,255,0.9);text-shadow: 0px 1px 1px black;background-color:rgba(255,25,25,0.6);padding:5px;'>" + tempArr[1] + "</h2>");
                                    
                                    //System.out.println(session.getAttribute("signUp"));
                                }
                                
                            }
                        }
                %>
            </div>
            <div class="container-content">
                <div id="content">
                    <h2 class="main-head">Create Account</h2>
                    <hr>
                    <form method="POST" action="SignUp.jsp">
                        <table class="tblMain">
                            <tr>
                                <td>
                                    <h4>Personal Information</h4>
                                    <table class="tblLeft">
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                                                    <input id="txtfName" name="txtfName" autocomplete="off" class="form-control" type="text" value="<% if (request.getMethod().equals("POST")){ out.println(tempfName);}%>" placeholder="First Name"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                                                    <input id="txtlName" name="txtlName" autocomplete="off" class="form-control" type="text" value="<% if (request.getMethod().equals("POST")){ out.println(templName);}%>" placeholder="Last Name"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-phone fa-fw"></i></span>
                                                    <input id="txtContactNum" name="txtContactNum" autocomplete="off" class="form-control" type="text" value="<% if (request.getMethod().equals("POST")){ out.println(tempContactNum);}%>" placeholder="Contact Number"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-envelope fa-fw"></i></span>
                                                    <input id="txtEmail" name="txtEmail" autocomplete="off" class="form-control" type="text" value="<% if (request.getMethod().equals("POST")){ out.println(tempEmail);}%>" placeholder="Email Address"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="height:38px;">
                                                
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
                                                    <input id="txtStreet" name="txtStreet" autocomplete="off" class="form-control" type="text" value="<% if (request.getMethod().equals("POST")){ out.println(tempStreet);}%>" placeholder="Street"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><p style="height:9px;width:45px;text-align:center;padding-top:2px;">Suburb:</p></span>
                                                    <input id="txtSuburb" name="txtSuburb" autocomplete="off" class="form-control" type="text" value="<% if (request.getMethod().equals("POST")){ out.println(tempSuburb);}%>" placeholder="Suburb"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><p style="height:9px;width:45px;text-align:center;padding-top:2px;">City:</p></span>
                                                    <input id="txtCity" name="txtCity" autocomplete="off" class="form-control" type="text" value="<% if (request.getMethod().equals("POST")){ out.println(tempCity);}%>" placeholder="City"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><p style="height:9px;width:45px;text-align:center;padding-top:2px;">Code:</p></span>
                                                    <input id="txtCode" name="txtCode" autocomplete="off" class="form-control" type="text" value="<% if (request.getMethod().equals("POST")){ out.println(tempCode);}%>" placeholder="Postal Code"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="height:38px;">
                                                
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h4>Account Information</h4>
                                    <table class="tblLeft">
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                                    <input id="txtPass" name="txtPass" class="form-control" type="password" placeholder="Password"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="width:100%;margin:auto;" class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                                    <input id="txtConfPass" name="txtConfPass" class="form-control" type="password" placeholder="Confirm Password"/>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
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
                                                        <input id="txtAns" name="txtAns" class="form-control" type="password" placeholder="Answer"/>
                                                    </div>
                                                </td>
                                            </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        </br>
                        <div class="pull-right" style="padding-right:40px;">
                            <input type="submit" value="Submit" id="btnSubmit" class="button btn btn-default btn-sm" />  <a href="Login.jsp" class="button btn btn-default btn-sm" >Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>  
