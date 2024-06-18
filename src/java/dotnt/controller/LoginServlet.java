/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.controller;

import dotnt.registration.RegistrationDAO;
import dotnt.util.DBHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class LoginServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.html";
    private final String INVALID_PAGE = "invalid.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = INVALID_PAGE;
//        try { 
//            out.println("Username:" +username +"- Password:" + password +"- button"+button);
//            
//            System.err.println("Username:" +username +"- Password:" + password +"- button"+button);//cach thuc debug
//            }
        try {
            //1. get all user information
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String rememberUser = request.getParameter("checkRemember");
            //2. call method of Model(DAO)
            //2.1 New DAO Object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of DAO object 
            boolean result = dao.checkLogin(username, password);
            //3.process
            if (result) {
                url = SEARCH_PAGE;
                //store cookies
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(60 * 30);
                response.addCookie(cookie);
            }//username is authenticated

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
