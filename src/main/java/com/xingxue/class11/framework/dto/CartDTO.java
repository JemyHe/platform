package com.xingxue.class11.framework.dto;

import com.xingxue.class11.entity.Cart;
import com.xingxue.class11.entity.Deal;


/**
 * 购物车显示
 */
public class CartDTO {

    private Cart cart;
    private Deal deal;
    private Integer subtotal;//小计

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public CartDTO() {
    }

    public CartDTO(Cart cart, Deal deal) {
        this.cart = cart;
        this.deal = deal;
        this.subtotal = deal.getDealPrice() * cart.getCount();
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cart=" + cart +
                ", deal=" + deal +
                ", subtotal=" + subtotal +
                '}';
    }
}
