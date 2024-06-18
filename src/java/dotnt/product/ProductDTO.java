/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.product;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class ProductDTO implements Serializable {
    private int sku;
    private String productName;
    private String decription;
    private int quantity;
    private double price;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(int sku, String productName, String decription, int quantity, double price, boolean status) {
        this.sku = sku;
        this.productName = productName;
        this.decription = decription;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    /**
     * @return the sku
     */
    public int getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(int sku) {
        this.sku = sku;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the decription
     */
    public String getDecription() {
        return decription;
    }

    /**
     * @param decription the decription to set
     */
    public void setDecription(String decription) {
        this.decription = decription;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
    
}
