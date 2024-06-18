<%-- 
    Document   : market
    Created on : Jun 18, 2024, 1:23:25 AM
    Author     : PC
--%>

<%@page import="dotnt.product.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="dotnt.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
    </head>
    <body>
    <head>
        <title>Book Market</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>

        <form action="MainServlet">

            Choose book <select name="ddlBook">
                <%
                    List<ProductDTO> result = (List<ProductDTO>) request.getAttribute("listProduct");
//                    List<ProductDTO> result = dao.listProducts();
                    if (result != null) {
                        for (ProductDTO dto : result) {
                %>
                <option><%= dto.getProductName()%></option>
                <%
                    }
                } else {
                %>
                <option value="">No products available</option>
                <%
                    }
                %>
            </select><br/>
            Quantity <input type="number" name="numQuantity" value="1"><br/>
            <input type="submit" value="Add Book to Your Cart" name="btAction" /><br/>
            <input type="submit" value="View Your Cart" name="btAction" /><br/>

        </form>
    </body>
</html>
