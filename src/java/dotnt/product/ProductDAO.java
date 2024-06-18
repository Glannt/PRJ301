/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.product;

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

    public List<ProductDTO> listProducts() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select sku, productName, description, quantity, price, productStatus "
                        + "From tbl_Product "
                        + "Where sku = sku";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int sku = rs.getInt("sku");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    boolean productStatus = rs.getBoolean("productStatus");

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
        return this.products;
    }
}
