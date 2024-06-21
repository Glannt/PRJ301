/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.orderdetails;

import dotnt.cart.CartBean;
import dotnt.order.OrderDAO;
import dotnt.product.ProductDAO;
import dotnt.product.ProductDTO;
import dotnt.util.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author PC
 */
public class OrderDetailsDAO {

    public static boolean addOrderDetails(String OrderID, CartBean cart)
            throws SQLException, ClassNotFoundException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO "
                    + "OrderDetail(productId, unitPrice, quantity, orderId, total) "
                    + "VALUES (?,?,?,?,?)";
            ProductDAO productDao = new ProductDAO();
            for (String key : cart.getItems().keySet()) {
                stm = con.prepareStatement(sql);
                stm.setInt(1, productDao.getIdProduct(key));
                stm.setDouble(2, productDao.getProduct(key).getPrice());
                stm.setInt(3, cart.getItems().get(key));
                stm.setString(4, OrderID);
                stm.setDouble(5, totalPayMent(key, cart));
                final int AffectedRow = stm.executeUpdate();
                if (AffectedRow > 0) {
                    result = true;
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return result;
    }

    public static double totalPayMent(String productId, CartBean cart) {
        double total = 0;

        for (String key : cart.getItems().keySet()) {
            ProductDTO productDto = new ProductDTO();
            try {
                ProductDAO dao = new ProductDAO();
                productDto = dao.getProduct(key);
                if (productDto.getProductName().equals(productId)) {
                    total += productDto.getPrice() * cart.getItems().get(key);
                    return total;
                }
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }

        }

        return total;
    }
}
