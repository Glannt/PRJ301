/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdetails;

/**
 *
 * @author PC
 */
public class OrderDetailsDTO {
    private int id;
    private int productId;
    private double unitPrice;
    private String orderId;

    public OrderDetailsDTO(int id, int productId, double unitPrice, String orderId) {
        this.id = id;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.orderId = orderId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    
}
