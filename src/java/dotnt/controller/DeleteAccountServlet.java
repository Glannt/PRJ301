/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.controller;

import dotnt.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {
    private final String ERROR_PAGE = "error.html";
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
        //1. Get all user's information
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        String url = ERROR_PAGE;
        try {
            //2.call method of model(DAO)
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.deleteAccount(username);
            //3.process result
            if(result){
                //.refresh by call the previous functions [Search] again by using URL Rewriting
                url = "MainServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }
        } catch(SQLException e){
            e.printStackTrace();
        } catch(NamingException e){
            e.printStackTrace();
        } finally{
            //vì khi gọi btAction được gọi 2 lần nên request Parameter tạo mảng 
            //không bt đc vị trí nên ko thể dùng request.dispatcher.forward
            response.sendRedirect(url);
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
