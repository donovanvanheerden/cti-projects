<%-- 
    Document   : Logout
    Created on : Mar 11, 2015, 1:50:07 PM
    Author     : Donovan van Heerden - EL2014-0043
--%>

<%@ page import="java.util.*, AuctionWS.*" %>
<%@ page errorPage="AuctionErrorPage.jsp" %>
<html>
    <body>
        <%-- Login Check --%>
        <%
            try
            {
                AuctionService auc = new AuctionService();
                /*  Checks the email and password parameters passed if a user exists and sets the logged
                 *  cookies value to the users email address, and sets the login session attribute to 
                 *  true followed by a redirect to the home.jsp page, otherwise false if the user 
                 *  doesn't exist, and then redirects them to the Login.jsp page.
                 */
                if (auc.LoginCheck(request.getParameter("email"), request.getParameter("pass")))
                {
                    Cookie logged = new Cookie("logged", request.getParameter("email"));
                    response.addCookie(logged);
                    session.setAttribute("login", true);

                    response.sendRedirect("Home.jsp");
                }
                else
                {
                    //System.out.println("About to set login variable to false;");
                    session.setAttribute("login", false);
                    response.sendRedirect("Login.jsp");
                }
            }
            catch(Exception x)
            {
                System.out.println(x);
            }
        
        %>
    </body>
</html>
