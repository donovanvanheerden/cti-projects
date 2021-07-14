<%-- 
    Document   : Logout
    Created on : Mar 11, 2015, 1:50:07 PM
    Author     : Donovan van Heerden - EL2014-0043
--%>

<%@ page import="java.util.*, AuctionWS.*" %>
<%@ page errorPage="AuctionErrorPage.jsp" %>
<html>
    <body>
        <%-- Logout --%>
        <%
            try
            {
                /*  removes the session attribute login, and sets the logged cookie value to null
                 *  followed by a redirect to the Login.jsp page
                 */
                session.removeAttribute("login");
                Cookie cookie = new Cookie("logged", null);
                response.addCookie(cookie);
                response.sendRedirect("Login.jsp");
                
            }
            catch(NullPointerException npe)
            {
                //System.out.println(npe);
            }
            catch(Exception x)
            {
                System.out.println(x);
            }
        %>
    </body>
</html>
