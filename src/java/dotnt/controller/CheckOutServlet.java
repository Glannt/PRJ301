/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.controller;

import dotnt.cart.CartBean;
import dotnt.order.OrderDAO;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dotnt.orderdetails.OrderDetailsDAO;

/**
 *
 * @author PC
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String VIEW_CART_PAGE = "viewCart.jsp";

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
        String url = VIEW_CART_PAGE;
        try {

            HttpSession session = request.getSession(false);
            if (session != null) {
                CartBean cart = (CartBean) session.getAttribute("CART");
                String email = request.getParameter("txtEmail");
                String customer = request.getParameter("txtCustomer");
                String address = request.getParameter("txtAddress");
                System.out.println(email + " " + customer + " " + address);
//                ArrayList<ProductDTO> cartDetails =
//                        (ArrayList<ProductDTO>)session.getAttribute("CartDetails");
                String OrderID = null;
                OrderDAO orderDao = new OrderDAO();
                OrderDetailsDAO orderDetailDao = new OrderDetailsDAO();
                boolean result = false;
                double TotalPayment = orderDao.TotalPayment(cart);
                OrderID = orderDao.addOrder(customer, address, email, TotalPayment);
                result = orderDetailDao.addOrderDetails(OrderID, cart);
                if (OrderID != null && result != false) {
                    session.removeAttribute("CART");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
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
