/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class CartBean implements Serializable{
    private Map<String, Integer> items;//ngăn chứa

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String item){
        if(item == null){
            return;
        }
        if(item.trim().isEmpty()){
            return;
        }
        //1. check existed items
        if(this.items == null){
            this.items = new HashMap<>();
        }
        //2. check existed item
        int quantity = 1;
        if(this.items.containsKey(item)) {
            quantity = this.items.get(item) + 1;
        }
        //3. drop item to items
        this.items.put(item, quantity);
    }
    
    public void addItemToCart(String item, int quantity){
        if(item == null){
            return;
        }
        if(item.trim().isEmpty()){
            return;
        }
        //1.check existed items
        if(this.items == null){
            this.items = new HashMap<>();
        }
        //2.check existed item
        if(this.items.containsKey(item)){
            quantity +=  this.items.get(item);
            if(quantity < 0){
                quantity = 0;
            }
        }
        //3.drop item to items
        this.items.put(item, quantity);
    }
    
    public void removeItemFromCart(String item){
        if(item == null){
            return;
        }
        if(item.trim().isEmpty()){
            return;
        }
        //1. check existed items
        if(this.items == null){
            return;
        }
        //2. check existed item
        if(this.items.containsKey(item)) {
            //3. remove item from items
            this.items.remove(item);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
    }
}
