<%-- 
    Document   : AuctionErrorPage
    Created on : Mar 11, 2015, 1:53:17 PM
    Author     : Donovan van Heerden
--%>

<%@ page isErrorPage="true" %>
<%-- This page acts as the error page for all JSP pages in the AuctionHouse project --%>
<html>
    <head>
        <title>An error has occured</title>
        <link rel="SHORTCUT ICON" href="images/top_hat.png"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body style="background-color:black;">
        <div id="bg">
            <img src="images/background_image_00.jpg" alt="">
        </div>
        <div id="main">
            <div class="container-content">
                <div id="content">
                    <h2 class="main-head" style="color:red;text-shadow: 0px 1px 1px black;">The Following Error has Occured!</h2>
                    <hr>
                    </br>
                    <div style="background-color:white;height:450px;width:900px;margin:auto;box-shadow: 4px 4px 2px -2px rgba(0,0,0,0.2);padding:20px;overflow-y:auto;overflow-x:hidden;">
                        <p style="color:red;"><% out.println(exception.getMessage()); %></p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
