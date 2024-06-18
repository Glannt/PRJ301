/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.controller;

import dotnt.cart.CartBean;
import dotnt.product.ProductDAO;
import dotnt.product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private final String MARKET_PLACE = "market.jsp";
    private final String DISPLAY_CONTROLLER = "DisplayProductServlet";

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
        String url = MARKET_PLACE;
        try {
           // request.getRequestDispatcher(DISPLAY_CONTROLLER).forward(request, response);
            //1.Cust goes to the cart place
            HttpSession session = request.getSession();
            //2. Cust takes his/her cart
            CartBean cart = (CartBean) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartBean();
            }
            //3.Cust drops an item to cart
            String item = request.getParameter("ddlBook");
            String quantity_raw = request.getParameter("numQuantity");
            int quantity = Integer.parseInt(quantity_raw);
            if(quantity == 1){
                cart.addItemToCart(item);
            } else{
                cart.addItemToCart(item, quantity);
            }
            
            session.setAttribute("CART", cart);
            url = DISPLAY_CONTROLLER;

        } finally {
            //4.Cust goes to shoping
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
