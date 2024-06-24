<%-- 
    Document   : search
    Created on : Jun 6, 2024, 3:50:37 PM
    Author     : PC
--%>

<%@page import="dotnt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8; IE=edge">
        <title>Search</title>
    </head>
    <body>
        <font>
            Welcome, ${sessionScope.USER.fullName}
            </font>
            <form class="form__logout" action="MainServlet">
                <input class="button__logout" type="submit" value="Logout" name="btAction" />
            </form>
            <h1>Search Page</h1>
            <form action="MainServlet">
                <label>Search value</label>
                <input type="text" name="txtSearchValue" 
                       value="${param.txtSearchValue}" />
                <input type="submit" value="Search" name="btAction" />

            </form>
            <c:set var="searchValue" value="${param.txtSearchValue}"/>
            <c:if test = "${not empty searchValue}">
                <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var = "dto" items="${result}" varStatus="counter"> 
                            <form action="MainServlet" method="POST">
                                <tr>
                                    <td>
                                        ${counter.count} 
                                    </td>
                                    <td>

                                        ${dto.username}
                                    </td>
                                    <td>
                                        ${dto.password}
                                    </td>
                                    <td>
                                        ${dto.fullName}
                                    </td>
                                    <td>

                                        <c:if test="${dto.role}">
                                            <input class="checkAdmin" type="checkbox" name="checkAdmin" value="ON" checked="checked" />
                                        </c:if>
                                        <c:if test="${!dto.role}">
                                            <input class="checkAdmin" type="checkbox" name="checkAdmin" value="ON" />
                                        </c:if>


                                    </td>
                                </tr>
                            </form>  
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty result}">
                        <h2>No record is matched!!!</h2>
                    </c:if>
                </c:if>
        <%--
        <%
            
            //1. get all Cookies
            Cookie[] cookies = request.getCookies();
            //2.check exsited Cookies
            if (cookies != null){
                Cookie recentCookie = cookies[cookies.length - 1];
                String username = recentCookie.getName();
                %>
                <font color="red">Welcome <%= username %></font>
                <%
            }
        %>
        <h1>Search Page</h1>
        <form action="MainServlet">
            Search value<input type="text" name="txtSearchValue" 
                               value="<%= request.getParameter("txtSearchValue")%>" /><br/>
            <input type="submit" value="Search" name="btAction" /><br/>
            <input type="submit" value="Logout" name="btAction" />
        </form>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");

                if (result != null) {//have value
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;//count stt
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "MainServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="MainServlet" method="POST">
                <tr>
                    <td>
                        <%= ++count%>   
                        .</td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" 
                               value="<%= dto.getUsername() %>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword"
                               value="<%= dto.getPassword() %>" />

                    </td>
                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="checkAdmin" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked ="checked"
                               <%
                                           }//admin role
%>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting %>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue"
                               value="<%= searchValue %>" />
                    </td>
                </tr>
            </form>  
            <%
                }//process each dto in result
            %>
        </tbody>
    </table>
    <%
    } else {//no value
    %>
    <h2>
        No record is matched!
    </h2>
    <%
            }
        }//not directly
    %>
        --%>
</body>
</html>
