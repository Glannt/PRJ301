/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.product;

import dotnt.cart.CartBean;
import dotnt.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author PC
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> products;

    public List<ProductDTO> getAllProduct() {
        return this.products;
    }

    public void listProducts() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select sku, name, description, quantity, price, status "
                        + "From Product ";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int sku = rs.getInt("sku");
                    String productName = rs.getString("name");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    boolean productStatus = rs.getBoolean("status");

                    ProductDTO dto = new ProductDTO(sku, productName, description, quantity, price, productStatus);
                    if (this.products == null) {
                        this.products = new ArrayList<>();
                    }
                    this.products.add(dto);
                }
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
    }

    public ProductDTO getProduct(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select sku, name, description, quantity, price, status "
                        + "From Product "
                        + "Where name = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();

                if (rs.next()) {
                    ProductDTO dto = new ProductDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getBoolean(6));
                    return dto;
                }
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

    public int getIdProduct(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select sku "
                        + "From Product "
                        + "Where name = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();

                if (rs.next()) {
                    result = rs.getInt("sku");
                }
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
        return result;
    }
}

