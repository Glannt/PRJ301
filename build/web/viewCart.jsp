<%-- 
    Document   : viewCart
    Created on : Jun 17, 2024, 2:01:31 PM
    Author     : PC
--%>

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
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (String key : items.keySet()) {
                %>
                <tr>
                    <td>
                        <%= ++count %>
                    .</td>
                    <td>
                        <%= key %>
                    </td>
                    <td>
                        <%= items.get(key) %>
                    </td>
                </tr>    
                <%
                    }//each entry is processed
                %>
            </tbody>
        </table>

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
