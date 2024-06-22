<%-- 
    Document   : viewCart
    Created on : Jun 17, 2024, 2:01:31 PM
    Author     : PC
--%>

<%@page import="dotnt.product.ProductDTO"%>
<%@page import="dotnt.order.OrderDTO"%>
<%@page import="dotnt.order.OrderDAO"%>
<%@page import="dotnt.product.ProductDAO"%>
<%@page import="java.util.Map"%>
<%@page import="dotnt.cart.CartBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
    </head>
    <body>
        <h1>Your Cart includes</h1>
        <a href="MainServlet?btAction=market">Back to Market</a>
        <%
            //1.Cust goes to his/her cart
            if (session != null) {
                //2.Cust takes his/her cart
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    //3.Cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4.Cust shows all item
        %>
        <form action="MainServlet">
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Customer</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>Select Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (String key : items.keySet()) {


                %>
                <tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <input type="text" name="txtCustomer" value="<%= request.getParameter("txtCustomer")%>" />
                    </td>
                    <td>
                        <%= key%>
                    </td>
                    <td>
                        <%= items.get(key)%>
                    </td>
                    <td><textarea rows="1" name="txtAddress" id="" value="<%= request.getParameter("txtAddress")%>"></textarea></td>
                    <td>
                        <input type="text" name="txtEmail" value="<%= request.getParameter("txtEmail")%>" />
                    </td>
                    <td><input type="checkbox" name="chkItem"
                               value="<%= key%>"/></td>
                </tr>        
                    <%
                        }//each entry is processed
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="MainServlet?btAction=market">Add more book to your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Items" name="btAction"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" name="btAction" value="checkOut" />
        </form>      

        <%
                        return;
                    }
                }//cart has existed
            }//cart place has existed

        %>
        <h2>
            <font color="red">
            No cart is existed!!!
            </font>
        </h2>
    </body>
</html>
