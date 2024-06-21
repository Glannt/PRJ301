/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.order;

import dotnt.cart.CartBean;
import dotnt.product.ProductDAO;
import dotnt.product.ProductDTO;
import dotnt.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author PC
 */
public class OrderDAO implements Serializable {

    public static ArrayList<String> getOrders() throws SQLException, NamingException {
        ArrayList<String> orderIDs = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select id "
                    + "From Orders";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                if (orderIDs == null) {
                    orderIDs = new ArrayList<>();
                }
                orderIDs.add(id);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return orderIDs;
    }

    private static String generateOrderID() {
        ArrayList<String> OrderIDs = null;
        String stringTransform;
        try {
            OrderIDs = getOrders();
        } catch (SQLException | NamingException ex) {
        }
        int returnvalue = 1;
//        if (OrderIDs == null) {
//            stringTransform = Integer.toString(returnvalue);
//        }
        ArrayList<Integer> OrderIDValues = new ArrayList<>();
        if (OrderIDs != null) {
            for (String o : OrderIDs) {
                Integer x = null;
                System.out.println(o);
                try {
                    x = Integer.parseInt(o.substring(1));
                    System.out.println(o.substring(1));
                    System.out.println(x);
                } catch (NumberFormatException ex) {
                }
                if (x != null) {
                    OrderIDValues.add(x);
                }
                returnvalue++;
            }
        }

         stringTransform = Integer.toString(returnvalue);
        if (stringTransform.length() > 3) {
            return null;
        }
        int numberof0 = 3 - stringTransform.length();
        if (numberof0 > 0) {
            for (int i = 1; i <= numberof0; i++) {
                stringTransform = "0" + stringTransform;
            }
        }
        stringTransform = "Od" + stringTransform;
        return stringTransform;
    }

    public double TotalPayment(CartBean cart) {
        double total = 0;
        for (String key : cart.getItems().keySet()) {
            ProductDTO book = null;
            try {
                ProductDAO dao = new ProductDAO();
                book = dao.getProduct(key);
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
            total += book.getPrice() * cart.getItems().get(key);

        }
        return total;
    }

    public static String addOrder(String username, double total) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        String orderId = generateOrderID();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO Orders(id , date, customer, ADDRESS, email, total) "
                    + "VALUES (?,?,?,?,?,?);";
            stm = con.prepareStatement(sql);
            stm.setString(1, orderId);
            stm.setDate(2, date);
            stm.setString(3, username);
            stm.setString(4, "HCM");
            stm.setString(5, "tongnguyenthanhdo@gmail.com");
            stm.setDouble(6, total);
            int affectedRow = stm.executeUpdate();
            if (affectedRow > 0) {
                result = true;
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        if (result) {
            return orderId;
        } else {
            return null;
        }
    }

    public OrderDTO getOrder(String OrderId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "Select id, date, customer, address, email "
                    + "From Orders "
                    + "Where id = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, OrderId);
            rs = stm.executeQuery();
            if (rs.next()) {
                Date date = rs.getDate(2);
                OrderDTO dto
                        = new OrderDTO(rs.getString(1), date, rs.getString(3), rs.getString(4), rs.getString(5));
                return dto;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
}
