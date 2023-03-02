package com.cn.beep.data.entity;

/**
 * @author YCKJ3465
 * @since 2023/2/21 下午6:13
 */
public class OrderInfo {

    private Long orderInfoId;
    private String orderId;
    private String shopingName;
    private String shopingPrice;

    public Long getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Long orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopingName() {
        return shopingName;
    }

    public void setShopingName(String shopingName) {
        this.shopingName = shopingName;
    }

    public String getShopingPrice() {
        return shopingPrice;
    }

    public void setShopingPrice(String shopingPrice) {
        this.shopingPrice = shopingPrice;
    }
}
